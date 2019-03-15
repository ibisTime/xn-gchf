package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.StaffLog;

@Component
public interface IStaffLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addStaffLog(StaffLog data);

    public Paginable<StaffLog> queryStaffLogPage(int start, int limit,
            StaffLog condition);

    public List<StaffLog> queryStaffLogList(StaffLog condition);

    public StaffLog getStaffLog(String code);

}
