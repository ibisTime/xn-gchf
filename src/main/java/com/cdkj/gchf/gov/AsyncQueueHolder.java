package com.cdkj.gchf.gov;

import java.lang.reflect.Method;
import java.util.LinkedList;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.enums.EGovAsyncStatus;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.spring.SpringContextHolder;

@Component
public class AsyncQueueHolder {

    private static LinkedList<QueueBean> serialMQ = null;

    private static void init() {
        serialMQ = new LinkedList<QueueBean>();
    }

    public static void addSerial(String connectRes, ProjectConfig projectConfig,
            String boClass, String code, String status) {
        if (null == serialMQ) {
            init();
        }

        serialMQ.add(new QueueBean(connectRes, projectConfig.getProjectCode(),
            projectConfig.getSecret(), boClass, code, status));

    }

    @Scheduled(cron = "0/5 * * * * ? ")
    private void syncSerial() {
        if (CollectionUtils.isNotEmpty(serialMQ)) {
            for (QueueBean queueBean : serialMQ) {
                String requestSerialCode = GovUtil
                    .parseRequestSerialCode(queueBean.getConnectRes());

                AsyncRes asyncRes = GovUtil.queryAsyncHandleResult(
                    requestSerialCode, queueBean.getProjectCode(),
                    queueBean.getSecret());

                // 更新状态
                if (EGovAsyncStatus.SUCCESS.getCode()
                    .equals(asyncRes.getStatus())) {

                    refreshUploadStatus(queueBean.getBoClass(),
                        queueBean.getCode());

                    serialMQ.remove(queueBean);
                }

            }
        }
    }

    private void refreshUploadStatus(String boClass, String code) {

        Object result = null;
        try {
            result = SpringContextHolder.getBean(boClass);

            String[] arges = new String[] { code,
                    EUploadStatus.UPLOAD_EDITABLE.getCode() };

            Method method = result.getClass().getMethod("refreshUploadStatus",
                new Class[] { String.class, String.class });

            method.invoke(result, arges);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
