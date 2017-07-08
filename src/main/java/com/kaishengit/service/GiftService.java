package com.kaishengit.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.GiftDao;
import com.kaishengit.entity.Gift;
import com.kaishengit.util.Page;

public class GiftService {

	private GiftDao giftDao = new GiftDao();
	public Page<Gift> findPageByPage(String page) {
		int pageNo = 1;
		if(StringUtils.isNumeric(page)) {
			pageNo = Integer.parseInt(page);
		}

		int count = giftDao.findCount();
		Page<Gift> pageObj = new Page<>(pageNo,count);
		List<Gift> items = giftDao.findList();
		pageObj.setItems(items);
		
		
		return pageObj;
	}

}
