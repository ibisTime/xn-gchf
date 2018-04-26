package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EUserStatus;

public interface IUserBO extends IPaginableBO<User> {

    public void refreshStatus(String userId, EUserStatus status, String updater,
            String remark);

    public void refreshMobile(User user, String mobile, String updater,
            String remark);

    public void refreshRole(String userId, String roleCode, String updater,
            String remark);

    public void resetAdminLoginPwd(User user, String loginPwd);

    public void isMobileExist(String mobile);

    public boolean isUserExist(String code);

    public void saveUser(User data);

    public List<User> queryUserList(User condition);

    public User getUser(String userId);

    public void refreshLoginPwd(User user, String newLoginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    public void refreshLoginPwd(User data, String loginPwd, String updater,
            String remark);

    public void refreshPhoto(String userId, String photo);

}
