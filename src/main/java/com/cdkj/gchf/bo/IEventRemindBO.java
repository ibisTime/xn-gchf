package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EventRemind;

public interface IEventRemindBO extends IPaginableBO<EventRemind> {

    void saveEventRemind(EventRemind data);

    EventRemind getEventRemind(String code);

    void removeEventRemind(String code);

    void refreshEventRemind(EventRemind data);

    List<EventRemind> queryEventRemindList(EventRemind condition);

    List<EventRemind> queryEventRemindList(String organizationCode);

}
