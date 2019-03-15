package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IEventRemindBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IEventRemindDAO;
import com.cdkj.gchf.domain.EventRemind;
import com.cdkj.gchf.exception.BizException;

@Component
public class EventRemindBOImpl extends PaginableBOImpl<EventRemind>
        implements IEventRemindBO {

    @Autowired
    IEventRemindDAO eventRemindDAO;

    @Override
    public void saveEventRemind(EventRemind data) {
        eventRemindDAO.insert(data);
    }

    @Override
    public EventRemind getEventRemind(String code) {
        EventRemind data = null;
        if (StringUtils.isNotBlank(code)) {
            EventRemind condition = new EventRemind();
            condition.setCode(code);
            data = eventRemindDAO.select(condition);
            if (data == null) {
                throw new BizException("xn00000", "该事件通知人不存在");
            }
        }
        return data;
    }

    @Override
    public void removeEventRemind(String code) {
        EventRemind data = getEventRemind(code);
        eventRemindDAO.delete(data);
    }

    @Override
    public void refreshEventRemind(EventRemind data) {
        eventRemindDAO.update(data);
    }

    @Override
    public List<EventRemind> queryEventRemindList(EventRemind condition) {
        return eventRemindDAO.selectList(condition);
    }

    @Override
    public List<EventRemind> queryEventRemindList(String organizationCode) {
        List<EventRemind> eventRemindsList = null;
        if (StringUtils.isNotBlank(organizationCode)) {
            EventRemind condition = new EventRemind();
            condition.setOrganizationCode(organizationCode);
            eventRemindsList = queryEventRemindList(condition);
        }
        return eventRemindsList;
    }

}
