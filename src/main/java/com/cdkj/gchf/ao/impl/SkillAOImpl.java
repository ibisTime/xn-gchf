package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISkillAO;
import com.cdkj.gchf.bo.ISkillBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631500Req;
import com.cdkj.gchf.dto.req.XN631502Req;

@Service
public class SkillAOImpl implements ISkillAO {
    @Autowired
    ISkillBO skillBO;

    @Autowired
    IStaffBO staffBO;

    @Override
    public String saveSkill(XN631500Req req) {
        Staff staff = staffBO.getStaffBrief(req.getStaffCode());
        return skillBO.saveSkill(staff.getCode(), staff.getName(), req);
    }

    @Override
    public void editSkill(XN631502Req req) {
        skillBO.refreshSkill(req);
    }

    @Override
    public void dropSkill(String code) {
        skillBO.dropSkill(code);
    }

    @Override
    public Paginable<Skill> querySalaryLogPage(int start, int limit,
            Skill condition) {
        return skillBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Skill> querySalaryLogList(Skill condition) {
        return skillBO.querySkillList(condition);
    }

    @Override
    public Skill getSalaryLog(String code) {
        return skillBO.getSkill(code);
    }

}
