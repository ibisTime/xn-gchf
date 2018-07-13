package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.dto.req.XN631500Req;
import com.cdkj.gchf.dto.req.XN631502Req;

public interface ISkillBO extends IPaginableBO<Skill> {
    // 添加技能
    public String saveSkill(String staffCode, String staffName,
            XN631500Req req);

    // 修改技能
    public void refreshSkill(XN631502Req req);

    // 删除技能
    public void dropSkill(String code);

    // 根据员工删除技能
    public void dropSkillByStaff(String staffCode);

    List<Skill> querySkillList(Skill condition);

    Skill getSkill(String code);

    List<Skill> querySkillByStaff(String code);

}
