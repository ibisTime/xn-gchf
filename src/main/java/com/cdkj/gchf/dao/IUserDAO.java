package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.User;

public interface IUserDAO extends IBaseDAO<User> {

    String NAMESPACE = IUserDAO.class.getName().concat(".");

    // 更新状态
    public void updateStatus(User data);

    // 更新角色
    public void updateRole(User data);

    // 设置登录密码
    public void updateLoginPwd(User data);

    // 更新手机号
    public void updateMobile(User data);

    // 更新头像
    public void updatePhoto(User data);
}
