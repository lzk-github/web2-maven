package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Acc;
import com.kaishengit.entity.Result;
import com.kaishengit.service.AccService;
import com.kaishengit.util.AESUtils;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private AccService as = new AccService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		forward(req, resp, "acc/login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("userName");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		String callback = req.getParameter("callback");
		
		String remoteIp = req.getRemoteAddr();
		Result res = new Result();
		try {
			Acc acc = as.login(name,password,remoteIp);
			if(StringUtils.isNotEmpty(remember)) {
				String userPass = name + "-" + password;
				String userToken = AESUtils.encode(userPass);
				Cookie cookie = new Cookie("userToken",userToken);
				cookie.setDomain("localhost");
				cookie.setHttpOnly(true);
				cookie.setMaxAge(60 * 60 * 24);
				cookie.setPath("/");
				
				resp.addCookie(cookie);
			}
			HttpSession session = req.getSession();
			session.setAttribute("acc", acc);
			res.setState("success");
			res.setData(callback);
			
		} catch (Exception e) {
			res.setState("fail");
			res.setMessage(e.getMessage());
		}
		
		sendJson(res, resp);
		
		
	}
}





