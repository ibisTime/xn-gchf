package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISYSMenuRoleBO;
import com.cdkj.gchf.bo.ISYSRoleBO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.ISubbranchBO;
import com.cdkj.gchf.bo.ISuperviseBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.common.PwdUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.SYSMenuRole;
import com.cdkj.gchf.domain.SYSRole;
import com.cdkj.gchf.domain.Subbranch;
import com.cdkj.gchf.domain.Supervise;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631070Req;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.enums.EUserStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class UserAOImpl implements IUserAO {

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSRoleBO sysRoleBO;

    @Autowired
    private ISYSMenuRoleBO sysMenuRoleBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISubbranchBO subbranchBO;

    @Autowired
    private ISuperviseBO superviseBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    @Transactional
    public String doAddUser(XN631070Req req) {
        if (StringUtils.isNotBlank(req.getMobile())) {
            PhoneUtil.checkMobile(req.getMobile());
        }

        List<User> loginNameList = userBO.checkLoginName(req.getLoginName());
        if (CollectionUtils.isNotEmpty(loginNameList)) {
            throw new BizException("xn00000",
                "登录名" + req.getLoginName() + "已经存在!");
        }
        User data = new User();
        String userId = OrderNoGenerater.generate("U");
        String organizationCode = null;

        data.setUserId(userId);
        data.setType(req.getType());
        data.setRealName(req.getRealName());
        data.setUserRefree(req.getUserRefree());

        data.setLoginName(req.getLoginName());
        data.setMobile(req.getMobile());
        data.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        data.setLoginPwdStrength(
            PwdUtil.calculateSecurityLevel(req.getLoginPwd()));
        data.setCreateDatetime(new Date());
        data.setStatus(EUserStatus.NORMAL.getCode());

        // 业主用户
        if (EUserKind.Owner.getCode().equals(req.getType())) {
            Project project = projectBO.getProject(req.getProjectCode());
            if (null == project) {
                throw new BizException("xn00000", "业主端项目不存在！");
            }
            organizationCode = project.getCode();
        }

        // 银行用户
        if (EUserKind.Bank.getCode().equals(req.getType())) {
            Subbranch subbranch = subbranchBO.getSubbranch(req.getBankName(),
                req.getSubbranch());
            if (null == subbranch) {
                organizationCode = subbranchBO.saveSubbranch(req.getBankCode(),
                    req.getBankName(), req.getSubbranch());
            } else {
                organizationCode = subbranch.getCode();
            }
        }

        // 监管用户
        if (EUserKind.Supervise.getCode().equals(req.getType())) {
            Supervise supervise = superviseBO.getSupervise(req.getProvince(),
                req.getCity(), req.getArea());
            if (null == supervise) {
                organizationCode = superviseBO.saveSupervise(req.getProvince(),
                    req.getCity(), req.getArea());
            } else {
                organizationCode = supervise.getCode();
            }
        }

        data.setOrganizationCode(organizationCode);
        data.setRemark(req.getRemark());

        // 给用户分配角色
        if (null != req.getRoleCode()) {
            // 添加用户时手动选择角色
            data.setRoleCode(req.getRoleCode());
        } else {
            SYSRole condition = new SYSRole();
            condition.setType(req.getType());
            List<SYSRole> list = sysRoleBO.querySYSRoleList(condition);
            SYSRole role = list.get(0);
            String sysRoleCode = role.getCode();
            if (list.size() > 1) {
                // 获取最大角色
                long count = 0L;
                for (SYSRole sysRole : list) {
                    SYSMenuRole sysMenuRole = new SYSMenuRole();
                    sysMenuRole.setRoleCode(sysRole.getCode());
                    if (sysMenuRoleBO.getTotalCount(sysMenuRole) > count) {
                        count = sysMenuRoleBO.getTotalCount(sysMenuRole);
                        sysRoleCode = sysRole.getCode();
                    }
                }
            }
            data.setRoleCode(sysRoleCode);
        }

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
            throw new BizException("xn00000", "登录密码错误");
        }
        User user = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn00000", "该用户操作存在异常");
        }
        if (EUserKind.Owner.getCode().equals(user.getType())) {
            if (StringUtils.isBlank(user.getType())) {
                throw new BizException("xn00000", "您不属于任何公司");
            }
        }
        return user.getUserId();
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile, String updater,
            String remark) {
        PhoneUtil.checkMobile(newMobile);
        userBO.isMobileExist(newMobile);
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        userBO.refreshMobile(user, newMobile, updater, remark);
    }

    @Override
    @Transactional
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd) {
        if (oldLoginPwd.equals(newLoginPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }
        User user = userBO.getUser(userId);
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(userId, oldLoginPwd);
        // // 重置
        userBO.refreshLoginPwd(user, newLoginPwd);
    }

    @Override
    @Transactional
    public void doResetLoginPwdByOss(String userId, String newLoginPwd,
            String updater, String remark) {
        User data = userBO.getUser(userId);
        userBO.refreshLoginPwd(data, newLoginPwd, updater, remark);
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
        userBO.refreshStatus(userId, userStatus, updater, remark);
        // if (!EUserKind.Plat.getCode().equals(user.getType())
        // && StringUtils.isNotBlank(user.getMobile())) {
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
    public void doResetLoginPwd(String userId, String mobile, String smsCaptcha,
            String newLoginPwd) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        if (EUserStatus.Li_Locked.getCode().equals(user.getStatus())
                || EUserStatus.Ren_Locked.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050",
                "该账号" + EUserStatus.getMap().get(user.getStatus()).getValue()
                        + "，请联系工作人员");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "631080");
        userBO.refreshLoginPwd(user, newLoginPwd);
        // // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码重置成功。请妥善保管您的账户相关信息。",
                "804080");
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
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        List<User> list = page.getList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (User user : list) {
                initUser(user);
            }
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<User> queryUserList(User condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeEnd()
                    .before(condition.getCreateDatetimeStart())) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }
        List<User> list = userBO.queryUserList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (User user : list) {
                initUser(user);
            }
        }
        return list;
    }

    @Override
    public User getUser(String userId) {
        User data = userBO.getUser(userId);

        initUser(data);

        return data;
    }

    private void initUser(User data) {
        // 监管用户数据
        if (EUserKind.Supervise.getCode().equals(data.getType())) {
            // 监管区域
            Supervise supervise = superviseBO
                .getSupervise(data.getOrganizationCode());
            data.setProvince(supervise.getProvince());
            data.setCity(supervise.getCity());
            data.setArea(supervise.getArea());

            // 监管单位项目列表
            // List<String> projectCodeList = projectBO.queryProjectCodeList(
            // supervise.getProvince(), supervise.getCity(),
            // supervise.getArea());
            // data.setProjectCodeList(projectCodeList);

        }

        // 业主用户数据
        if (EUserKind.Owner.getCode().equals(data.getType())) {
            // 项目信息
            Project project = projectBO.getProject(data.getOrganizationCode());
            data.setProjectCode(project.getCode());
            data.setProjectName(project.getName());
            // data.setProvince(project.getProvince());
            // data.setCity(project.getCity());
            // data.setArea(project.getArea());
        }

        // 银行用户数据
        if (EUserKind.Bank.getCode().equals(data.getType())) {
            // 支行信息
            Subbranch subbranch = subbranchBO
                .getSubbranch(data.getOrganizationCode());
            data.setBankName(subbranch.getBankName());
            data.setSubbranch(subbranch.getSubbranchName());
        }
    }

    @Override
    public boolean doCheckLoginName(String loginName) {
        List<User> list = userBO.checkLoginName(loginName);
        if (CollectionUtils.isNotEmpty(list)) {
            return false;
        }
        return true;
    }
}
