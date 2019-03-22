package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.OperateLog;
import com.cdkj.gchf.domain.User;

public interface IOperateLogBO extends IPaginableBO<OperateLog> {

    public String saveOperateLog(String refType, String refCode, String operate,
            User operator, String remark);

    public List<OperateLog> queryOperateLogList(OperateLog condition);

    public OperateLog getOperateLog(String code);

}
