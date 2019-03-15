package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.QueryLog;

public interface IQueryLogAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    String addQueryLog(String userId, String idNo);

    Paginable<QueryLog> queryQueryLogPage(int start, int limit,
            QueryLog condition);

    List<QueryLog> queryQueryLogList(QueryLog condition);

    QueryLog getQueryLog(String code);

    void dropQueryLog(List<String> codeList);
}
