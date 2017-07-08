package com.kaishengit.web.com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.CompanyService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/com/telValidate")
public class TelValidateServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tel = req.getParameter("companyTel");
		Boolean res = cs.checkByTel(tel);
		sendText(res,resp);
	}
}
