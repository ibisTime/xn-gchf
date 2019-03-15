package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.domain.SYSMenu;
import com.cdkj.gchf.domain.SYSMenuRole;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午10:37:44 
 * @history:
 */
public interface ISYSMenuRoleAO {

    String DEFAULT_ORDER_COLUMN = "code";

    public int addSYSMenuRole(SYSMenuRole data);

    public List<SYSMenu> querySYSMenuList(SYSMenuRole data);

}
