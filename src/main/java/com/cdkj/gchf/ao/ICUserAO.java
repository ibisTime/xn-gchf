package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CUser;
import com.cdkj.gchf.dto.req.XN630200Req;

public interface ICUserAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    // C端用户注册
    public XN630200Req doRegister(String mobile, String loginPwd,
            String smsCaptcha);

    // 检查手机号是否存在
    // public void doCheckMobile(String mobile);

    // 验证码注册用户
    public String doCaptchaLoginReg(String mobile, String smsCaptcha);

    // 用户登录
    public String doLogin(String loginName, String loginPwd);

    // 检查登录名密码是否正确
    // public void doCheckLoginPwd(String loginName, String loginPwd);

    // 注册
    public String insertCUser(CUser data);

    // 分页查询
    public Paginable<CUser> queryCUserPage(int start, int limit,
            CUser condition);

    // 列表查询
    public List<CUser> queryCUserList(CUser condition);

    // 详情查询
    public CUser getCUser(String userId);

}
