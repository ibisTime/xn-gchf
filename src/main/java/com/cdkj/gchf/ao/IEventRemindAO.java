package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EventRemind;
import com.cdkj.gchf.dto.req.XN631510Req;
import com.cdkj.gchf.dto.req.XN631512Req;

public interface IEventRemindAO {

    String DEFAULT_ORDER_COLUMN = "code";

    String addEventRemind(XN631510Req req);

    void dropEventRemind(String code);

    void editEventRemind(XN631512Req req);

    Paginable<EventRemind> queryEventRemindPage(int start, int limit,
            EventRemind condition);

    List<EventRemind> queryEventRemindList(EventRemind condition);

    EventRemind getEventRemind(String code);

}
