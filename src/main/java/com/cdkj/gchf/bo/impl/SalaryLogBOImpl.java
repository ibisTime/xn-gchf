package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ISalaryLogDAO;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.ESalaryLogType;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Component
public class SalaryLogBOImpl extends PaginableBOImpl<SalaryLog>
        implements ISalaryLogBO {

    @Autowired
    private ISalaryLogDAO salaryLogDAO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String saveSalaryLog(String salaryCode, String staffCode,
            String type, String handler, String handleNote, String HandlePic) {
        SalaryLog data = new SalaryLog();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SalaryLog.getCode());
        data.setCode(code);
        data.setSalaryCode(salaryCode);
        data.setStaffCode(staffCode);
        data.setType(ESalaryLogType.Abnormal.getCode());

        data.setHandler(handler);
        data.setHandleDatetime(new Date());
        data.setHandleNote(handleNote);
        data.setHandlePic(HandlePic);

        salaryLogDAO.insert(data);

        return code;
    }

    @Override
    public List<SalaryLog> querySalaryLogList(SalaryLog condition) {
        List<SalaryLog> list = salaryLogDAO.selectList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (SalaryLog salaryLog : list) {
                salaryLog.setHandleName(getName(salaryLog.getHandler()));
            }
        }

        return list;
    }

    @Override
    public SalaryLog getSalaryLog(String code) {
        SalaryLog data = null;
        if (StringUtils.isNotBlank(code)) {
            SalaryLog condition = new SalaryLog();
            condition.setCode(code);
            data = salaryLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "工资条日志不存在");
            }
            data.setHandleName(getName(data.getHandler()));
        }
        return data;
    }

    @Override
    public SalaryLog getLastSalaryLog(String salaryCode) {
        SalaryLog data = null;
        if (StringUtils.isNotBlank(salaryCode)) {
            SalaryLog condition = new SalaryLog();
            condition.setSalaryCode(salaryCode);
            condition.setHandler("admin");
            data = salaryLogDAO.selectLastSalaryLog(condition);
            if (null != data) {
                data.setHandleName(getName(data.getHandler()));
            }
        }
        return data;
    }

    private String getName(String userId) {
        String name = null;
        if (EUser.ADMIN.getCode().equals(userId)) {
            name = EUser.getValue(userId);
        } else {
            User user = userBO.getUserName(userId);
            if (user != null) {
                name = user.getRealName();
            }
        }

        return name;
    }
}
