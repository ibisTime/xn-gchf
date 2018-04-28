package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IBcontractBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IBcontractDAO;
import com.cdkj.gchf.domain.Bcontract;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class BcontractBOImpl extends PaginableBOImpl<Bcontract>
        implements IBcontractBO {

    @Autowired
    private IBcontractDAO bcontractDAO;

    @Override
    public String saveBcontract(Bcontract data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.Bcontract.getCode());
            data.setCode(code);
            bcontractDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeBcontract(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Bcontract data = new Bcontract();
            data.setCode(code);
            count = bcontractDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshBcontract(Bcontract data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = bcontractDAO.updateBcontract(data);
        }
        return count;
    }

    @Override
    public List<Bcontract> queryBcontractList(Bcontract condition) {
        return bcontractDAO.selectList(condition);
    }

    @Override
    public Bcontract getBcontract(String code) {
        Bcontract data = null;
        if (StringUtils.isNotBlank(code)) {
            Bcontract condition = new Bcontract();
            condition.setCode(code);
            data = bcontractDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "合同不存在");
            }
        }
        return data;
    }
}
