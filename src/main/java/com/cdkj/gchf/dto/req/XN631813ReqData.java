package com.cdkj.gchf.dto.req;

import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;

public class XN631813ReqData {
    private PayRoll payRoll;

    private PayRollDetail payRollDetail;

    private ProjectConfig projectConfig;

    public PayRoll getPayRoll() {
        return payRoll;
    }

    public void setPayRoll(PayRoll payRoll) {
        this.payRoll = payRoll;
    }

    public PayRollDetail getPayRollDetail() {
        return payRollDetail;
    }

    public void setPayRollDetail(PayRollDetail payRollDetail) {
        this.payRollDetail = payRollDetail;
    }

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

}
