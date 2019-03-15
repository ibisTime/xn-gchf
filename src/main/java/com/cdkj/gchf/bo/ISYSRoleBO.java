package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.SYSRole;

/**
 * 
 * @author: Gejin 
 * @since: 2016年4月16日 下午9:32:33 
 * @history:
 */
public interface ISYSRoleBO extends IPaginableBO<SYSRole> {
    public boolean isSYSRoleExist(String code);

    public int saveSYSRole(SYSRole data);

    public int removeSYSRole(String code);

    public int refreshSYSRole(SYSRole data);

    public SYSRole getSYSRole(String code);

    public List<SYSRole> querySYSRoleList(SYSRole data);

}
