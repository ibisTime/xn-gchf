package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISkillBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.dto.req.XN631412Req;
import com.cdkj.gchf.dto.req.XN631413Req;
import com.cdkj.gchf.dto.req.XN631413ReqSkill;
import com.cdkj.gchf.dto.req.XN631414Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIDKind;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;
import com.google.gson.Gson;

@Service
public class StaffAOImpl implements IStaffAO {

    @Autowired
    IStaffBO staffBO;

    @Autowired
    IBankCardBO bankCardBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    ICcontractBO ccontractBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    IEmployBO employBO;

    @Autowired
    ISalaryBO salaryBO;

    @Autowired
    ISkillBO skillBO;

    @Autowired
    IDepartmentBO departmentBO;

    @Autowired
    private IAttendanceBO attendanceBO;

    @Override
    public String addStaff(XN631410Req req) {
        Staff data = staffBO.getStaffByIdNo(req.getIdNo());
        if (null != data) {
            return data.getCode();
        }

        Date date = new Date();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Staff.getCode());

        data = new Staff();
        data.setCode(code);
        data.setCompanyCode(req.getCompanyCode());
        data.setName(req.getRealName());
        data.setSex(req.getSex());
        data.setIdNation(req.getIdNation());

        data.setBirthday(DateUtil.strToDate(req.getBirthday(),
            DateUtil.DB_DATE_FORMAT_STRING));
        data.setIdType(EIDKind.IDCard.getCode());
        data.setIdNo(req.getIdNo());
        data.setIdAddress(req.getIdAddress());
        data.setIdPic(req.getIdPic());

