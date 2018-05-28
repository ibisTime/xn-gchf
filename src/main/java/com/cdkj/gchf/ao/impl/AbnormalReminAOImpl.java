package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IAbnormalRemindAO;
import com.cdkj.gchf.bo.IAbnormalRemindBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.AbnormalRemind;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631510Req;
import com.cdkj.gchf.dto.req.XN631512Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EUser;

@Service
public class AbnormalReminAOImpl implements IAbnormalRemindAO {

    @Autowired
    IAbnormalRemindBO abnormalRemindBO;

    @Autowired
    IUserBO userBO;

    @Override
    public String addAbnormalRemin(XN631510Req req) {
        AbnormalRemind data = new AbnormalRemind();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AbnormalRemind.getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setName(req.getName());

        data.setMobile(req.getMobile());
        data.setUpdater(req.getUpdater());
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());

        abnormalRemindBO.saveAbnormalRemin(data);
        return code;
    }

    @Override
    public void dropAbnormalRemin(String code) {
        AbnormalRemind data = abnormalRemindBO.getAbnormalRemind(code);
        abnormalRemindBO.removeAbnormalRemind(data);
    }

    @Override
    public void editAbnormalRemin(XN631512Req req) {
        AbnormalRemind data = abnormalRemindBO.getAbnormalRemind(req.getCode());
        data.setName(req.getName());
        data.setMobile(req.getMobile());
        data.setUpdater(req.getUpdater());
        Date date = new Date();

        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        abnormalRemindBO.refreshAbnormalRemind(data);
    }

    @Override
    public Paginable<AbnormalRemind> queryAbnormalRemindPage(int start,
            int limit, AbnormalRemind condition) {
        Paginable<AbnormalRemind> page = abnormalRemindBO.getPaginable(start,
            limit, condition);
        for (AbnormalRemind data : page.getList()) {
            data.setUpdateName(getName(data.getUpdater()));
        }
        return page;
    }

    @Override
    public List<AbnormalRemind> queryAbnormalRemindList(
            AbnormalRemind condition) {
        List<AbnormalRemind> list = abnormalRemindBO
            .queryAbnormalRemindList(condition);
        for (AbnormalRemind data : list) {
            data.setUpdateName(getName(data.getUpdater()));
        }
        return list;
    }

    @Override
    public AbnormalRemind getAbnormalRemind(String code) {
        return abnormalRemindBO.getAbnormalRemind(code);
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;

    }

}
