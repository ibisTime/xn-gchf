package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.gchf.domain.Skill;

/**
 * 修改务工人员信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631412Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）证件类型
    @NotBlank(message = "证件类型不能为空")
    private String idType;

    // （必填）身份证号
    @NotBlank(message = "身份证号不能为空")
    private String idNo;

    // （必填）联系方式
    @NotBlank(message = "联系方式不能为空")
    private String mobile;

    // （必填）姓名
    @NotBlank(message = "姓名不能为空")
    private String name;

    // （必填）免冠照片
    @NotBlank(message = "免冠照片不能为空")
    private String pict1;

    // （必填）手持身份张照片
    @NotBlank(message = "手持身份张照片不能为空")
    private String pict2;

    // （必填）身份证正反面照片+签名
    @NotBlank(message = "身份证正反面照片+签名不能为空")
    private String pict3;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 员工技能
    private List<Skill> skillList;

    // （选填）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPict1() {
        return pict1;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

    public String getPict2() {
        return pict2;
    }

    public void setPict2(String pict2) {
        this.pict2 = pict2;
    }

    public String getPict3() {
        return pict3;
    }

    public void setPict3(String pict3) {
        this.pict3 = pict3;
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

}