        data.setIdPolice(req.getIdPolice());
        data.setIdStartDate(DateUtil.strToDate(req.getIdStartDate(),
            DateUtil.DB_DATE_FORMAT_STRING));
        data.setIdEndDate(DateUtil.strToDate(req.getIdEndDate(),
            DateUtil.DB_DATE_FORMAT_STRING));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(date);
        staffBO.saveStaff(data);
        return code;
    }

    @Override
    public void editFeat(String code, String pict1, String feat,
            String updater) {
        Staff data = staffBO.getStaff(code);
        staffBO.refreshFeat(data, pict1, feat, updater);

    }

    @Override
    public void editIdPict(XN631414Req req) {
        Staff data = staffBO.getStaff(req.getCode());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());
        data.setPict4(req.getPict4());
        data.setUpdater(req.getUpdater());

        Date date = new Date();
        data.setUpdateDatetime(date);
        staffBO.refreshIdPict(data);
    }

    @Override
    @Transactional
    public void editStaff(XN631412Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        Date date = new Date();
        Staff data = staffBO.getStaff(req.getCode());
        data.setMobile(req.getMobile());

        data.setPict1(req.getPict1());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        staffBO.refreshStaff(data);
        // 修改技能
        if (CollectionUtils.isNotEmpty(req.getSkillList())) {
            for (Skill skill : req.getSkillList()) {
                skillBO.refreshSkill(skill);
            }
        }
    }

    @Override
    @Transactional
    public void editStaffInfo(XN631413Req req) {
        if (StringUtils.isNotBlank(req.getMobile())) {
            PhoneUtil.checkMobile(req.getMobile());
        }
        if (StringUtils.isNotBlank(req.getContactsMobile())) {
            PhoneUtil.checkMobile(req.getContactsMobile());
        }

        Date date = new Date();
        Staff data = staffBO.getStaff(req.getCode());
        data.setContacts(req.getContacts());
        data.setContactsMobile(req.getContactsMobile());

        data.setMobile(req.getMobile());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        staffBO.refreshStaffInfo(data);

        // 每个员工只有一张工资卡;不存在时添加工资卡信息，存在时修改工资卡信息
        BankCard bankCardCondition = new BankCard();
        bankCardCondition.setStaffCode(req.getCode());
        if (CollectionUtils
            .isEmpty(bankCardBO.queryBankCardList(bankCardCondition))) {
            bankCardBO.addBankCard(req, data);
        } else {
            BankCard bankCard = bankCardBO.getBankCardByStaff(req.getCode());
            bankCardBO.refreshBankCard(bankCard.getCode(), req.getBankCode(),
                req.getBankName(), req.getSubbranch(), req.getBankcardNumber(),
                req.getUpdater(), null);
        }

        // 若存在合同则更新，否则添加
        Ccontract checkCcontract = ccontractBO.isExist(req.getProjectCode(),
            data.getCode());
        Date contractDatetime = DateUtil.strToDate(req.getContractDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        if (checkCcontract != null) {
            ccontractBO.refreshCcontract(checkCcontract.getCode(),
                req.getContentPic(), contractDatetime, req.getUpdater(),
                req.getRemark());
        } else {
            ccontractBO.saveCcontract(data.getCode(), req.getProjectCode(),
                req.getContentPic(), contractDatetime, req.getUpdater(),
                req.getRemark());
        }

        // 删除之前的技能信息
        skillBO.dropSkillByStaff(req.getCode());
        // 添加技能信息
        if (CollectionUtils.isNotEmpty(req.getSkillList())) {
            for (XN631413ReqSkill reqSkill : req.getSkillList()) {
                skillBO.saveSkill(data.getCode(), data.getName(), reqSkill);
            }
        }

        // 更新考勤员工号码信息
        attendanceBO.updateStaffMobile(req.getCode(), req.getMobile());
    }

    @Override
    public Staff getStaffInfo(String code, List<String> projectCodeList) {
        Staff data = staffBO.getStaff(code);
        // 所在项目及工资条
        List<Employ> employList = new ArrayList<Employ>();
        List<Salary> salaryList = new ArrayList<Salary>();
        if (CollectionUtils.isNotEmpty(projectCodeList)) {
            for (String projectCode : projectCodeList) {
                employList.add(
                    employBO.getEmployByStaff(data.getCode(), projectCode));
            }
        }

        data.setEmployList(employList);
        data.setSalaryList(salaryList);
        // 工资卡
        BankCard bankCard = bankCardBO.getBankCardByStaff(data.getCode());
        data.setBankCard(bankCard);
        // 技能
        List<Skill> skillList = skillBO.querySkillByStaff(data.getCode());
        data.setSkillList(skillList);
        return data;
    }

    @Override
    public Staff getStaffByIdNo(String idNo, List<String> projectCodeList) {
        Staff data = staffBO.getStaffByIdNo(idNo);
        if (null == data) {
            throw new BizException("xn0000", "该身份证对应的员工不存在！");
        }

        // 所在项目及工资条
        List<Employ> employList = new ArrayList<Employ>();
        List<Salary> salaryList = new ArrayList<Salary>();
        List<Salary> abnormalSalaryList = new ArrayList<Salary>();
        Employ employ = null;

        if (CollectionUtils.isNotEmpty(projectCodeList)) {
            List<Salary> sList = new ArrayList<Salary>();
            for (String projectCode : projectCodeList) {
                employ = employBO.getEmployByStaff(data.getCode(), projectCode);
                if (employ != null) {
                    employList.add(employ);
                }
                sList = salaryBO.getAbnormalSalaryByStaff(data.getCode(),
                    projectCode);
                if (CollectionUtils.isNotEmpty(sList)) {
                    salaryList.addAll(sList);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(salaryList)) {
            for (Salary salary : salaryList) {
                // 工资卡
                BankCard bankCard = bankCardBO
                    .getBankCardByStaff(data.getCode());
                salary.setBankCard(bankCard);
                salary.setBankcardNumber(bankCard.getBankcardNumber());
                if (ESalaryStatus.Pay_Portion.getCode()
                    .equals(salary.getStatus())) {
                    abnormalSalaryList.add(salary);
                }
            }
        }

        data.setEmployList(employList);
        data.setSalaryList(salaryList);
        data.setAbnormalSalaryList(abnormalSalaryList);

        List<Skill> skillList = skillBO.querySkillByStaff(data.getCode());
        data.setSkillList(skillList);
        return data;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;

    }

    @Override
    public Paginable<Staff> queryStaffPage(int start, int limit,
            Staff condition) {
        Paginable<Staff> page = staffBO.getPaginable(start, limit, condition);
        for (Staff staff : page.getList()) {
            staff.setUpdateName(getName(staff.getUpdater()));
        }
        return page;
    }

    @Override
    public List<Staff> queryStaffList(Staff condition) {
        List<Staff> list = staffBO.queryStaffList(condition);
        for (Staff staff : list) {
            staff.setUpdateName(getName(staff.getUpdater()));
        }
        return list;
    }

    @Override
    public Staff getStaff(String code) {
        Staff data = staffBO.getStaff(code);
        BankCard bankCard = bankCardBO.getBankCardByStaff(data.getCode());
        data.setBankCard(bankCard);
        data.setUpdateName(getName(data.getUpdater()));
        List<Skill> skillList = skillBO.querySkillByStaff(data.getCode());
        data.setSkillList(skillList);
        return data;
    }

    @Override
    public String getStaffFeatList() {
        JSONArray array = new JSONArray();
        List<Staff> list = staffBO.getStaffFeatList();
        for (Staff staff : list) {
            JSONObject obj = new JSONObject(new LinkedHashMap());
            if (StringUtils.isNotBlank(staff.getFeat())) {
                obj.put("id", staff.getCode());
                obj.put("name", staff.getName());
                obj.put("feat", staff.getFeat());
                array.add(obj);
            }
        }
        return new Gson().toJson(array);
    }

}
