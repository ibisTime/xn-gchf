package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.MD5Util;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.common.PwdUtil;
import com.cdkj.gchf.dao.IUserDAO;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EUserStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {

    @Autowired
    private IUserDAO userDAO;

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
    public void refreshDepartment(User data, String departmentCode,
            String updater, String remark) {
        data.setDepartmentCode(departmentCode);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        userDAO.updateDepartment(data);
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
    public void refreshPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setPhoto(photo);
            userDAO.updatePhoto(data);
        }
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
        }
        return data;
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
    public List<User> getUserByCompany(String companyCode) {
        User condition = new User();
        condition.setCompanyCode(companyCode);
        return userDAO.selectList(condition);
    }

    @Override
    public List<User> checkLoginName(String loginName) {
        User condition = new User();
        condition.setLoginName(loginName);
        return userDAO.selectList(condition);
    }

}
