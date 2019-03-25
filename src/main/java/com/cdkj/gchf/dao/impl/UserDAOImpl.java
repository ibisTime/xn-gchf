package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IUserDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.User;

@Repository("userDAOImpl")
public class UserDAOImpl extends AMybatisTemplate implements IUserDAO {

    @Override
    public int insert(User data) {
        return super.insert(NAMESPACE.concat("insert_user"), data);
    }

    @Override
    public int delete(User data) {
        return super.delete(NAMESPACE.concat("delete_user"), data);
    }

    @Override
    public User select(User condition) {
        return super.select(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    @Override
    public User selectBriefUser(User data) {
        return super.select(NAMESPACE.concat("select_briefUser"), data,
            User.class);
    }

    @Override
    public long selectTotalCount(User condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_user_count"),
            condition);
    }

    @Override
    public List<User> selectList(User condition) {
        return super.selectList(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    @Override
    public List<User> selectList(User condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_user"), start, count,
            condition, User.class);
    }

    @Override
    public void updateStatus(User data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public void updateRole(User data) {
        super.update(NAMESPACE.concat("update_role"), data);
    }

    @Override
    public void updateLoginPwd(User data) {
        super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    @Override
    public void updateMobile(User data) {
        super.update(NAMESPACE.concat("update_mobile"), data);
    }

}
