package com.kaishengit.web.salary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.SalarySum;
import com.kaishengit.service.SalaryService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;
@WebServlet("/sal/sum")
public class SalarySumServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private SalaryService salaryService = new SalaryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		Page<SalarySum> pageObj = salaryService.findPageByParams(p,key,value);
		
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("pageObj", pageObj);
		
		forward(req, resp, "salary/sal_sum.jsp");
	}
}












