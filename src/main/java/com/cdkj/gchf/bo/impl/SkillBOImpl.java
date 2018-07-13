package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISkillBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dao.ISkillDAO;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.dto.req.XN631500Req;
import com.cdkj.gchf.dto.req.XN631502Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class SkillBOImpl extends PaginableBOImpl<Skill> implements ISkillBO {

    @Autowired
    ISkillDAO skillDAO;

    @Override
    public String saveSkill(String staffCode, String staffName,
            XN631500Req req) {
        Skill skill = new Skill();
        String skillCode = OrderNoGenerater
            .generate(EGeneratePrefix.Skill.getCode());
        skill.setCode(skillCode);
        skill.setName(req.getName());
        skill.setStaffCode(staffCode);
        skill.setStaffName(staffName);
        if (null != req.getScore()) {
            skill.setScore(StringValidater.toInteger(req.getScore()));
        }

        skill.setPdf(req.getPdf());
        skillDAO.insert(skill);
        return skillCode;
    }

    @Override
    public void refreshSkill(XN631502Req req) {
        Skill data = new Skill();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setPdf(req.getPdf());
        if (null != req.getScore()) {
            data.setScore(StringValidater.toInteger(req.getScore()));
        }
        skillDAO.update(data);
    }

    @Override
    public void dropSkill(String code) {
        Skill skill = new Skill();
        skill.setCode(code);
        skillDAO.delete(skill);
    }

    @Override
    public void dropSkillByStaff(String staffCode) {
        Skill skill = new Skill();
        skill.setStaffCode(staffCode);
        skillDAO.deleteByStaff(skill);
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
