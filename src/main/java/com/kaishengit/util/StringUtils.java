package com.kaishengit.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.exception.ServiceException;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	private static Logger logger = LoggerFactory.getLogger(StringUtils.class);
	
	public static String iSO8859_1_To_UTF_8 (String str) {
		String res = null;
		if(str != null) {
			try {
				res = new String(str.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("转换字符串{}编码异常",str);
				throw new ServiceException("字符串转换异常");
			}
		}
		return res;
	}
}
