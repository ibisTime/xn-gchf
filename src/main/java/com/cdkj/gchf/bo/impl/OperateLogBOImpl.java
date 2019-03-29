package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IOperateLogDAO;
import com.cdkj.gchf.domain.OperateLog;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
@Primary
public class OperateLogBOImpl extends PaginableBOImpl<OperateLog>
        implements IOperateLogBO {

    @Autowired
    private IOperateLogDAO operateLogDAO;

    @Override
    public String saveOperateLog(String refType, String refCode, String operate,
            User operator, String remark) {
        OperateLog data = new OperateLog();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.OperateLog.getCode());
        data.setCode(code);
        data.setRefType(refType);
        data.setRefCode(refCode);
        data.setOperate(operate);
        data.setOperator(operator.getUserId());

        data.setOperatorName(operator.getLoginName());
        data.setRemark(remark);
        data.setOperateDatetime(new Date());
        operateLogDAO.insert(data);

        return code;
    }

    @Override
    public void refreshRemark(String code, String remark) {
        OperateLog operateLog = new OperateLog();

        operateLog.setCode(code);
        operateLog.setRemark(remark);

        operateLogDAO.updateRemark(operateLog);
    }

    @Override
    public List<OperateLog> queryOperateLogList(OperateLog condition) {
        return operateLogDAO.selectList(condition);
    }

    @Override
    public OperateLog getOperateLog(String code) {
        OperateLog data = null;
        if (StringUtils.isNotBlank(code)) {
            OperateLog condition = new OperateLog();
            condition.setCode(code);
            data = operateLogDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "日志编号不存在");
            }
        }
        return data;
    }

}
