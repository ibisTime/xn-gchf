package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IOperatorGuideBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IOperatorGuideDAO;
import com.cdkj.gchf.domain.OperatorGuide;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

/**
 * 操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午4:50:55 
 * @history:
 */
@Component
public class OperatorGuideBOImpl extends PaginableBOImpl<OperatorGuide>
        implements IOperatorGuideBO {

    @Autowired
    private IOperatorGuideDAO operatorGuideDAO;

    @Override
    public String saveOperatorGuide(OperatorGuide data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.OperatorGuide.getCode());
            data.setCode(code);
            operatorGuideDAO.insert(data);
        }
        return code;
    }

    @Override
    public void removeOperatorGuide(String code) {
        if (StringUtils.isNotBlank(code)) {
            OperatorGuide data = new OperatorGuide();
            data.setCode(code);
            operatorGuideDAO.delete(data);
        }
    }

    @Override
    public void refreshOperatorGuide(OperatorGuide data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            operatorGuideDAO.update(data);
        }
    }

    @Override
    public List<OperatorGuide> queryOperatorGuideList(OperatorGuide condition) {
        return operatorGuideDAO.selectList(condition);
    }

    @Override
    public OperatorGuide getOperatorGuide(String code) {
        OperatorGuide data = null;
        if (StringUtils.isNotBlank(code)) {
            OperatorGuide condition = new OperatorGuide();
            condition.setCode(code);
            data = operatorGuideDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "操作指南不存在！");
            }
        }
        return data;
    }
}
