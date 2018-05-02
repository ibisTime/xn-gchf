package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.dao.IEmployDAO;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.enums.EStaffStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class EmployBOImpl extends PaginableBOImpl<Employ> implements IEmployBO {

    @Autowired
    private IEmployDAO employDAO;

    public void saveEmploy(Employ data) {
        employDAO.insert(data);
    }

    @Override
    public void toHoliday(Employ data) {
        employDAO.toHoliday(data);
    }

    @Override
    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark) {
        data.setLeavingDatetime(DateUtil.strToDate(leavingDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setStatus(EStaffStatus.Leave.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        employDAO.leaveOffice(data);
    }

    @Override
    public int removeEmploy(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Employ data = new Employ();
            data.setCode(code);
            count = employDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshEmploy(Employ data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = employDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Employ> queryEmployList(Employ condition) {
        return employDAO.selectList(condition);
    }

    @Override
    public Employ getEmploy(String code) {
        Employ data = null;
        if (StringUtils.isNotBlank(code)) {
            Employ condition = new Employ();
            condition.setCode(code);
            data = employDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该雇佣信息不存在");
            }
        }
        return data;
    }

}
