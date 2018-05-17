package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IUserAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISYSMenuRoleBO;
import com.cdkj.gchf.bo.ISYSRoleBO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.common.PwdUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.SYSMenuRole;
import com.cdkj.gchf.domain.SYSRole;
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
    private IDepartmentBO departmentBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String doAddUser(XN631070Req req) {
        if (StringUtils.isNotBlank(req.getMobile())) {
            PhoneUtil.checkMobile(req.getMobile());
        }

        User data = new User();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);

        data.setType(req.getType());

        data.setRealName(req.getRealName());
        data.setLoginName(req.getLoginName());
        data.setMobile(req.getMobile());

        data.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        data.setLoginPwdStrength(
            PwdUtil.calculateSecurityLevel(req.getLoginPwd()));
        data.setCreateDatetime(new Date());
        data.setStatus(EUserStatus.NORMAL.getCode());
        if (EUserKind.Owner.getCode().equals(req.getType())) {
            Company company = companyBO.getCompany(req.getCompanyCode());
            data.setCompanyCode(company.getCode());
            data.setCompanyName(company.getName());
        }
        if (EUserKind.Bank.getCode().equals(req.getType())) {
            data.setLoginName(req.getBankName() + req.getSubbranch());
            data.setBankName(req.getBankName());
            data.setSubbranch(req.getSubbranch());
        }
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setRemark(req.getRemark());

        // 给用户分配角色
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
        // 验证手机号
        userBO.isMobileExist(newMobile);
        userBO.refreshMobile(user, newMobile, updater, remark);
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "提交的更改绑定手机号码服务已审核通过，现绑定手机号码为" + newMobile + "，同时您的登录名更改为"
                    + newMobile + "，请妥善保管您的账户相关信息。",
            "805061");
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
        if (!EUserKind.Plat.getCode().equals(user.getType())) {
            smsOutBO.sendSmsOut(user.getMobile(),
                "尊敬的" + PhoneUtil.hideMobile(user.getMobile())
                        + "用户，您的登录密码修改成功。请妥善保管您的账户相关信息。",
                "631073");
        }
    }

    @Override
    @Transactional
    public void doResetLoginPwdByOss(String userId, String newLoginPwd,
            String updater, String remark) {
        User data = userBO.getUser(userId);
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
        userBO.refreshStatus(userId, userStatus, updater, remark);
        if (!EUserKind.Plat.getCode().equals(user.getType())
                && StringUtils.isNotBlank(user.getMobile())) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile,
                "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "631075");
        }
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
                || EUserStatus.Ren_Locked.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050",
                "该账号" + EUserStatus.getMap().get(user.getStatus()).getValue()
                        + "，请联系工作人员");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805063");
        userBO.refreshLoginPwd(user, newLoginPwd);
        // // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码重置成功。请妥善保管您的账户相关信息。",
            "805063");
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
        for (User user : list) {
            if (StringUtils.isNotBlank(user.getDepartmentCode())) {
                Department department = departmentBO
                    .getDepartment(user.getDepartmentCode());
                user.setDepartmentName(department.getName());
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
        for (User user : list) {
            if (StringUtils.isNotBlank(user.getDepartmentCode())) {
                Department department = departmentBO
                    .getDepartment(user.getDepartmentCode());
                user.setDepartmentName(department.getName());
            }

        }

        return list;
    }

    @Override
    public User getUser(String code) {
        User data = userBO.getUser(code);
        if (EUserKind.Supervise.getCode().equals(data.getType())
                || EUserKind.Owner.getCode().equals(data.getType())) {
            Project condition = new Project();
            condition.setProvince(data.getProvince());
            condition.setCity(data.getCity());
            condition.setArea(data.getArea());
            condition.setCompanyCode(data.getCompanyCode());
            List<String> projectCodeList = projectBO
                .queryProjectCodeList(condition);
            data.setProjectCodeList(projectCodeList);
        }
        return data;
    }

    @Override
    public void doDepartmentCode(String userId, String departmentCode,
            String updater, String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn0000", "用户不存在");
        }
        userBO.refreshDepartment(user, departmentCode, updater, remark);
    }

}
