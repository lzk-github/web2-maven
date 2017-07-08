package com.kaishengit.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Acc;
import com.kaishengit.util.ValidateUtil;

/**
 * 
 * 权限过滤器
 * @author lzk
 *
 */
public class PriviligeFilter extends AbstractFilter {

	private static Map<String,String> map = null;
	//销售部 -财务部-运营部-总经理办公室
	static {
		map = new HashMap<String,String>();
		map.put("销售部", "/cust,/invest,/home");
		map.put("财务部", "/sal,/home");
		map.put("运营部", "/point,/home");
		map.put("总经理办公室", "/cust,/invest,/project,/pro,/com,/acc,/sal,/home");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		
		if(ValidateUtil.isNeedToValidate(uri)) {
			
			//判断acc的dept是否可以访问此uri,那么需要建立一套映射,即 :dept:URI
			
			if(ValidateUtil.isNeedToValidate(uri)) {
				if(priviligeValidate(uri,req)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendError(403,"权限不足,无法访问!");
				}
			} else {
				chain.doFilter(req, resp);
			}
		} else {
			chain.doFilter(req, resp);
		}
		
	}

	/**
	 * 权限校验
	 * @param uri
	 * @param req 
	 * @return
	 */
	private boolean priviligeValidate(String uri, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Acc acc = (Acc) session.getAttribute("acc");
		String uris = map.get(acc.getDept());
		List<String> list = Arrays.asList(uris.split(","));
		for(String str : list) {
			if(uri.startsWith(str)) {
				return true;
			}
		}
		return false;
	}

}
