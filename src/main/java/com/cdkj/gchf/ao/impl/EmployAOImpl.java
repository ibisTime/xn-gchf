package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IEmployAO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631460Req;
import com.cdkj.gchf.dto.req.XN631461Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EStaffStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class EmployAOImpl implements IEmployAO {

    @Autowired
    private IEmployBO employBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addEmploy(XN631460Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Employ.getCode());

        Employ data = new Employ();
        data.setCode(code);
        data.setProjectCode(req.getProjectCode());
        Project project = projectBO.getProject(req.getProjectCode());
        data.setProjectName(project.getName());
        data.setStaffCode(req.getStaffCode());

        Staff staff = staffBO.getStaff(req.getStaffCode());
        data.setStaffCode(staff.getMobile());
        data.setType(req.getType());
        data.setPosition(req.getPosition());
        data.setSalary(StringValidater.toLong(req.getSalary()));

        userBO.getUser(req.getUpUser());
        data.setUpUser(req.getUpUser());
        data.setJoinDatetiem(DateUtil.strToDate(req.getJoinDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        employBO.saveEmploy(data);
        return code;
    }

    @Override
    public void toHoliday(XN631461Req req) {
        Employ data = employBO.getEmploy(req.getCode());
        if (EStaffStatus.Work.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该员工不是在职状态");
        }
        Date start = DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date end = DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_1);

        if (start.after(end)) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }
        data.setStartDatetime(start);
        data.setEndDatetime(end);
        data.setStatus(EStaffStatus.Hoilday.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        employBO.toHoliday(data);

    }

    @Override
    public void leaveOffice(String code, String leavingDatetime, String updater,
            String remark) {
        Employ data = employBO.getEmploy(code);
        if (EStaffStatus.Leave.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该员工已经离职");
        }
        if (DateUtil
            .strToDate(leavingDatetime, DateUtil.FRONT_DATE_FORMAT_STRING)
            .after(new Date())) {
            throw new BizException("xn0000", "请输入正确的离职时间");
        }
        employBO.leaveOffice(data, leavingDatetime, updater, remark);
    }

    @Override
    public int editEmploy(Employ data) {
        return employBO.refreshEmploy(data);
    }

    @Override
    public int dropEmploy(String code) {
        return employBO.removeEmploy(code);
    }

    @Override
    public Paginable<Employ> queryEmployPage(int start, int limit,
            Employ condition) {
        return employBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Employ> queryEmployList(Employ condition) {
        return employBO.queryEmployList(condition);
    }

    @Override
    public Employ getEmploy(String code) {
        return employBO.getEmploy(code);
    }

}
