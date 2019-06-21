package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631600Req;
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

    public boolean checkMobile(String mobile);

    public boolean isUserExist(String code);

    public void saveUser(User data);

    // 添加业主端管理员
    public void saveProjectAdmin(String projectCode, XN631600Req req);

    public List<User> queryUserList(User condition);

    public User getUser(String userId);

    User getBriefUser(String userId);

    public List<User> getUserByOrganization(String organizationCode);

    public void refreshLoginPwd(User user, String newLoginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    public void refreshLoginPwd(User data, String loginPwd, String updater,
            String remark);

    public User getUserName(String userId);

    public List<User> checkLoginName(String loginName);

    /**
     * 根据手机号查询用户列表
     *
     * @param mobile 手机号
     */
    User getUserByMobile(String mobile);

}
