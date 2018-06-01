package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IUserSettingsBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IUserSettingsDAO;
import com.cdkj.gchf.domain.UserSettings;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.EBizErrorCode;


@Component
public class UserSettingsBOImpl extends PaginableBOImpl<UserSettings> implements IUserSettingsBO {

    @Autowired
    private IUserSettingsDAO UserSettingsDAO;

    @Override
    public boolean isUserSettingsExist(String code) {
        UserSettings condition = new UserSettings();
        if (UserSettingsDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveUserSettings(UserSettings data) {

        data.setUpdateDatetime(new Date());
        data.setCreateDatetime(new Date());
        int count = UserSettingsDAO.insert(data);
        if (count != 1) {

            throw new BizException("xn000", "添加设置失败");

        }

    }

    @Override
    public int removeUserSettings(String userId, String type) {
        int count = 0;
        UserSettings deleteCondition = new UserSettings();
        deleteCondition.setUserId(userId);
        deleteCondition.setType(type);
        count = this.UserSettingsDAO.delete(deleteCondition);
        return count;
    }

    @Override
    public int refreshUserSettings(UserSettings data) {
        int count = 0;

        return count;
    }

    @Override
    public List<UserSettings> queryUserSettingsList(UserSettings condition) {
        return UserSettingsDAO.selectList(condition);
    }

    @Override
    public long checkUserSetting(String userId, String type) {

        UserSettings userSettings = new UserSettings();
        userSettings.setUserId(userId);
        userSettings.setType(type);
        long count = this.UserSettingsDAO.selectTotalCount(userSettings);

        return count;

    }


    //	@Override
//	public UserSettings getUserSettings(in code) {
//		UserSettings data = null;
//		if (StringUtils.isNotBlank(code)) {
//			UserSettings condition = new UserSettings();
//			condition.setCode(code);
//			data = UserSettingsDAO.select(condition);
//			if (data == null) {
//				throw new BizException("xn0000", "记录不存在");
//			}
//		}
//		return data;
//	}
}