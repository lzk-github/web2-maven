package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Invest;
import com.kaishengit.service.InvestService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/invest/list")
public class ListInvestServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private InvestService investService = new InvestService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("page");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		Page<Invest> pageObj = investService.findPageObjByParams(p,key,value);
		
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("pageObj", pageObj);
		forward(req, resp, "invest/invest_list.jsp");
	}
}










