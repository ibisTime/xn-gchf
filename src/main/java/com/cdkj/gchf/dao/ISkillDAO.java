package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Skill;

public interface ISkillDAO extends IBaseDAO<Skill> {

    String NAMESPACE = ISkillDAO.class.getName().concat(".");

    void update(Skill data);
}
