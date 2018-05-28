package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.AbnormalRemind;
import com.cdkj.gchf.dto.req.XN631510Req;
import com.cdkj.gchf.dto.req.XN631512Req;

public interface IAbnormalRemindAO {

    String DEFAULT_ORDER_COLUMN = "code";

    String addAbnormalRemin(XN631510Req req);

    void dropAbnormalRemin(String code);

    void editAbnormalRemin(XN631512Req req);

    Paginable<AbnormalRemind> queryAbnormalRemindPage(int start, int limit,
            AbnormalRemind condition);

    List<AbnormalRemind> queryAbnormalRemindList(AbnormalRemind condition);

    AbnormalRemind getAbnormalRemind(String code);

}
