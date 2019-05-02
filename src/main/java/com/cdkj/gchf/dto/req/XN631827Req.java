package com.cdkj.gchf.dto.req;

public class XN631827Req extends AListReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1105368290783428192L;

    // 项目编号
    private String projectCode;

    // 设备状态
    private String state;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
