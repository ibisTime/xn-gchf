package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.dto.req.XN631411Req;
import com.cdkj.gchf.dto.req.XN631412Req;
import com.cdkj.gchf.dto.req.XN631413Req;

@Component
public interface IStaffAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addStaff(XN631410Req req);

    public void editStaff(XN631412Req req);

    public Paginable<Staff> queryStaffPage(int start, int limit,
            Staff condition);

    public List<Staff> queryStaffList(Staff condition);

    public Staff getStaff(String code);

    // 获取特征值
    public String getStaffFeatList();

    // 员工建档
    public String addStaff(XN631411Req req);

    // 补全信息
    public void addStaffInfo(XN631413Req req);

    // 获取员工所有信息
    public Staff getStaffInfo(String code, List<String> projetCodeList);

    // 根据身份证号获取员工信息
    public Staff getStaffByIdNo(String idNo, List<String> projetCodeList);

}
