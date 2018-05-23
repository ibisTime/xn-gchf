package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICcontractBO;
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
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.dto.req.XN631411Req;
import com.cdkj.gchf.dto.req.XN631412Req;
import com.cdkj.gchf.dto.req.XN631413Req;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
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

    @Override
    public String addStaff(XN631410Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        Date date = new Date();
        String code = null;
        User user = userBO.getUser(req.getUpdater());
        Staff staff = null;
        BankCard bankCard = new BankCard();
        Ccontract ccontract = new Ccontract();

        Project project = projectBO.getProject(req.getProjectCode());
        // 合同信息
        String ccontractCode = OrderNoGenerater
            .generate(EGeneratePrefix.Ccontract.getCode());
        ccontract.setCode(ccontractCode);
        ccontract.setProjectName(project.getName());
        ccontract.setProjectCode(project.getCode());
        ccontract.setStaffMobile(req.getMobile());

        ccontract.setProjectName(project.getName());
        ccontract.setContentPic(req.getContentPic());
        ccontract.setContractDatetime(DateUtil.strToDate(
            req.getContractDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        ccontract.setUpdater(req.getUpdater());

        ccontract.setUpdateDatetime(date);
        ccontract.setRemark(req.getRemark());
        // 工资卡信息 ;
        bankCard.setCompanyCode(project.getCompanyCode());
        bankCard.setBankCode(req.getBankCode());
        bankCard.setBankName(req.getBankName());
        bankCard.setBankcardNumber(req.getBankcardNumber());

        bankCard.setSubbranch(req.getSubbranch());
        bankCard.setUpdater(req.getUpdater());
        bankCard.setUpdateDatetime(date);
        bankCard.setRemark(req.getRemark());

        if (StringUtils.isNotBlank(req.getStaffCode())) {
            staff = staffBO.getStaff(req.getStaffCode());
            code = staff.getCode();
            // 更新银行卡信息
            bankCard = bankCardBO.getBankCardByStaff(code);
            bankCardBO.refreshBankCard(bankCard);
            // 添加新合同信息
            ccontract.setStaffCode(code);
            ccontractBO.saveCcontract(ccontract);
            return code;
        }

        Staff data = new Staff();
        code = OrderNoGenerater.generate(EGeneratePrefix.Staff.getCode());
        data.setCode(code);
        data.setIdType(req.getIdType());
        data.setIdNo(req.getIdNo());
        data.setMobile(req.getMobile());
        data.setName(req.getName());

        data.setCompanyCode(user.getCompanyCode());
        data.setCompanyName(user.getCompanyName());
        data.setPict1(req.getPict1());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        data.setFeat(req.getFeat());
        staffBO.saveStaff(data);
        // 添加工资卡
        bankCard.setStaffCode(code);
        bankCard.setStaffName(req.getName());
        bankCardBO.addBankCard(bankCard);

        // 添加新合同信息
        ccontract.setStaffCode(code);
        ccontractBO.saveCcontract(ccontract);

        return code;
    }

    @Override
    public void editStaff(XN631412Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        Date date = new Date();
        Staff data = staffBO.getStaff(req.getCode());
        data.setIdType(req.getIdType());
        data.setIdNo(req.getIdNo());
        data.setMobile(req.getMobile());
        data.setName(req.getName());

        data.setPict1(req.getPict1());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        staffBO.refreshStaff(data);
        // 修改技能
        for (Skill skill : req.getSkillList()) {
            skillBO.refreshSkill(skill);
        }
    }

    @Override
    public void dropStaff(String code) {
        staffBO.removeStaff(code);
    }

    @Override
    public Paginable<Staff> queryStaffPage(int start, int limit,
            Staff condition) {
        Paginable<Staff> page = staffBO.getPaginable(start, limit, condition);
        String updateName = null;
        for (Staff staff : page.getList()) {
            updateName = getName(staff.getUpdater());
            staff.setUpdateName(updateName);
        }
        return page;
    }

    @Override
    public List<Staff> queryStaffList(Staff condition) {
        List<Staff> list = staffBO.queryStaffList(condition);
        String updateName = null;
        for (Staff staff : list) {
            updateName = getName(staff.getUpdater());
            staff.setUpdateName(updateName);
        }

        return list;
    }

    @Override
    public Staff getStaff(String code) {
        Staff data = staffBO.getStaff(code);
        BankCard bankCard = bankCardBO.getBankCardByStaff(data.getCode());
        data.setBankCard(bankCard);
        String updateName = getName(data.getUpdater());
        data.setUpdateName(updateName);
        return data;
    }

    @Override
    public String getStaffFeatList() {
        JSONArray json = new JSONArray();
        JSONObject jo = new JSONObject();
        List<Staff> list = staffBO.getStaffFeatList();
        for (Staff staff : list) {
            if (StringUtils.isNotBlank(staff.getFeat())) {

                jo.put("id", staff.getCode());
                jo.put("feat", staff.getFeat());
                json.add(jo);
            }
        }
        return new Gson().toJson(json);
    }

    @Override
    public String addStaff(XN631411Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Staff.getCode());
        Staff data = null;
        if (StringUtils.isNotBlank(req.getIdNo())) {
            data = staffBO.getStaffByIdNo(req.getIdNo());
            if (data != null) {
                throw new BizException("xn000000", "档案库中已有此人的资料");
            }
        }
        data = new Staff();
        data.setCode(code);
        data.setName(req.getRealName());
        data.setSex(req.getSex());
        data.setIdNation(req.getIdNation());
        data.setBirthday(DateUtil.strToDate(req.getBirthday(),
            DateUtil.DB_DATE_FORMAT_STRING));

        data.setIdType(req.getIdKind());
        data.setIdNo(req.getIdNo());
        data.setIdAddress(req.getIdAddress());
        data.setIdPic(req.getIdPic());
        data.setIdPolice(req.getIdPolice());

        data.setIdStartDate(DateUtil.strToDate(req.getIdStartDate(),
            DateUtil.DB_DATE_FORMAT_STRING));
        data.setIdEndDate(DateUtil.strToDate(req.getIdEndDate(),
            DateUtil.DB_DATE_FORMAT_STRING));
        data.setFeat(req.getFeat());

        staffBO.saveStaff(data);
        return code;
    }

    @Override
    public void addStaffInfo(XN631413Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        PhoneUtil.checkMobile(req.getContactsMobile());

        Date date = new Date();
        Staff data = staffBO.getStaff(req.getCode());
        data.setPict1(req.getPict1());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());

        data.setContacts(req.getContacts());
        data.setMobile(req.getContactsMobile());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(date);

        data.setRemark(req.getRemark());
        staffBO.saveStaffInfo(data);

        // 添加工资卡
        BankCard bankCard = new BankCard();
        bankCard.setStaffName(data.getName());
        bankCard.setBankCode(req.getBankCode());
        bankCard.setBankName(req.getBankName());
        bankCard.setBankcardNumber(req.getBankcardNumber());

        bankCard.setStatus(EBankCardStatus.Normal.getCode());
        bankCard.setSubbranch(req.getSubbranch());
        bankCard.setUpdater(req.getUpdater());
        bankCard.setUpdateDatetime(date);
        bankCard.setRemark(req.getRemark());

        BankCard card = bankCardBO.getBankCardByStaff(data.getCode());
        if (card == null) {
            bankCard.setStaffCode(data.getCode());
            bankCardBO.addBankCard(bankCard);
        } else {
            bankCard.setCode(card.getCode());
            bankCardBO.refreshBankCard(bankCard);
        }

        // 添加技能信息
        String skillCode = null;
        for (Skill sData : req.getSkillList()) {
            Skill skill = new Skill();
            skillCode = OrderNoGenerater
                .generate(EGeneratePrefix.Skill.getCode());
            skill.setCode(skillCode);
            skill.setStaffCode(data.getCode());
            skill.setStaffName(data.getName());
            skill.setName(sData.getName());

            skill.setScore(sData.getScore());
            skill.setPdf(sData.getPdf());
            skillBO.saveSkill(skill);
        }

    }

    @Override
    public Staff getStaffInfo(String code, List<String> projectCodeList) {
        Staff data = staffBO.getStaff(code);
        // 所在项目及工资条
        List<Employ> employList = new ArrayList<Employ>();
        List<Salary> salaryList = new ArrayList<Salary>();
        for (String projectCode : projectCodeList) {
            employList
                .add(employBO.getEmployByStaff(data.getCode(), projectCode));
            // salaryList
            // .add(salaryBO.getSalaryByStaff(data.getCode(), projectCode));
        }
        data.setEmployList(employList);
        data.setSalaryList(salaryList);
        // 工资卡
        BankCard bankCard = bankCardBO.getBankCardByStaff(data.getCode());
        data.setBankCard(bankCard);
        // 技能
        return data;
    }

    @Override
    public Staff getStaffByIdNo(String idNo, List<String> projectCodeList) {
        Staff data = staffBO.getStaffByIdNo(idNo);
        // 所在项目及工资条
        List<Employ> employList = new ArrayList<Employ>();
        List<Salary> salaryList = new ArrayList<Salary>();
        if (CollectionUtils.isNotEmpty(projectCodeList)) {
            for (String projectCode : projectCodeList) {
                employList.add(
                    employBO.getEmployByStaff(data.getCode(), projectCode));

                salaryList.addAll(
                    salaryBO.getSalaryByStaff(data.getCode(), projectCode));
            }
        }
        data.setEmployList(employList);
        data.setSalaryList(salaryList);
        // 工资卡
        BankCard bankCard = bankCardBO.getBankCardByStaff(data.getCode());
        data.setBankCard(bankCard);

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

}
