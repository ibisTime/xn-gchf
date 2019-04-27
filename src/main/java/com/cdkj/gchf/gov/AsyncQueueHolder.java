package com.cdkj.gchf.gov;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631635Req;
import com.cdkj.gchf.dto.req.XN631655Req;
import com.cdkj.gchf.dto.req.XN631695Req;
import com.cdkj.gchf.enums.EGovAsyncStatus;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.spring.SpringContextHolder;

@Component
public class AsyncQueueHolder {

    private static class serialMQHolder {
        private static LinkedList<QueueBean> serialMQ = new LinkedList<QueueBean>();
    }

    /**
     * 添加到上传状态更新队列，由定时器进行状态更新
     * @param resString 国家平台返回结果
     * @param projectConfig 系统配置
     * @param boClass bo类型，用于更新状态
     * @param code 数据编号
     * @param status 上传状态
     * @param logCode 日志编号
     * @create: Mar 23, 2019 2:04:23 PM silver
     * @history:
     */
    public static void addSerial(String resString, ProjectConfig projectConfig,
            String boClass, String code, String status, String logCode,
            String userId) {

        String requestSerialCode = GovUtil.parseRequestSerialCode(resString);

        QueueBean queueBean = new QueueBean(requestSerialCode,
            projectConfig.getProjectCode(), projectConfig.getSecret(), boClass,
            code, status, logCode, userId);

        if (EGovAsyncStatus.TO_HANDLE.getCode()
            .equals(handleQueueBean(queueBean))) {
            serialMQHolder.serialMQ.addLast(queueBean);
        }

    }

    @Scheduled(cron = "0/30 * * * * ? ")
    private void syncSerial() {

        synchronized (serialMQHolder.serialMQ) {
            Iterator<QueueBean> iterator = serialMQHolder.serialMQ.iterator();
            while (iterator.hasNext()) {
                if (EGovAsyncStatus.TO_HANDLE.getCode()
                    .equals(handleQueueBean(iterator.next()))) {
                    iterator.remove();
                }
            }
        }

    }

