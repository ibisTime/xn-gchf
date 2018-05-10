package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.StaffLog;

public interface IStaffLogBO extends IPaginableBO<StaffLog> {

    public void saveStaffLog(Employ data, String staffName, String companyCode,
            String companyName, String projectCode, String projectName);

    public List<StaffLog> queryStaffLogList(StaffLog condition);

    public StaffLog getStaffLog(String code);

}
