package com.kaishengit.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Acc;
import com.kaishengit.service.AccService;
import com.kaishengit.util.AESUtils;
import com.kaishengit.util.ValidateUtil;

public class ValidateAccFilter extends AbstractFilter {

	private AccService as = new AccService();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		if(ValidateUtil.isNeedToValidate(uri)) {
			
			String regex = "(/\\w+)+";
			if(uri.matches(regex)) {
				
				HttpSession session = req.getSession();
				Acc acc = (Acc) session.getAttribute("acc");
				if(acc == null) {
					
					//自动登录
					try { 
						acc = getAccByCookie(req);
						if(acc != null) {
							session.setAttribute("acc", acc);
							chain.doFilter(req, resp);
						} else {
							resp.sendRedirect("/login?callback="+uri);
						}
						
					} catch (Exception e) {
						resp.sendRedirect("/login?callback="+uri);
					}
					
					
				} else {
					chain.doFilter(req, resp);
				}
			} else {
				resp.sendError(403,"访问路径不正确!");
			}
			
		} else {
			chain.doFilter(req, resp);
		}
		
	}

	private Acc getAccByCookie(HttpServletRequest req) throws Exception {
		Cookie[] cookies = req.getCookies();
		Acc acc = null;
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c != null && "userToken".equals(c.getName())) {
					String pass = c.getValue();
					String decode = AESUtils.decode(pass);
					String userName = decode.split("-")[0];
					String password = decode.split("-")[1];
					String remoteIp = req.getRemoteAddr();
					
					acc = as.login(userName, password, remoteIp);
				}
			}
		}
		
		return acc;
	}


}
