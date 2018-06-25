package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Subbranch;

@Component
public interface ISubbranchAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Subbranch> querySubbranchPage(int start, int limit,
            Subbranch condition);

    public List<Subbranch> querySubbranchList(Subbranch condition);

    public Subbranch getSubbranch(String code);

}
