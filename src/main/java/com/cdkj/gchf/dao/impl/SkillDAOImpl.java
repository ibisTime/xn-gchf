package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ISkillDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Skill;

@Repository("skillDAOImpl")
public class SkillDAOImpl extends AMybatisTemplate implements ISkillDAO {

    @Override
    public int insert(Skill data) {
        return super.insert(NAMESPACE.concat("insert_skill"), data);
    }

    @Override
    public int delete(Skill data) {
        return super.delete(NAMESPACE.concat("delete_skill"), data);
    }

    @Override
    public int deleteByStaff(Skill data) {
        return super.delete(NAMESPACE.concat("delete_skillByStaff"), data);
    }

    @Override
    public Skill select(Skill condition) {
        return super.select(NAMESPACE.concat("select_skill"), condition,
            Skill.class);
    }

    @Override
    public long selectTotalCount(Skill condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_skill_count"),
            condition);
    }

    @Override
    public List<Skill> selectList(Skill condition) {
        return super.selectList(NAMESPACE.concat("select_skill"), condition,
            Skill.class);
    }

    @Override
    public List<Skill> selectList(Skill condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_skill"), start, limit,
            condition, Skill.class);
    }

    @Override
    public void update(Skill data) {
        super.update(NAMESPACE.concat("update_skill"), data);
    }

}
