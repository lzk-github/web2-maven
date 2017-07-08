package com.kaishengit.util;

import java.util.Arrays;
import java.util.List;

public class ValidateUtil {

	public static boolean isNeedToValidate(String uri) {
		uri = uri == null ? uri : uri.replace("/", "");
		
		List<String> list = Arrays.asList(Config.get("validateUri").replace("/","").split(","));
		
		if(list == null) {
			return false;
		} 
		if(list.contains("uri")) {
			return true;
		} 
		
		for (String str : list) {
			if(uri.startsWith(str)) {
				return true;
			}
		}
	
		return false;
	}
}
