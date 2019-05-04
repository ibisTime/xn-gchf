package com.cdkj.gchf.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISubbranchBO;
import com.cdkj.gchf.bo.ISuperviseBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.common.PwdUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IUserDAO;
import com.cdkj.gchf.domain.Subbranch;
import com.cdkj.gchf.domain.Supervise;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.enums.EUserStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private ISubbranchBO subbranchBO;

    @Autowired
    private ISuperviseBO superviseBO;

    @Override
    public void resetAdminLoginPwd(User user, String loginPwd) {
        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        userDAO.updateLoginPwd(user);
    }

    @Override
    public void refreshMobile(User data, String mobile, String updater,
            String remark) {
        data.setMobile(mobile);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        userDAO.updateMobile(data);

    }

    @Override
    public void refreshRole(String userId, String roleCode, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setRoleCode(roleCode);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateRole(data);
        }
    }

    @Override
    public void refreshStatus(String userId, EUserStatus status, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setStatus(status.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateStatus(data);
        }
    }

    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    @Override
    public void refreshLoginPwd(User data, String loginPwd) {
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        userDAO.updateLoginPwd(data);
    }

    @Override
    public void refreshLoginPwd(User data, String loginPwd, String updater,
            String remark) {
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        userDAO.updateLoginPwd(data);
    }

    @Override
    public boolean isUserExist(String code) {
        User condition = new User();
        if (userDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveUser(User data) {
        userDAO.insert(data);
    }

    @Override
    public void saveProjectAdmin(String projectCode, XN631600Req req) {
        // req.getName()- projectName,
        // req.getLinkMan()-linkManName,req.getLinkPhone()-mobile

        User user = new User();
        String userId = OrderNoGenerater.generate("U");
        user.setUserId(userId);
        user.setOrganizationCode(projectCode);
        user.setType(EUserKind.Owner.getCode());
        user.setRealName(req.getLinkMan());
        user.setLoginName(projectCode);
        // user.setLoginName(req.getName().concat("管理员"));
        user.setMobile(req.getLinkPhone());

        user.setLoginPwd(MD5Util.md5("888888"));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel("888888"));
        user.setCreateDatetime(new Date());
        user.setRoleCode("RO201800000000000003");// 业主端管理员角色
        user.setUserRefree("USYS201800000000001");// 系统管理员用户
        user.setStatus(EUserStatus.NORMAL.getCode());

        userDAO.insert(user);
    }

    @Override
    public List<User> queryUserList(User condition) {
        return userDAO.selectList(condition);
    }

    @Override
    public User getUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户不存在");
            }
            initUser(data);
        }
        return data;
    }

    @Override
    public User getBriefUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        return userDAO.selectBriefUser(user);
    }

    @Override
    public List<User> getUserByOrganization(String organizationCode) {
        List<User> list = new ArrayList<User>();
        if (StringUtils.isNotBlank(organizationCode)) {
            User condition = new User();
            condition.setOrganizationCode(organizationCode);
            list = userDAO.selectList(condition);
        }
        return list;
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "原登录密码错误");
            }
        } else {
            throw new BizException("jd00001", "原登录密码错误");
        }
    }

    @Override
    public User getUserName(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
        }
        return data;
    }

    @Override
    public List<User> checkLoginName(String loginName) {
        User condition = new User();
        condition.setLoginName(loginName);
        return userDAO.selectList(condition);
    }

    private void initUser(User data) {
        // 监管用户数据
        if (EUserKind.Supervise.getCode().equals(data.getType())) {
            Supervise supervise = superviseBO
                .getSupervise(data.getOrganizationCode());
            if (null != supervise) {
                data.setProvince(supervise.getProvince());
                data.setCity(supervise.getCity());
                data.setArea(supervise.getArea());
            }

            // 监管单位项目列表
            // List<String> projectCodeList = projectBO.queryProjectCodeList(
            // supervise.getProvince(), supervise.getCity(),
            // supervise.getArea());
            // data.setProjectCodeList(projectCodeList);
        }

        // 业主用户数据
        // if (EUserKind.Owner.getCode().equals(data.getType())) {
        // Project project = projectBO.getProject(data.getOrganizationCode());
        // if (null != project) {
        // data.setProjectCode(project.getCode());
        // data.setProjectName(project.getName());
        // data.setProvince(project.getProvince());
        // data.setCity(project.getCity());
        // data.setArea(project.getArea());
        // }
        // }

        // 银行用户数据
        if (EUserKind.Bank.getCode().equals(data.getType())) {
            Subbranch subbranch = subbranchBO
                .getSubbranch(data.getOrganizationCode());
            data.setBankName(subbranch.getBankName());
            data.setSubbranch(subbranch.getSubbranchName());
        }
    }

}
