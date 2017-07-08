package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.InvestService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/invest/unuse")
public class UnuseServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private InvestService is = new InvestService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		Result res = new Result();
		try {
			is.unuseById(id);
			res.setState("success");
		} catch (ServiceException e) {
			res.setState("error");
			res.setMessage(e.getMessage());
		}
		sendJson(res,resp);
		
	}
}






