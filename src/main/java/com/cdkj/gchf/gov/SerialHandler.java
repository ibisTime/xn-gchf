package com.cdkj.gchf.gov;

import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.enums.EGovAsyncStatus;
import com.cdkj.gchf.exception.BizException;

public class SerialHandler {

    public static void handle(String resString,
            ProjectConfig projectConfig) {
        String requestSerialCode = GovUtil.parseRequestSerialCode(resString);

        AsyncRes asyncRes = GovUtil.queryAsyncHandleResult(requestSerialCode,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        if (EGovAsyncStatus.SUCCESS.getCode().equals(asyncRes.getStatus())) {
            return;
        } else if (EGovAsyncStatus.FAIL.getCode()
            .equals(asyncRes.getStatus())) {
            throw new BizException("XN0000", asyncRes.getResult());
        } else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
