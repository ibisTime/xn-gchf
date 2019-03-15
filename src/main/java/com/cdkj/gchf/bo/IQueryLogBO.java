package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.QueryLog;
import com.cdkj.gchf.domain.Staff;

public interface IQueryLogBO extends IPaginableBO<QueryLog> {

    String save(String userId, Staff staffCode);

    QueryLog getQueryLog(String code);

    void removeQueryLog(QueryLog data);

    List<QueryLog> queryQueryLogList(QueryLog condition);

    QueryLog isExist(String userId, String staffCode);
}
