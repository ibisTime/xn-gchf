package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.UserSettings;

public interface IUserSettingsDAO extends IBaseDAO<UserSettings> {
	String NAMESPACE = IUserSettingsDAO.class.getName().concat(".");
}