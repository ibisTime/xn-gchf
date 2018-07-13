package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.dto.req.XN631500Req;
import com.cdkj.gchf.dto.req.XN631502Req;

public interface ISkillAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加技能
    public String saveSkill(XN631500Req req);

    // 编辑技能
    public void editSkill(XN631502Req req);

    // 删除技能
    public void dropSkill(String code);

    public Paginable<Skill> querySalaryLogPage(int start, int limit,
            Skill condition);

    public List<Skill> querySalaryLogList(Skill condition);

    public Skill getSalaryLog(String code);
}
