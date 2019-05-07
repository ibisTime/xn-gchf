package com.cdkj.gchf.dto.req;

public class XN631837Req extends AListReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -2647040393828306249L;

    // 设备人员状态
    private String state;

    // 用户id
    private String userId;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
