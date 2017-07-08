package com.kaishengit.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/cust/birth")
public class CustomerBirthServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CustomerService cs = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String days = req.getParameter("days");
		String p = req.getParameter("p");
		
		Page<Customer> pageObj = cs.findBirthDaysAndPage(days,p);
		
		req.setAttribute("pageObj", pageObj);
		forward(req, resp, "cust/cus_birth.jsp");
	}
}





