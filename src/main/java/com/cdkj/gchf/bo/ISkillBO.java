package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Skill;

public interface ISkillBO extends IPaginableBO<Skill> {

    void saveSkill(Skill data);

    void refreshSkill(Skill skill);

    List<Skill> querySalaryLogList(Skill condition);

    Skill getSalaryLog(String code);

}
