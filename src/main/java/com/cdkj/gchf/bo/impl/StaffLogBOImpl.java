package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IStaffLogBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IStaffLogDAO;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.StaffLog;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class StaffLogBOImpl extends PaginableBOImpl<StaffLog>
        implements IStaffLogBO {

    @Autowired
    private IStaffLogDAO staffLogDAO;

    public void saveStaffLog(Employ data, String staffName, String projectCode,
            String projectName) {
        StaffLog staffLog = new StaffLog();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.StaffLog.getCode());
        staffLog.setCode(code);
        staffLog.setProjectCode(projectCode);
        staffLog.setProjectName(projectName);

        staffLog.setStaffName(staffName);
        staffLog.setStaffCode(data.getStaffCode());
        staffLog.setPosition(data.getPosition());
        staffLog.setJoinDatetime(data.getJoinDatetime());
        staffLog.setLeavingDatetime(data.getLeavingDatetime());
        staffLogDAO.insert(staffLog);
    }

    @Override
    public List<StaffLog> queryStaffLogList(StaffLog condition) {
        return staffLogDAO.selectList(condition);
    }

    @Override
    public StaffLog getStaffLog(String code) {
        StaffLog data = null;
        if (StringUtils.isNotBlank(code)) {
            StaffLog condition = new StaffLog();
            condition.setCode(code);
            data = staffLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "务工人员记录不存在");
            }
        }
        return data;
    }
}
