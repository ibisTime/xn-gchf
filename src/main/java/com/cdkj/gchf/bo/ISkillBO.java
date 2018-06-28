package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.dto.req.XN631413ReqSkill;

public interface ISkillBO extends IPaginableBO<Skill> {
    void saveSkill(String staffCode, String staffName, XN631413ReqSkill req);

    void refreshSkill(Skill skill);

    // 根据员工删除技能
    public void dropSkillByStaff(String staffCode);

    List<Skill> querySkillList(Skill condition);

    Skill getSkill(String code);

    List<Skill> querySkillByStaff(String code);

}
