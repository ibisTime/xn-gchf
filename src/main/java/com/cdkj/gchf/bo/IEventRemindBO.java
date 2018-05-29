package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EventRemind;

public interface IEventRemindBO extends IPaginableBO<EventRemind> {

    void saveEventRemind(EventRemind data);

    EventRemind getEventRemind(String code);

    void removeEventRemind(EventRemind data);

    void refreshEventRemind(EventRemind data);

    List<EventRemind> queryEventRemindList(EventRemind condition);

}
