package com.kaishengit.service;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.SettingsDao;
import com.kaishengit.entity.Settings;
import com.kaishengit.exception.ServiceException;

public class SettingsService {

	private SettingsDao sd = new SettingsDao();
	public String getValueBySettingName(String key) {
		Settings settings = sd.findByName(key);
		return settings.getValue();
	}
	public void updateByKeyAndValue(String key, String value) {
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sd.update(key,value);
		} else {
			throw new ServiceException("传入参数异常!");
		}
	}

}
