package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Skill;

public interface ISkillAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Skill> querySalaryLogPage(int start, int limit,
            Skill condition);

    public List<Skill> querySalaryLogList(Skill condition);

    public Skill getSalaryLog(String code);
}
