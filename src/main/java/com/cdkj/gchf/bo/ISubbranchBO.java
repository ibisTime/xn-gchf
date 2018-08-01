package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Subbranch;

/**
 * 银行组织
 * @author: silver 
 * @since: 2018年6月25日 下午7:12:57 
 * @history:
 */
public interface ISubbranchBO extends IPaginableBO<Subbranch> {

    // 添加银行组织
    public String saveSubbranch(String bankCode, String bankName,
            String subbranchName);

    public List<Subbranch> querySubbranchList(Subbranch condition);

    public Subbranch getSubbranch(String code);

    public Subbranch getSubbranch(String bankName, String subbranchName);

}
