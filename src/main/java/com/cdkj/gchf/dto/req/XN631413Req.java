package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 补录员工信息
 * @author: silver 
 * @since: 2018年7月6日 下午12:40:48 
 * @history:
 */
public class XN631413Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （选填）联系方式
    private String mobile;

    // （选填）紧急联系人
    private String contacts;

    // （选填）紧急联系人电话
    private String contactsMobile;

    // （选填）合同项目编号
    private String projectCode;

    // （选填）签约合同
    private String contentPic;

    // （选填）签约时间
    private String contractDatetime;

    // 技能
    private List<XN631413ReqSkill> skillList;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public List<XN631413ReqSkill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<XN631413ReqSkill> skillList) {
        this.skillList = skillList;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

    public String getContractDatetime() {
        return contractDatetime;
    }

    public void setContractDatetime(String contractDatetime) {
        this.contractDatetime = contractDatetime;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
