package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IEventRemindAO;
import com.cdkj.gchf.bo.IEventRemindBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.EventRemind;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631510Req;
import com.cdkj.gchf.dto.req.XN631512Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EUser;

@Service
public class EventRemindAOImpl implements IEventRemindAO {

    @Autowired
    IEventRemindBO eventRemindBO;

    @Autowired
    IUserBO userBO;

    @Override
    public String addEventRemind(XN631510Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        EventRemind data = new EventRemind();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.EventRemind.getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setName(req.getName());

        data.setMobile(req.getMobile());
        data.setUpdater(req.getUpdater());
        Date date = new Date();
        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());

        eventRemindBO.saveEventRemind(data);
        return code;
    }

    @Override
    public void dropEventRemind(String code) {
        EventRemind data = eventRemindBO.getEventRemind(code);
        eventRemindBO.removeEventRemind(data);
    }

    @Override
    public void editEventRemind(XN631512Req req) {
        EventRemind data = eventRemindBO.getEventRemind(req.getCode());
        PhoneUtil.checkMobile(req.getMobile());
        data.setName(req.getName());
        data.setMobile(req.getMobile());
        data.setUpdater(req.getUpdater());
        Date date = new Date();

        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        eventRemindBO.refreshEventRemind(data);
    }

    @Override
    public Paginable<EventRemind> queryEventRemindPage(int start, int limit,
            EventRemind condition) {
        Paginable<EventRemind> page = eventRemindBO.getPaginable(start, limit,
            condition);
        for (EventRemind data : page.getList()) {
            data.setUpdateName(getName(data.getUpdater()));
        }
        return page;
    }

    @Override
    public List<EventRemind> queryEventRemindList(EventRemind condition) {
        List<EventRemind> list = eventRemindBO.queryEventRemindList(condition);
        for (EventRemind data : list) {
            data.setUpdateName(getName(data.getUpdater()));
        }
        return list;
    }

    @Override
    public EventRemind getEventRemind(String code) {
        return eventRemindBO.getEventRemind(code);
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
