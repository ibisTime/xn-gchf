package com.cdkj.gchf.dto.req;

public class XN631825Req extends APageReq {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1059948583642115264L;

    // 项目编号
    private String projectCode;

    // 设备状态
    private String state;

    // 设备命名成
    private String name;

    // 用户id
    private String userId;

    // 来源
    private String source;

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

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
