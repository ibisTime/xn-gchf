package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * 员工
 * @author: nyc 
 * @since: 2018年4月29日 下午8:48:09 
 * @history:
 */
public class Staff extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 姓名
    private String name;

    // 部门编号
    private String departmentCode;

    // 手机号
    private String mobile;

    // 证件类型
    private String idType;

    // 证件号
    private String idNo;

    // 性别
    private String sex;

    // 出生年月日
    private Date birthday;

    // 民族
    private String idNation;

    // 身份证上籍贯
    private String idAddress;

    // 身份证上头像
    private String idPic;

    // 签发机关
    private String idPolice;

    // 证件有效开始时间
    private Date idStartDate;

    // 证件有效结束时间
    private Date idEndDate;

    // 免冠照片
    private String pict1;

    // 手持身份张照片
    private String pict2;

    // 身份证正反面照片+签名
    private String pict3;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 特征值
    private String feat;

    // 紧急联系人
    private String contacts;

    // 紧急联系人电话
    private String contactsMobile;

    // ***************db***********
    // 关键字模糊查询
    private String keyword;

    // 工资卡
    private BankCard bankCard;

    // 公司编号List
    private List<String> projectCodeList;

    // 雇佣List
    private List<Employ> employList;

    // 工资条List
    private List<Salary> salaryList;

    // 异常工资条List
    private List<Salary> abnormalSalaryList;

    // 更新人名字
    private String updateName;

    // 技能
    private List<Skill> skillList;

    // 薪资发放状态
    private String salaryStatus;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNation() {
        return idNation;
    }

    public void setIdNation(String idNation) {
        this.idNation = idNation;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getIdPic() {
        return idPic;
    }

    public void setIdPic(String idPic) {
        this.idPic = idPic;
    }

    public String getIdPolice() {
        return idPolice;
    }

    public void setIdPolice(String idPolice) {
        this.idPolice = idPolice;
    }

    public Date getIdStartDate() {
        return idStartDate;
    }

    public void setIdStartDate(Date idStartDate) {
        this.idStartDate = idStartDate;
    }

    public Date getIdEndDate() {
        return idEndDate;
    }

    public void setIdEndDate(Date idEndDate) {
        this.idEndDate = idEndDate;
    }

    public String getPict1() {
        return pict1;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setPict2(String pict2) {
        this.pict2 = pict2;
    }

    public String getPict2() {
        return pict2;
    }

    public void setPict3(String pict3) {
        this.pict3 = pict3;
    }

    public String getPict3() {
        return pict3;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getFeat() {
        return feat;
    }

    public void setFeat(String feat) {
        this.feat = feat;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Employ> getEmployList() {
        return employList;
    }

    public void setEmployList(List<Employ> employList) {
        this.employList = employList;
    }

    public List<Salary> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<Salary> salaryList) {
        this.salaryList = salaryList;
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public String getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    public List<Salary> getAbnormalSalaryList() {
        return abnormalSalaryList;
    }

    public void setAbnormalSalaryList(List<Salary> abnormalSalaryList) {
        this.abnormalSalaryList = abnormalSalaryList;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

}
