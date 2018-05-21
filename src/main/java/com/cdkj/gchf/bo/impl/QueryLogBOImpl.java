package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IQueryLogBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IQueryLogDAO;
import com.cdkj.gchf.domain.QueryLog;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class QueryLogBOImpl extends PaginableBOImpl<QueryLog>
        implements IQueryLogBO {

    @Autowired
    IQueryLogDAO queryLogDAO;

    @Override
    public String save(String userId, Staff staff) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.QueryLog.getCode());
        QueryLog data = new QueryLog();
        data.setCode(code);
        data.setUserId(userId);
        data.setStaffCode(staff.getCode());
        data.setStaffName(staff.getName());

        queryLogDAO.insert(data);
        return code;
    }

    @Override
    public QueryLog getQueryLog(String code) {
        QueryLog data = null;
        if (StringUtils.isNotBlank(code)) {
            QueryLog condition = new QueryLog();
            condition.setCode(code);
            data = queryLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn00000", "记录不存在");
            }
        }
        return data;

    }

    @Override
    public void removeQueryLog(QueryLog data) {
        queryLogDAO.delete(data);
    }

    @Override
    public List<QueryLog> queryQueryLogList(QueryLog condition) {
        return queryLogDAO.selectList(condition);
    }

    @Override
    public QueryLog isExist(String userId, String staffCode) {
        QueryLog data = null;
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(staffCode)) {
            QueryLog condition = new QueryLog();
            condition.setUserId(userId);
            condition.setStaffCode(staffCode);
            data = queryLogDAO.select(condition);
        }
        return data;
    }

}
