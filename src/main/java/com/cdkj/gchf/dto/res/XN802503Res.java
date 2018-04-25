/**
 * @Title XN802503Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月22日 下午1:03:23 
 * @version V1.0   
 */
package com.cdkj.gchf.dto.res;

import java.util.List;

import com.cdkj.gchf.domain.Account;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月22日 下午1:03:23 
 * @history:
 */
public class XN802503Res {

    // 账户列表
    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

}