    private static String handleQueueBean(QueueBean queueBean) {
        AsyncRes asyncRes = GovUtil.queryAsyncHandleResult(
            queueBean.getRequestSerialCode(), queueBean.getProjectCode(),
            queueBean.getSecret());

        if (EGovAsyncStatus.SUCCESS.getCode().equals(asyncRes.getStatus())) {

            refreshUploadStatus(queueBean.getBoClass(), queueBean.getCode(),
                queueBean.getStatus());

            refreshLogRemark(queueBean.getLogCode(), asyncRes);

            if ("teamMasterBO".equals(queueBean.getBoClass())) {
                // 更新本地班组编号
                syncTeamSysNo(queueBean.getCode(), asyncRes);
                // 刷新状态
                ITeamMasterBO bean = SpringContextHolder
                    .getBean(ITeamMasterBO.class);
                bean.refreshUploadStatus(queueBean.getCode(),
                    EUploadStatus.UPLOAD_UPDATE.getCode());

            }
            if ("payRollDetailBO".equals(queueBean.getBoClass())) {

                syncPayRollDetailNo(queueBean.getCode(), asyncRes);

            }
        }

        if (EGovAsyncStatus.FAIL.getCode().equals(asyncRes.getStatus())) {

            // 上传失败 但是国家平台已存在。
            if (asyncRes.getResult().contains("项目中已存在身份证为")
                    || asyncRes.getResult().contains("参建单位已存在")
                    || asyncRes.getResult().contains("班组已存在")) {
                // 本地数据状态 为同步中
                refreshUploadStatus(queueBean.getBoClass(), queueBean.getCode(),
                    EUploadStatus.UPDATEING.getCode());
                // 可编辑的数据(参建单位、项目班组、项目人员)则同步国家平台信息
                if (queueBean.getBoClass().equals("projectCorpInfoBO")) {
                    // 同步国家平台数据
                    IProjectCorpInfoAO ao = SpringContextHolder
                        .getBean(IProjectCorpInfoAO.class);
                    XN631635Req req = new XN631635Req();
                    req.setCodeList(Arrays.asList(queueBean.getCode()));
                    req.setUserId(queueBean.getUserId());
                    ao.updatePlantformProjectCorpInfo(req);
                }
                if (queueBean.getBoClass().equals("teamMasterBO")) {
                    // 同步国家平台数据
                    // syncTeamSysNo(queueBean.getCode(), asyncRes);
                    ITeamMasterAO ao = SpringContextHolder
                        .getBean(ITeamMasterAO.class);
                    XN631655Req req = new XN631655Req();
                    req.setCodeList(Arrays.asList(queueBean.getCode()));
                    req.setUserId(queueBean.getUserId());
                    ao.updatePlantformTeamMaster(req);
                }
                if (queueBean.getBoClass().equals("projectWorkerBO")) {
                    // 同步国家平台数据
                    IProjectWorkerAO ao = SpringContextHolder
                        .getBean(IProjectWorkerAO.class);
                    XN631695Req req = new XN631695Req();
                    req.setCodeList(Arrays.asList(queueBean.getCode()));
                    req.setUserId(queueBean.getUserId());
                    ao.updatePlantformProjectWorker(req);
                }

            } else if (asyncRes.getCode().equals("1001")) {
                // 上传失败 参数异常
                if (queueBean.getBoClass().equals("projectCorpInfoBO")
                        || queueBean.getBoClass().equals("teamMasterBO")
                        || queueBean.getBoClass().equals("projectWorkerBO")) {

                    Object bo = SpringContextHolder
                        .getBean(queueBean.getBoClass());
                    if (queueBean.getBoClass().equals("projectCorpInfoBO")) {
                        IProjectCorpInfoBO projectCorpbo = (IProjectCorpInfoBO) bo;
                        ProjectCorpInfo projectCorpInfo = projectCorpbo
                            .getProjectCorpInfo(queueBean.getCode());
                        if (projectCorpInfo.getUploadStatus()
                            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())
                                || projectCorpInfo.getUploadStatus().equals(
                                    EUploadStatus.UPLOAD_UPDATE.getCode())) {
                            projectCorpbo.refreshUploadStatus(
                                queueBean.getCode(),
                                EUploadStatus.UPLOAD_UNUPDATE.getCode());
                        }
                    }
                    if (queueBean.getBoClass().equals("teamMasterBO")) {
                        ITeamMasterBO teamMasterBO = (ITeamMasterBO) bo;
                        TeamMaster teamMaster = teamMasterBO
                            .getTeamMaster(queueBean.getCode());
                        if (teamMaster.getUploadStatus()
                            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())
                                || teamMaster.getUploadStatus().equals(
                                    EUploadStatus.UPLOAD_UPDATE.getCode())) {
                            teamMasterBO.refreshUploadStatus(
                                queueBean.getCode(),
                                EUploadStatus.UPLOAD_UNUPDATE.getCode());
                        }
                    }
                    if (queueBean.getBoClass().equals("projectWorkerBO")) {
                        IProjectWorkerBO projectWorkerBO = (IProjectWorkerBO) bo;
                        ProjectWorker projectWorker = projectWorkerBO
                            .getProjectWorker(queueBean.getCode());
                        if (projectWorker.getUploadStatus()
                            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())
                                || projectWorker.getUploadStatus().equals(
                                    EUploadStatus.UPLOAD_UPDATE.getCode())) {
                            projectWorkerBO.refreshUploadStatus(
                                queueBean.getCode(),
                                EUploadStatus.UPLOAD_UNUPDATE.getCode());
                        }
                    }

                } else {
                    refreshUploadStatus(queueBean.getBoClass(),
                        queueBean.getCode(),
                        EUploadStatus.UPLOAD_FAIL.getCode());
                }
            } else if (asyncRes.getCode().equals("9999")) {
                // 修改失败
                refreshUploadStatus(queueBean.getBoClass(), queueBean.getCode(),
                    EUploadStatus.UPLOAD_UNUPDATE.getCode());

                refreshLogRemark(queueBean.getLogCode(), asyncRes);
            }

            else {
                // 上传失败。国家平台不存在
                refreshUploadStatus(queueBean.getBoClass(), queueBean.getCode(),
                    EUploadStatus.UPLOAD_FAIL.getCode());

                refreshLogRemark(queueBean.getLogCode(), asyncRes);
            }

        }

        return asyncRes.getStatus();
    }

    private static void refreshUploadStatus(String boClass, String code,
            String status) {

        Object result = null;

        try {
            result = SpringContextHolder.getBean(boClass);

            String[] arges = new String[] { code, status };

            Method method = result.getClass().getMethod("refreshUploadStatus",
                new Class[] { String.class, String.class });

            method.invoke(result, arges);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void refreshLogRemark(String code, AsyncRes asyncRes) {
        Object result = null;

        try {
            result = SpringContextHolder.getBean("operateLogBO");

            String remark = StringUtils.isEmpty(asyncRes.getResult())
                    ? asyncRes.getMessage() : asyncRes.getResult();
            String[] arges = new String[] { code, remark };

            Method method = result.getClass().getMethod("refreshRemark",
                new Class[] { String.class, String.class });

            method.invoke(result, arges);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void syncTeamSysNo(String code, AsyncRes asyncRes) {
        JSONObject resultJson = JSONObject.parseObject(asyncRes.getResult());
        String sysTeamNo = resultJson.getString("teamSysNo");

        try {

            // 更新项目班组国家平台班组编号
            Object teamMaster = SpringContextHolder.getBean("teamMasterBO");
            String[] teamMasterArges = new String[] { code, sysTeamNo };

            Method teamMasterMethod = teamMaster.getClass().getMethod(
                "refreshTeamSysNoByLocal",
                new Class[] { String.class, String.class });

            teamMasterMethod.invoke(teamMaster, teamMasterArges);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void syncPayRollDetailNo(String code, AsyncRes asyncRes) {
        JSONObject resultJson = JSONObject.parseObject(
            JSONObject.parseArray(asyncRes.getResult()).get(0).toString());
        String payRollCode = resultJson.getString("payrollCode");

        try {

            // 更新工资单国家平台编号
            Object payRollBO = SpringContextHolder.getBean("payRollBO");
            String[] payRollArges = new String[] { code, payRollCode };

            Method payRollMethod = payRollBO.getClass().getMethod(
                "refreshPayRollCodeByLocal",
                new Class[] { String.class, String.class });

            payRollMethod.invoke(payRollBO, payRollArges);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
