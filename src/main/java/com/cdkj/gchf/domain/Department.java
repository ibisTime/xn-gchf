package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 部门
* @author: chenshan 
* @since: 2018-04-25 20:49:56
* @history:
*/
public class Department extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 所属公司编号
    private String companyCode;

    // 部门名称
    private String name;

    // 部门负责人
    private String leader;

    // 负责人手机号
    private String leadeMobile;

    // 上级部门编号
    private String parentCode;

    // ***************db*************

    // 关键字模糊查询
    private String keyword;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLeadeMobile() {
        return leadeMobile;
    }

    public void setLeadeMobile(String leadeMobile) {
        this.leadeMobile = leadeMobile;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
