package com.cdkj.gchf.gov;

import java.lang.reflect.Method;
import java.util.LinkedList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.enums.EGovAsyncStatus;
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
            String boClass, String code, String status, String logCode) {

        String requestSerialCode = GovUtil.parseRequestSerialCode(resString);

        serialMQHolder.serialMQ.addLast(
            new QueueBean(requestSerialCode, projectConfig.getProjectCode(),
                projectConfig.getSecret(), boClass, code, status, logCode));

    }

    @Scheduled(cron = "0/30 * * * * ? ")
    private void syncSerial() {

        synchronized (this) {
            for (QueueBean queueBean : serialMQHolder.serialMQ) {
                AsyncRes asyncRes = GovUtil.queryAsyncHandleResult(
                    queueBean.getRequestSerialCode(),
                    queueBean.getProjectCode(), queueBean.getSecret());

                if (EGovAsyncStatus.SUCCESS.getCode()
                    .equals(asyncRes.getStatus())) {

                    refreshUploadStatus(queueBean.getBoClass(),
                        queueBean.getCode(), queueBean.getStatus());

                    refreshLogRemark(queueBean.getLogCode(), asyncRes);

                    if ("teamMasterBO".equals(queueBean.getBoClass())) {
                        syncTeamSysNo(queueBean.getCode(), asyncRes);
                    }
                    if ("payRollDetailBO".equals(queueBean.getBoClass())) {
                        syncPayRollDetailNo(queueBean.getCode(), asyncRes);
                    }
                    serialMQHolder.serialMQ.remove(queueBean);
                }

                if (EGovAsyncStatus.FAIL.getCode()
                    .equals(asyncRes.getStatus())) {

                    refreshLogRemark(queueBean.getLogCode(), asyncRes);

                    serialMQHolder.serialMQ.remove(queueBean);
                }
            }
        }

    }

    private void refreshUploadStatus(String boClass, String code,
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

    private void refreshLogRemark(String code, AsyncRes asyncRes) {
        Object result = null;

        try {
            result = SpringContextHolder.getBean("operateLogBO");

            String remark = StringUtils.isEmpty(asyncRes.getResult())
                    ? asyncRes.getMessage()
                    : asyncRes.getResult();
            String[] arges = new String[] { code, remark };

            Method method = result.getClass().getMethod("refreshRemark",
                new Class[] { String.class, String.class });

            method.invoke(result, arges);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void syncTeamSysNo(String code, AsyncRes asyncRes) {
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

    private void syncPayRollDetailNo(String code, AsyncRes asyncRes) {
        JSONObject resultJson = JSONObject.parseObject(asyncRes.getResult());
        String payRollCode = resultJson.getString("payRollCode");

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
