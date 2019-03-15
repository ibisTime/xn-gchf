package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISubbranchBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ISubbranchDAO;
import com.cdkj.gchf.domain.Subbranch;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

/**
 * 支行表
 * @author: silver 
 * @since: 2018年6月25日 下午8:01:18 
 * @history:
 */
@Component
public class SubbranchBOImpl extends PaginableBOImpl<Subbranch>
        implements ISubbranchBO {
    @Autowired
    private ISubbranchDAO subbranchDAO;

    @Override
    public String saveSubbranch(String bankCode, String bankName,
            String subbranchName) {
        String code = null;
        if (StringUtils.isNotBlank(bankCode) && StringUtils.isNotBlank(bankName)
                && StringUtils.isNotBlank(subbranchName)) {
            Subbranch subbranch = new Subbranch();
            code = OrderNoGenerater
                .generate(EGeneratePrefix.SubBranch.getCode());
            subbranch.setCode(code);
            subbranch.setBankCode(bankCode);
            subbranch.setBankName(bankName);
            subbranch.setSubbranchName(subbranchName);
            subbranch.setUpdateDatetime(new Date());
            subbranchDAO.insert(subbranch);
        }
        return code;
    }

    @Override
    public List<Subbranch> querySubbranchList(Subbranch condition) {
        return subbranchDAO.selectList(condition);
    }

    @Override
    public Subbranch getSubbranch(String code) {
        Subbranch data = null;
        if (StringUtils.isNotBlank(code)) {
            Subbranch condition = new Subbranch();
            condition.setCode(code);
            data = subbranchDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "支行记录不存在！");
            }
        }
        return data;
    }

    @Override
    public Subbranch getSubbranch(String bankName, String subbranchName) {
        Subbranch data = null;
        if (StringUtils.isNotBlank(bankName)
                && StringUtils.isNotBlank(subbranchName)) {
            Subbranch condition = new Subbranch();
            condition.setBankName(bankName);
            condition.setSubbranchName(subbranchName);
            data = subbranchDAO.select(condition);
        }
        return data;
    }
}
