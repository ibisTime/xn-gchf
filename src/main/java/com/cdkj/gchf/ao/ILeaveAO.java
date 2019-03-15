package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Leave;
import com.cdkj.gchf.dto.req.XN631461Req;

/**
 * 请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午2:04:35 
 * @history:
 */
@Component
public interface ILeaveAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addLeave(XN631461Req req);

    public Paginable<Leave> queryLeavePage(int start, int limit,
            Leave condition);

    public List<Leave> queryLeaveList(Leave condition);

    public Leave getLeave(String code);

}
