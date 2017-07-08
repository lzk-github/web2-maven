package com.kaishengit.web.com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/com/del")
public class DelComServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		Result res = new Result();
		try {
			cs.delById(id);
			res.setState("success");
		} catch (ServiceException e) {
			res.setState("fail");
			res.setMessage(e.getMessage());
		}
		sendJson(res, resp);
	}

}
