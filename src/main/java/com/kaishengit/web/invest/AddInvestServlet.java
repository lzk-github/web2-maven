package com.kaishengit.web.invest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Acc;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Result;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.InvestService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/invest/add")
public class AddInvestServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private ProjectService ps = new ProjectService();
	private CustomerService cs = new CustomerService();
	private InvestService is = new InvestService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String projectId = req.getParameter("id");
		Project project = ps.findById(projectId);
		req.setAttribute("project", project);
		
		List<Customer> customerList = cs.findAllCustomers();
		req.setAttribute("customerList", customerList);
		
		List<Project> projectList = ps.findNormalList();
		req.setAttribute("projectList", projectList);
		
		forward(req, resp, "invest/invest_add.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("cust");
		String projectId = req.getParameter("pro");
		String remainMoney = req.getParameter("remainMoney");
		String investMoney =req.getParameter("investMoney");
		String repayRate = req.getParameter("repayRate");
		String months = req.getParameter("months");
		String signDate = req.getParameter("signDate");
		String endDate = req.getParameter("endDate");
		
		HttpSession session = req.getSession();
		Acc acc = (Acc) session.getAttribute("acc");
		int accId = acc.getId();
		
		
		Result result = new Result();
		try {
			is.saveInvests(accId,custId,projectId,remainMoney,investMoney,repayRate,months,signDate,endDate);
			result.setState("success");
			
		} catch (Exception e) {
			result.setState("error");
			result.setMessage(e.getMessage());
		}
		
		sendJson(result,resp);
	}

}






