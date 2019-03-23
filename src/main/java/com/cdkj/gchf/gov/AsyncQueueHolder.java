package com.cdkj.gchf.gov;

import java.lang.reflect.Method;
import java.util.LinkedList;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.enums.EGovAsyncStatus;
import com.cdkj.gchf.spring.SpringContextHolder;

@Component
public class AsyncQueueHolder {

    private static class serialMQHolder {
        private static LinkedList<QueueBean> instance = new LinkedList<QueueBean>();
    }

    public static void addSerial(String resString, ProjectConfig projectConfig,
            String boClass, String code, String status, String logCode) {

        String requestSerialCode = GovUtil.parseRequestSerialCode(resString);

        serialMQHolder.instance.addLast(
            new QueueBean(requestSerialCode, projectConfig.getProjectCode(),
                projectConfig.getSecret(), boClass, code, status, logCode));

    }

    @Scheduled(cron = "0/30 * * * * ? ")
    private void syncSerial() {

        synchronized (this) {
            while (CollectionUtils.isNotEmpty(serialMQHolder.instance)) {
                QueueBean queueBean = serialMQHolder.instance.peekFirst();

                AsyncRes asyncRes = GovUtil.queryAsyncHandleResult(
                    queueBean.getRequestSerialCode(),
                    queueBean.getProjectCode(), queueBean.getSecret());

                if (EGovAsyncStatus.SUCCESS.getCode()
                    .equals(asyncRes.getStatus())) {

                    refreshUploadStatus(queueBean.getBoClass(),
                        queueBean.getCode(), queueBean.getStatus());

                    refreshLogRemark(queueBean.getLogCode(), asyncRes);

                    serialMQHolder.instance.remove(queueBean);
                }

                if (EGovAsyncStatus.FAIL.getCode()
                    .equals(asyncRes.getStatus())) {

                    refreshLogRemark(queueBean.getLogCode(), asyncRes);

                    serialMQHolder.instance.remove(queueBean);
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
            result = SpringContextHolder.getBean(IOperateLogBO.class);

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

}
