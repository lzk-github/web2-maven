package com.kaishengit.web.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/cust/edit")
public class CustomerEditServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		Customer customerObj = customerService.findById(id);
		req.setAttribute("customerObj", customerObj);
		
		List<Customer> customerList = customerService.findAllCustomers();
		req.setAttribute("customerList", customerList);
		forward(req, resp, "cust/cus_edit.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("name");
		String idNo = req.getParameter("idNo");
		String tel = req.getParameter("tel");
		String bankNo = req.getParameter("bankNo");
		String bankName = req.getParameter("bankName");
		String birthday = req.getParameter("birthday");
		String address = req.getParameter("address");
		String remark = req.getParameter("remark");
		
		Result res = new Result();
		try {
			customerService.edit(custId,idNo,tel,bankNo,bankName,birthday,address,remark);
			res.setState("success");
		} catch (ServiceException e) {
			res.setState("error");
			res.setMessage(e.getMessage());
		}
		sendJson(res,resp);
	}
}









