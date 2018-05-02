package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.dto.req.XN631412Req;

@Component
public interface IStaffAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addStaff(XN631410Req req);

    public void dropStaff(String code);

    public void editStaff(XN631412Req req);

    public Paginable<Staff> queryStaffPage(int start, int limit,
            Staff condition);

    public List<Staff> queryStaffList(Staff condition);

    public Staff getStaff(String code);

}
