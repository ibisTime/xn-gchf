package com.cdkj.gchf.dto.req;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN631077Req {

    // 用户编号(必填)
    private String userId;

    // 部门编号(必填)
    private String departmentCode;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
