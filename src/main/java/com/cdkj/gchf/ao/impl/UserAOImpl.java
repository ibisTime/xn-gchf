package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.bo.ISYSRoleBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.common.PwdUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.SYSRole;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631070Req;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class UserAOImpl implements IUserAO {

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSRoleBO sysRoleBO;

    @Override
    public String doAddUser(XN631070Req req) {
        User data = new User();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setLoginName(req.getMobile());
        data.setMobile(req.getMobile());
        data.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        data.setLoginPwdStrength(
            PwdUtil.calculateSecurityLevel(req.getLoginPwd()));

        data.setCreateDatetime(new Date());
        data.setStatus(EUserStatus.NORMAL.getCode());
        data.setRemark(req.getRemark());
        userBO.saveUser(data);
        return userId;
    }

    @Override
    public String doLogin(String type, String loginName, String loginPwd) {
        User condition = new User();
        condition.setType(type);
        condition.setLoginName(loginName);
        condition.setLoginPwd(MD5Util.md5(loginPwd));

        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        User user = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050", "该用户操作存在异常");
        }
        return user.getUserId();
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 验证手机号
        userBO.isMobileExist(newMobile);
        // 短信验证码是否正确（往新手机号发送）
        // smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805061");
        userBO.refreshMobile(userId, newMobile);
        // 发送短信
        // smsOutBO.sendSmsOut(oldMobile,
        // "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
        // + DateUtil.dateToStr(new Date(),
        // DateUtil.DATA_TIME_PATTERN_1)
        // + "提交的更改绑定手机号码服务已审核通过，现绑定手机号码为" + newMobile
        // + "，请妥善保管您的账户相关信息。",
        // "805061", user.getCompanyCode(), user.getSystemCode());
    }

    @Override
    @Transactional
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd) {
        if (oldLoginPwd.equals(newLoginPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(userId, oldLoginPwd);
        // // 重置
        // userBO.refreshLoginPwd(userId, newLoginPwd);
        // 发送短信
        User user = userBO.getUser(userId);
        // if (!EUserKind.Plat.getCode().equals(user.getKind())) {
        // smsOutBO.sendSmsOut(user.getMobile(),
        // "尊敬的" + PhoneUtil.hideMobile(user.getMobile())
        // + "用户，您的登录密码修改成功。请妥善保管您的账户相关信息。",
        // "805064", user.getCompanyCode(), user.getSystemCode());
        // }
    }

    @Override
    @Transactional
    public void doResetLoginPwdByOss(String userId, String newLoginPwd,
            String updater, String remark) {
        User data = userBO.getUser(userId);
        if (data.getLoginPwd().equals(MD5Util.md5(newLoginPwd))) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }
        userBO.refreshLoginPwd(data, newLoginPwd, updater, remark);
    }

    @Override
    public void doModifyPhoto(String userId, String photo) {
        userBO.refreshPhoto(userId, photo);
    }

    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(user.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = EUserStatus.NORMAL;
        }
        // userBO.refreshStatus(userId, userStatus, updater, remark);
        // if (!EUserKind.Plat.getCode().equals(user.getKind())
        // && PhoneUtil.isMobile(mobile)) {
        // // 发送短信
        // smsOutBO.sendSmsOut(mobile,
        // "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "631075");
        // }
    }

    @Override
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        userBO.refreshRole(userId, roleCode, updater, remark);
    }

    @Override
    public void resetAdminLoginPwd(String userId, String newLoginPwd) {
        User user = userBO.getUser(userId);
        userBO.resetAdminLoginPwd(user, newLoginPwd);
    }

    @Override
    @Transactional
    public void doResetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd) {
        User user = userBO.getUser(mobile);
        if (StringUtils.isBlank(user.getUserId())) {
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        if (EUserStatus.Li_Locked.getCode().equals(user.getStatus())
                || EUserStatus.Ren_Locked.getCode().equals(user.getStatus())
                || EUserStatus.TO_APPROVE.getCode().equals(user.getStatus())
                || EUserStatus.APPROVE_NO.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050",
                "该账号" + EUserStatus.getMap().get(user.getStatus()).getValue()
                        + "，请联系工作人员");
        }
        // 短信验证码是否正确
        // smsOutBO.checkCaptcha(mobile, smsCaptcha, "805063");
        userBO.refreshLoginPwd(user, newLoginPwd);
        // // 发送短信
        // smsOutBO.sendSmsOut(mobile,
        // "尊敬的" + PhoneUtil.hideMobile(mobile)
        // + "用户，您的登录密码重置成功。请妥善保管您的账户相关信息。",
        // "805063", companyCode, systemCode);
    }

    @Override
    public void doCheckMobile(String mobile) {
        userBO.isMobileExist(mobile);
    }

    @Override
    public Paginable<User> queryUserPage(int start, int limit, User condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeEnd()
                    .before(condition.getCreateDatetimeStart())) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }
        return userBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<User> queryUserList(User condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeEnd()
                    .before(condition.getCreateDatetimeStart())) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }
        return userBO.queryUserList(condition);
    }

    @Override
    public User getUser(String code) {
        return userBO.getUser(code);
    }

}
