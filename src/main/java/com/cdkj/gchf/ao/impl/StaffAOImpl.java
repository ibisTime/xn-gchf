package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISkillBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.dto.req.XN631412Req;
import com.cdkj.gchf.dto.req.XN631413Req;
import com.cdkj.gchf.dto.req.XN631414Req;
import com.cdkj.gchf.dto.res.XN631094Res;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIDKind;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.http.BizConnecter;
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

    private static final Log logger = LogFactory.getLog(StaffAOImpl.class);

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
    public void editPict1(String code, String pict1, String feat,
            String updater) {

        staffBO.refreshFeat(code, pict1, feat, updater);

    }

    @Override
    public void editPicts(XN631414Req req) {
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
        data.setPict4(req.getPict4());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        staffBO.refreshStaff(data);
    }

    @Override
    @Transactional
    public void editStaffInfo(XN631413Req req) {
        Staff data = staffBO.getStaff(req.getCode());
        data.setMobile(req.getMobile());
        data.setContacts(req.getContacts());
        data.setContactsMobile(req.getContactsMobile());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        staffBO.refreshStaffInfo(data);
    }

    @Override
    public XN631094Res refreshFeat(String projectCode) {
        Staff condition = new Staff();
        condition.setUpdateDatetimeStart(DateUtil.getTodayStart());
        condition.setUpdateDatetimeEnd(DateUtil.getTodayEnd());

        // 如果projectCode不为null，则更新所有员工
        if (null != projectCode) {
            condition.setProjectCode(projectCode);
        }

        int start = 0;
        int count = 0;
        StringBuffer staffNames = new StringBuffer();

        while (true) {

            List<Staff> staffList = staffBO.queryStaffPicList(condition, start,
                start + 10);
            start += 10;

            if (CollectionUtils.isNotEmpty(staffList)) {
                for (Staff staff : staffList) {
                    if (refreshStaffFeat(staff)) {
                        count++;
                        staffNames.append(staff.getName().trim() + ",");
                    }
                }
            } else {
                break;
            }

        }

        return new XN631094Res(count, staffNames.toString());
    }

    @Override
    public void doUpdateFeatDaily() {
        Staff condition = new Staff();
        condition.setFeat("NOFACE");

        int start = 0;

        while (true) {

            List<Staff> staffList = staffBO.queryStaffPicList(condition, start,
                start + 10);
            start += 10;

            if (CollectionUtils.isNotEmpty(staffList)) {
                for (Staff staff : staffList) {
                    refreshStaffFeat(staff);
                }
            } else {
                break;
            }

        }

        logger.info(DateUtil.getTodayStart() + "更新员工特征值成功。");
    }

    // 识别员工免冠照
    private boolean refreshStaffFeat(Staff staff) {

        String featResult = BizConnecter.getFeat(staff.getPict1());
        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(featResult);
        String featString = null;
        while (matcher.find()) {
            featString = matcher.group();
        }

        JSONObject featJson = JSONObject.parseObject("{" + featString + "}");
        String feat = featJson.getString("data");

        if (!feat.equals("error") && !feat.equals("NOFACE")
                && !feat.equals(staff.getFeat())) {

            staff.setFeat(feat);
            staffBO.refreshFeat(staff.getCode(), staff.getPict1(),
                featJson.getString("data"), "USYS201800000000001");

            return true;

        }

        return false;

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

        // 技能
        List<Skill> skillList = skillBO.querySkillByStaff(data.getCode());
        data.setSkillList(skillList);

        return data;
    }

    @Override
    public Staff getStaffByKeyword1(String keyword1,
            List<String> projectCodeList) {
        Staff data = staffBO.getStaffByKeyword1(keyword1);
        if (null == data) {
            if (CollectionUtils.isNotEmpty(projectCodeList)) {
                // 手持端查询直接提示异常
                throw new BizException("xn0000", "该身份证对应的员工不存在！");
            } else {
                // 业主端返回null
                return null;
            }
        }

        // 所在项目及工资条
        List<Employ> employList = new ArrayList<Employ>();
        List<Salary> salaryList = new ArrayList<Salary>();
        List<Salary> abnormalSalaryList = new ArrayList<Salary>();
        Employ employ = null;

        if (CollectionUtils.isNotEmpty(projectCodeList)) {
            for (String projectCode : projectCodeList) {
                employ = employBO.getEmployByStaff(data.getCode(), projectCode);
                if (employ != null) {
                    employList.add(employ);
                    salaryList = salaryBO.getEmploySalary(employ.getCode());
                }
            }
        }

        if (CollectionUtils.isNotEmpty(salaryList)) {
            for (Salary salary : salaryList) {
                // 工资卡
                BankCard bankCard = bankCardBO
                    .getEmployBankCard(salary.getEmployCode());
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

        // 技能列表
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
        // 重写分页查，加快查询时间
        long totalCount = staffBO.getTotalCount(condition);

        Paginable<Staff> page = new Page<Staff>(start, limit, totalCount);

        List<Staff> dataList = staffBO.queryStaffListBrief(condition,
            page.getStart(), page.getPageSize());

        page.setList(dataList);

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
        data.setUpdateName(getName(data.getUpdater()));
        List<Skill> skillList = skillBO.querySkillByStaff(data.getCode());
        data.setSkillList(skillList);
        return data;
    }

    @Override
    public String getStaffFeatList(String projectCode) {
        Employ employCondition = new Employ();
        employCondition.setProjectCode(projectCode);
        List<Employ> employList = employBO.queryEmployListByProject(projectCode,
            EEmployStatus.Not_Leave.getCode());

        List<String> staffCodeList = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(employList)) {
            for (Employ employ : employList) {
                staffCodeList.add(employ.getStaffCode());
            }
        }

        JSONArray array = new JSONArray();
        if (CollectionUtils.isNotEmpty(staffCodeList)) {
            List<Staff> list = staffBO.getStaffFeatList(staffCodeList);
            for (Staff staff : list) {
                JSONObject obj = new JSONObject(new LinkedHashMap());
                if (StringUtils.isNotBlank(staff.getFeat())) {
                    obj.put("id", staff.getCode());
                    obj.put("name", staff.getName());
                    obj.put("feat", staff.getFeat());
                    array.add(obj);
                }
            }
        }

        return new Gson().toJson(array);
    }

}
