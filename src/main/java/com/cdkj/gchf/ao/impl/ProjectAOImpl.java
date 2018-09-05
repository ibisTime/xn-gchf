package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectCardBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCard;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectAOImpl implements IProjectAO {

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectCardBO projectCardBO;

    @Autowired
    private IReportBO reportBO;

    @Autowired
    IEmployBO employBO;

    @Autowired
    IDepartmentBO departmentBO;

    @Override
    @Transactional
    public String addProject(XN631350Req req) {
        Project condition = new Project();
        condition.setName(req.getName());
        if (CollectionUtils.isNotEmpty(projectBO.queryProject(condition))) {
            throw new BizException("xn000", "项目名称已存在，请重新输入！");
        }

        // 添加项目
        String code = projectBO.saveProject(req.getName(), req.getUpdater(),
            req.getRemark());

        // 添加项目管理员
        userBO.saveProjectAdmin(code, req.getName());

        // 添加项目账户
        projectCardBO.saveProjectCard(code);

        return code;
    }

    @Override
    @Transactional
    public void editProject(XN631352Req req) {
        Project data = projectBO.getProject(req.getCode());
        if (EProjectStatus.End.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该项目已结束，无法编辑！");
        }

        if (DateUtil
            .strToDate(req.getAttendanceStarttime(),
                DateUtil.DATA_TIME_PATTERN_7)
            .after(DateUtil.strToDate(req.getAttendanceEndtime(),
                DateUtil.DATA_TIME_PATTERN_7))) {
            throw new BizException("xn000", "上班时间不能晚于下班时间！");
        }

        data.setCompanyName(req.getCompanyName());
        data.setChargeUser(req.getChargeUser());
        data.setChargeMobile(req.getChargeMobile());

        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setSalaryCreateDatetime(req.getSalaryCreateDatetime());
        data.setSalaryDatetime(req.getSalaryDatetime());
        data.setAttendanceStarttime(req.getAttendanceStarttime());
        data.setAttendanceEndtime(req.getAttendanceEndtime());

        String status = data.getStatus();
        if (EProjectStatus.To_Edit.getCode().equals(status)) {
            status = EProjectStatus.To_Building.getCode();
        }
        data.setStatus(status);
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        projectBO.editProject(data);

        // 更新账户信息
        ProjectCard projectCard = projectCardBO
            .getProjectCardByProject(req.getCode());
        if (null != projectCard) {
            projectCardBO.refreshProjectCard(projectCard.getCode(),
                req.getBankCode(), req.getBankName(), req.getAccountName(),
                req.getBankcardNumber(), req.getSubbranch(), req.getUpdater(),
                data.getUpdateDatetime(), req.getRemark());
        }
    }

    @Override
    @Transactional
    public void startProject(String code, String approver, String approveNote) {
        Project data = projectBO.getProject(code);

        if (!EProjectStatus.To_Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该项目未处于待开工状态！");
        }

        Department departmentCondition = new Department();
        departmentCondition.setProjectCode(code);
        List<Department> departmentsList = departmentBO
            .queryDepartmentList(departmentCondition);
        if (CollectionUtils.isEmpty(departmentsList)) {
            throw new BizException("xn000", "请编辑施工单位后再开工！");
        }

        // 添加统计信息
        Report report = reportBO.getReportByProject(code);
        if (null == report) {
            reportBO.saveReport(data.getCode(), data.getName());
        }

        // 项目开工
        projectBO.startProject(code, approver, approveNote);
    }

    @Override
    public void pauseProject(String code, String updater, String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该项目未处于可停工状态！");
        }

        projectBO.pauseProject(code, updater, remark);
    }

    @Override
    public void restartProject(String code, String updater, String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Pause.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该项目未处于可重新开工状态！");
        }

        projectBO.restartProject(code, updater, remark);
    }

    @Override
    @Transactional
    public void endProject(String code, String updater, String remark) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该工程项目未处于在建状态！");
        }

        // 项目结束
        projectBO.endProject(code, updater, remark);

        // 员工离职
        Employ condition = new Employ();
        condition.setProjectCode(data.getCode());
        condition.setStatus(EEmployStatus.Not_Leave.getCode());
        List<Employ> list = employBO.queryEmployList(condition);

        Date leavingDatetime = new Date();
        for (Employ employ : list) {
            employ.setStatus(EEmployStatus.Leave.getCode());
            employ.setLeavingDatetime(leavingDatetime);
            employBO.updateStatus(employ);
        }
    }

    @Override
    public void editSalaryDelayDays(String code, Integer salaryDelayDays) {
        Project data = projectBO.getProject(code);
        if (!EProjectStatus.Building.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该项目未处于在建状态，无法设置薪资发放可延迟天数！");
        }

        projectBO.editSalaryDelayDays(code, salaryDelayDays);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {
        Paginable<Project> page = projectBO.getPaginable(start, limit,
            condition);
        for (Project project : page.getList()) {
            initProject(project);
        }
        return page;
    }

    @Override
    public Project getProject(String code) {
        Project data = projectBO.getProject(code);

        // 项目账户
        ProjectCard projectCard = projectCardBO
            .getProjectCardByProject(data.getCode());
        data.setProjectCard(projectCard);

        // 报表信息
        Report report = reportBO.getReportByProject(data.getCode());
        data.setReport(report);

        initProject(data);
        return data;
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectBO.queryProject(condition);
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

    private void initProject(Project project) {
        // 审核人
        project.setApproveName(getName(project.getApprover()));

        // 更新人
        project.setUpdateName(getName(project.getUpdater()));
    }
}
