package com.kaishengit.web.invest;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.kaishengit.dao.InvestDao;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Invest;
import com.kaishengit.entity.Project;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.InvestService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/invest/concat")
public class ConcatInvestServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private ProjectService projectService = new ProjectService();
	private CustomerService customerService = new CustomerService();
	private InvestDao investDao = new InvestDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String investId = req.getParameter("id");
		Map<String ,Object> map = Maps.newHashMap();
		if(StringUtils.isNumeric(investId)) {
			Invest invest = investDao.findById(investId);
			Project project = projectService.findById(String.valueOf(invest.getProjectId()));
			Customer customer = customerService.findById(String.valueOf(invest.getCustomerId()));
			map.put("state", "success");
			map.put("invest", invest);
			map.put("project", project);
			map.put("customer", customer);
		} else {
			map.put("state", "error");
			map.put("message", "传入参数异常!");
		}
		
		sendJson(map,resp);
	}
}











