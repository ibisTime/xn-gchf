package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查询请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午4:31:04 
 * @history:
 */
public class XN631468Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -9190494112316250359L;

    // 员工编号
    private String staffCode;

    // 项目编号
    private String projectCode;

    // 雇佣编号
    private String employCode;

    // 项目编号列表
    private List<String> projectCodeList;

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

}
