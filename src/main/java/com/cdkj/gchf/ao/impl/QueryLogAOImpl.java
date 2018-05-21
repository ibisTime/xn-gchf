package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IQueryLogAO;
import com.cdkj.gchf.bo.IQueryLogBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.QueryLog;
import com.cdkj.gchf.domain.Staff;

@Service
public class QueryLogAOImpl implements IQueryLogAO {

    @Autowired
    IQueryLogBO queryLogBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IStaffBO staffBO;

    @Override
    public String addQueryLog(String userId, String staffCode) {
        userBO.getUser(userId);
        Staff staff = staffBO.getStaff(staffCode);
        QueryLog data = queryLogBO.isExist(userId, staffCode);
        if (data != null) {
            return data.getCode();
        }
        return queryLogBO.save(userId, staff);
    }

    @Override
    public void dropQueryLog(List<String> codeList) {
        for (String code : codeList) {
            QueryLog data = queryLogBO.getQueryLog(code);
            queryLogBO.removeQueryLog(data);
        }
    }

    @Override
    public Paginable<QueryLog> queryQueryLogPage(int start, int limit,
            QueryLog condition) {
        return queryLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<QueryLog> queryQueryLogList(QueryLog condition) {
        return queryLogBO.queryQueryLogList(condition);
    }

    @Override
    public QueryLog getQueryLog(String code) {
        return queryLogBO.getQueryLog(code);
    }

}
