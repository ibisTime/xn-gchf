package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICUserBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.common.PwdUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ICUserDAO;
import com.cdkj.gchf.domain.CUser;
import com.cdkj.gchf.enums.EGeneratePrefix;

@Component
public class CUserBOImpl extends PaginableBOImpl<CUser> implements ICUserBO {

    @Autowired
    private ICUserDAO cuserDAO;

    // @Override public void isMobileExist(String mobile) { if
    // (StringUtils.isNotBlank(mobile)) { // 判断格式 PhoneUtil.checkMobile(mobile);
    // CUser condition = new CUser(); condition.setMobile(mobile); long count =
    // getTotalCount(condition); if (count > 0) { throw new
    // BizException("li01003", "手机号已经存在"); } } }

    /*
     * @Override public void isNicknameExist(String nickname) { if
     * (StringUtils.isNotBlank(nickname)) { // 判断格式 CUser condition = new
     * CUser(); condition.setNickname(nickname); long count =
     * getTotalCount(condition); if (count > 0) { throw new
     * BizException("li01003", "昵称已经被使用"); } } }
     */

    /*
     * @Override public void isLoginNameExist(String loginName) { if
     * (StringUtils.isNotBlank(loginName)) { // 判断格式 CUser condition = new
     * CUser(); condition.setLoginName(loginName); long count =
     * getTotalCount(condition); if (count > 0) { throw new
     * BizException("li01003", "登录名已经存在"); } } }
     */

    // @Override
    // public void checkLoginPwd(String userId, String loginPwd) {
    // if (StringUtils.isNotBlank(userId)
    // && StringUtils.isNotBlank(loginPwd)) {
    // CUser condition = new CUser();
    // condition.setUserId(userId);
    // condition.setLoginPwd(MD5Util.md5(loginPwd));
    // long count = this.getTotalCount(condition);
    // if (count != 1) {
    // throw new BizException("jd00001", "原登录密码错误");
    // }
    // } else {
    // throw new BizException("jd00001", "原登录密码错误");
    // }
    // }

    /*
     * @Override public void checkUserReferee(String userReferee, String
     * systemCode) { if (StringUtils.isNotBlank(userReferee)) { // 判断格式 CUser
     * condition = new CUser(); condition.setUserId(userReferee); long count =
     * getTotalCount(condition); if (count <= 0) { throw new
     * BizException("li01003", "推荐人不存在"); } } }
     */

    @Override
    public List<CUser> queryUserList(CUser condition) {
        return cuserDAO.selectList(condition);
    }

    @Override
    public String getUserId(String mobile) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile)) {
            CUser condition = new CUser();
            condition.setMobile(mobile);
            // List<CUser> list = cuserDAO.selectList(condition);
            // if (CollectionUtils.isNotEmpty(list)) {
            // CUser data = list.get(0);
            // userId = data.getUserId();
            // } else
            // throw new BizException("xn702002", "手机号[" + mobile + "]用户不存在");
            userId = condition.getUserId();
        }
        return userId;
    }

    @Override
    public CUser getUser(String mobile) {
        CUser cuser = null;
        if (StringUtils.isNotBlank(mobile)) {
            CUser condition = new CUser();
            condition.setMobile(mobile);
            List<CUser> list = cuserDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                cuser = list.get(0);
            }
        }
        return cuser;
    }

    @Override
    public String saveUser(String mobile) {
        String cuserId = null;
        if (StringUtils.isNotBlank(mobile)) {
            cuserId = OrderNoGenerater.generate("U");
            CUser data = new CUser();
            data.setUserId(cuserId);
            data.setMobile(mobile);
            cuserDAO.insert(data);
        }
        return cuserId;
    }

    @Override
    public String saveCNavigate(CUser data) {
        String userId = null;
        if (data != null) {
            if (data.getUserId() == null) {
                userId = OrderNoGenerater
                    .generate(EGeneratePrefix.DH.getCode());
                data.setUserId(userId);
            }
            cuserDAO.insert(data);
        }
        return userId;
    }

    @Override
    public String doRegister(String mobile, String loginPwd,
            String smsCaptcha) {
        String userId = OrderNoGenerater.generate("U");
        CUser user = new CUser();
        user.setUserId(userId);
        user.setLoginName(mobile);
        user.setMobile(mobile);

        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));

        cuserDAO.insert(user);
        return userId;
    }

}
