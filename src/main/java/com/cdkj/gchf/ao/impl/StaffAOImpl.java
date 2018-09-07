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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.bo.IBankCardBO;
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
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631410Req;
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

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Staff.getCode());

        data = new Staff();
        data.setCode(code);
        data.setName(req.getRealName());
        data.setIdType(EIDKind.IDCard.getCode());
        data.setIdNo(req.getIdNo());
        data.setSex(req.getSex());

        data.setBirthday(DateUtil.strToDate(req.getBirthday(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setIdNation(req.getIdNation());
        data.setIdAddress(req.getIdAddress());
        data.setIdPic(req.getIdPic());
        data.setIdPolice(req.getIdPolice());

        data.setIdStartDate(DateUtil.strToDate(req.getIdStartDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setIdEndDate(DateUtil.strToDate(req.getIdEndDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());

        staffBO.saveStaff(data);
        return code;
    }

    @Override
    public void editPict1(String code, String pict1, String feat,
            String updater) {

        staffBO.refreshPict1(code, pict1, feat, updater);

    }

    @Override
    public void editPicts(XN631414Req req) {

        staffBO.refreshPicts(req.getCode(), req.getPict2(), req.getPict3(),
            req.getPict4(), req.getUpdater());

    }

    @Override
    public void editContactInfo(XN631413Req req) {

        staffBO.refreshContactInfo(req.getCode(), req.getMobile(),
            req.getContacts(), req.getContactsMobile(), req.getUpdater(),
            req.getRemark());

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

        // 获取昨天更新的免冠照
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

            staffBO.refreshFeat(staff.getCode(), featJson.getString("data"));

            return true;

        }

        return false;

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
            initStaff(staff);
        }

        return page;
    }

    @Override
    public List<Staff> queryStaffList(Staff condition) {
        return staffBO.queryStaffList(condition);
    }

    @Override
    public Staff getStaff(String code) {
        Staff data = staffBO.getStaff(code);

        initStaff(data);

        // 技能列表
        List<Skill> skillList = skillBO.querySkillByStaff(data.getCode());
        data.setSkillList(skillList);

        return data;
    }

    private void initStaff(Staff staff) {
        // 更新人
        staff.setUpdateName(getName(staff.getUpdater()));
    }

    @Override
    public Staff getStaffInfo(String code, List<String> projectCodeList) {

        Staff data = staffBO.getStaff(code);

        // 工作履历
        List<Employ> employList = new ArrayList<Employ>();
        if (CollectionUtils.isNotEmpty(projectCodeList)) {
            for (String projectCode : projectCodeList) {
                Employ condition = new Employ();
                condition.setStaffCode(data.getCode());
                condition.setProjectCode(projectCode);
                List<Employ> list = employBO.queryEmployList(condition);

                employList.addAll(list);
            }
        }
        data.setEmployList(employList);

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

        // 工作履历
        List<Employ> employList = new ArrayList<Employ>();

        // 正常工资条
        List<Salary> salaryList = new ArrayList<Salary>();

        // 异常工资条
        List<Salary> abnormalSalaryList = new ArrayList<Salary>();

        if (CollectionUtils.isNotEmpty(projectCodeList)) {
            for (String projectCode : projectCodeList) {
                Employ condition = new Employ();
                condition.setStaffCode(data.getCode());
                condition.setProjectCode(projectCode);
                List<Employ> list = employBO.queryEmployList(condition);

                if (CollectionUtils.isNotEmpty(list)) {
                    for (Employ employ : list) {
                        if (employ != null) {

                            // 填充工作履历及正常工资条记录
                            employList.add(employ);
                            salaryList = salaryBO
                                .getEmploySalary(employ.getCode());

                        }
                    }
                }

            }
        }

        if (CollectionUtils.isNotEmpty(salaryList)) {
            for (Salary salary : salaryList) {
                if (ESalaryStatus.Pay_Portion.getCode()
                    .equals(salary.getStatus())) {

                    // 填充异常工资条记录
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

    // 获取用户名称
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String getStaffFeatList(String projectCode) {
        // 项目的雇佣人员列表
        Employ condition = new Employ();
        condition.setProjectCode(projectCode);
        List<Employ> employList = employBO.queryEmployListByProject(projectCode,
            EEmployStatus.Not_Leave.getCode());

        // 项目的务工人员列表
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
