package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.ICUserAO;
import com.cdkj.gchf.bo.ICUserBO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.domain.CUser;
import com.cdkj.gchf.dto.req.XN630200Req;
import com.cdkj.gchf.enums.EUserStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class CUserAOImpl implements ICUserAO {

    @Autowired
    private ICUserBO cuserBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Override
    public XN630200Req doRegister(String mobile, String loginPwd,
            String smsCaptcha) {
        String userId = cuserBO.doRegister(mobile, loginPwd, smsCaptcha);
        return new XN630200Req(userId);
    }

    // 验证手机号
    /*
     * @Override public void doCheckMobile(String mobile) {
     * cuserBO.isMobileExist(mobile); }
     */

    @Override
    public String doCaptchaLoginReg(String mobile, String smsCaptcha) {
        // 短信验证码是否正确
        // smsOutBO.checkCaptcha(mobile, smsCaptcha, "630200", null);
        String userId = cuserBO.getUserId(mobile);
        if (StringUtils.isNotBlank(userId)) {
            throw new BizException("mag", "手机号已存在，请重新输入！！！");
        }
        userId = cuserBO.saveUser(mobile);
        return userId;
    }

    // 用户登录
    @Override
    @Transactional
    public String doLogin(String loginName, String loginPwd) {
        CUser condition = new CUser();
        // if (EUserKind.Customer.getCode().equals(kind)
        // || EUserKind.Merchant.getCode().equals(kind)) {
        // condition.setLoginName(loginName);
        // condition.setLoginType(ELoginType.MOBILE.getCode());
        // } else {
        condition.setLoginName(loginName);
        // }
        List<CUser> userList1 = cuserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<CUser> userList2 = cuserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        CUser cuser = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(cuser.getStatus())) {
            throw new BizException("xn805050",
                "该账号" + EUserStatus.getMap().get(cuser.getStatus()).getValue()
                        + "，请联系工作人员");
        }
        // addLoginAmount(cuser);
        // cuserBO.refreshLastLogin(cuser.getUserId());
        return cuser.getUserId();
    }

    // 登录名密码验证
    // @Override
    // public void doCheckLoginPwd(String loginName, String loginPwd) {
    // CUser condition = new CUser();
    // condition.setLoginName(loginName);
    // List<CUser> userList1 = cuserBO.queryUserList(condition);
    // if (CollectionUtils.isEmpty(userList1)) {
    // throw new BizException("xn702002", "用户不存在");
    // }
    // condition.setLoginPwd(MD5Util.md5(loginPwd));
    // List<CUser> userList2 = cuserBO.queryUserList(condition);
    // if (CollectionUtils.isEmpty(userList2)) {
    // throw new BizException("xn702002", "登录密码错误");
    // }
    //
    // }

    @Override
    public String insertCUser(CUser data) {
        return cuserBO.saveCNavigate(data);
    }

    @Override
    public Paginable<CUser> queryCUserPage(int start, int limit,
            CUser condition) {
        return cuserBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CUser> queryCUserList(CUser condition) {
        return cuserBO.queryUserList(condition);
    }

    @Override
    public CUser getCUser(String userId) {
        return cuserBO.getUser(userId);
    }

}
