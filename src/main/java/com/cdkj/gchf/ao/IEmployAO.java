package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.dto.req.XN631460Req;
import com.cdkj.gchf.dto.req.XN631461Req;
import com.cdkj.gchf.dto.req.XN631462Req;

@Component
public interface IEmployAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 入职
    public String joinIn(XN631460Req req);

    public int dropEmploy(String code);

    public void editEmploy(Employ data);

    public Paginable<Employ> queryEmployPage(int start, int limit,
            Employ condition);

    public List<Employ> queryEmployList(Employ condition);

    public Employ getEmploy(String code);

    // 请假
    public void toHoliday(XN631461Req req);

    // 离职
    public void leaveOffice(XN631462Req req);

}
