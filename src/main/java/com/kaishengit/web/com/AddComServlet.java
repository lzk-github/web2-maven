package com.kaishengit.web.com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/com/add")
public class AddComServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> cityList = cs.findAllCity();
		req.setAttribute("cityList", cityList);
		
		forward(req, resp, "/com/com_add.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String companyName = req.getParameter("companyName");
		String companyTel = req.getParameter("companyTel");
		String city = req.getParameter("city");
		String controller = req.getParameter("controller");
		String remark = req.getParameter("remark");
		
		Result res = new Result();
		try {
			cs.save(companyName,companyTel,city,controller,remark);
			res.setState("success");
		} catch (ServiceException e) {
			res.setState("fail");
			res.setMessage(e.getMessage());
		}
		sendJson(res, resp);
	}
}
