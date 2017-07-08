package com.kaishengit.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/cust/add")
public class CustomerAddServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CustomerService cs = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward(req, resp, "cust/cus_add.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String idNo = req.getParameter("idNo");
		String tel = req.getParameter("tel");
		String bankNo = req.getParameter("bankNo");
		String bankName = req.getParameter("bankName");
		String birthday = req.getParameter("birthday");
		String address = req.getParameter("address");
		String remark = req.getParameter("remark");
		
		Result result = new Result();
		try {
			cs.save(name,idNo,tel,bankNo,bankName,birthday,address,remark);
			result.setState("success");
		} catch (ServiceException e) {
			result.setState("error");
			result.setMessage(e.getMessage());
		}
		sendJson(result,resp);
	}
	
	
	
}
