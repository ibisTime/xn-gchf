package com.cdkj.gchf.dao;

import java.util.List;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.SYSMenu;
import com.cdkj.gchf.domain.SYSMenuRole;

/**
 * 角色菜单
 * @author: Gejin 
 * @since: 2016年4月16日 下午10:24:53 
 * @history:
 */
public interface ISYSMenuRoleDAO extends IBaseDAO<SYSMenuRole> {
    String NAMESPACE = ISYSMenuRoleDAO.class.getName().concat(".");

    public List<SYSMenu> selectSYSMenuList(SYSMenuRole data);

    public int deleteSYSMenuList(SYSMenuRole data);
}
