package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISkillBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ISkillDAO;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.exception.BizException;

@Component
public class SkillBOImpl extends PaginableBOImpl<Skill> implements ISkillBO {

    @Autowired
    ISkillDAO skillDAO;

    @Override
    public void saveSkill(Skill data) {
        skillDAO.insert(data);
    }

    @Override
    public void refreshSkill(Skill data) {
        skillDAO.update(data);
    }

    @Override
    public List<Skill> querySkillList(Skill condition) {
        return skillDAO.selectList(condition);
    }

    @Override
    public Skill getSkill(String code) {
        Skill data = null;
        if (StringUtils.isNotBlank(code)) {
            Skill condition = new Skill();
            condition.setCode(code);
            data = skillDAO.select(condition);
            if (data == null) {
                throw new BizException("xn00000", "技能不存在");
            }
        }
        return data;
    }

    @Override
    public List<Skill> querySkillByStaff(String staffCode) {
        Skill condition = new Skill();
        condition.setStaffCode(staffCode);
        return skillDAO.selectList(condition);
    }

}
