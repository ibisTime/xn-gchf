package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISubbranchAO;
import com.cdkj.gchf.bo.ISubbranchBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Subbranch;

/**
 * 支行表
 * @author: silver 
 * @since: 2018年6月25日 下午7:15:03 
 * @history:
 */
@Service
public class SubbranchAOImpl implements ISubbranchAO {

    @Autowired
    private ISubbranchBO subbranchBO;

    @Override
    public Paginable<Subbranch> querySubbranchPage(int start, int limit,
            Subbranch condition) {
        return subbranchBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Subbranch> querySubbranchList(Subbranch condition) {
        List<Subbranch> subbranchList = subbranchBO
            .querySubbranchList(condition);
        for (Subbranch subbranch : subbranchList) {
            subbranch.setBankSubbranchName(
                subbranch.getBankName().concat(subbranch.getSubbranchName()));
        }
        return subbranchList;
    }

    @Override
    public Subbranch getSubbranch(String code) {
        return subbranchBO.getSubbranch(code);
    }
}
