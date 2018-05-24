package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISkillAO;
import com.cdkj.gchf.bo.ISkillBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Skill;

@Service
public class SkillAOImpl implements ISkillAO {

    @Autowired
    ISkillBO skillBO;

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
