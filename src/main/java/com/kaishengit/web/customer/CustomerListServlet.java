package com.kaishengit.web.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/cust/list")
public class CustomerListServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CustomerService cs = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		String attr = req.getParameter("attr");
		String value = req.getParameter("value");
		attr = StringUtils.iSO8859_1_To_UTF_8(attr);
		value = StringUtils.iSO8859_1_To_UTF_8(value);
		
		Page<Customer> pageObj = cs.findByAttrAndPage(p,attr,value);
		
		req.setAttribute("attr", attr);
		req.setAttribute("value", value);
		req.setAttribute("pageObj", pageObj);
		forward(req, resp, "/cust/cus_list.jsp");
		
	}
}








