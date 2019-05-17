package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631070Req;

/**
 * @author old3
 */
@Component
public interface IUserAO {

    static final String DEFAULT_ORDER_COLUMN = "user_id";

    // 用户登录
    public String doLogin(String type, String loginName, String loginPwd);

    // 注销/激活用户
    public void doCloseOpen(String userId, String updater, String remark);

    // 设置角色
    public void doRoleUser(String userId, String roleCode, String updater,
                           String remark);

    // 重置登录密码
    public void resetAdminLoginPwd(String userId, String newLoginPwd);

    // 检查手机号是否存在
    public void doCheckMobile(String mobile);

    // 根据手机号修改登录密码
    public void doResetLoginPwd(String mobile, String smsCaptcha,
                                String newLoginPwd);

    // 更换手机号
    public void doChangeMoblie(String userId, String newMobile, String updater,
                               String remark);

    // 修改登录密码
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
                                 String newLoginPwd);

    // 管理员重置用户密码
    public void doResetLoginPwdByOss(String userId, String newLoginPwd,
                                     String updater, String remark);

    public Paginable<User> queryUserPage(int start, int limit, User condition);

    public List<User> queryUserList(User condition);

    public User getUser(String code);

    public String doAddUser(XN631070Req req);

    // 校验登录名是否重复
    public boolean doCheckLoginName(String loginName);

}
