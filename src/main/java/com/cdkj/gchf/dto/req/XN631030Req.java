/**
 * @Title XNlh5033Req.java 
 * @Package com.xnjr.moom.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午10:43:57 
 * @version V1.0   
 */
package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增部门
 * @author: nyc 
 * @since: 2018年4月25日 下午9:00:02 
 * @history:
 */
public class XN631030Req {

    // （必填）名称
    @NotBlank(message = "名称不能为空")
    private String name;

    // （必填）负责人
    @NotBlank(message = "负责人不能为空")
    private String leader;

    // （必填）负责人手机号
    @NotBlank(message = "负责人手机号不能为空")
    private String leadeMobile;

    // （选填）上级部门编号
    private String parentCode;

    // 项目编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeadeMobile() {
        return leadeMobile;
    }

    public void setLeadeMobile(String leadeMobile) {
        this.leadeMobile = leadeMobile;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}
