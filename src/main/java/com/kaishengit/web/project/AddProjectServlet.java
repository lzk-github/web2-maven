package com.kaishengit.web.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/project/add")
public class AddProjectServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private ProjectService ps = new ProjectService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*String projectName = req.getParameter("projectName");
		String sumMoney = req.getParameter("sumMoney");
		String remainMoney = req.getParameter("remainMoney");
		String repayRate = req.getParameter("repayRate");
		String months = req.getParameter("months");
		String beginDate = req.getParameter("beginDate");
		String endDate = req.getParameter("endDate");
		String remark = req.getParameter("remark");*/
		
		Map<String,String> map = new HashMap<>();
		map.put("projectName",req.getParameter("projectName"));
		map.put("sumMoney",req.getParameter("sumMoney"));
		map.put("remainMoney",req.getParameter("remainMoney"));
		map.put("repayRate",req.getParameter("repayRate"));
		map.put("months",req.getParameter("months"));
		map.put("startDate",req.getParameter("startDate"));
		map.put("endDate",req.getParameter("endDate"));
		map.put("remark",req.getParameter("remark"));
		
		Result res = new Result();
		try {
			ps.saveByMap(map);
			res.setState("success");
		} catch (ServiceException e) {
			res.setState("error");
			res.setMessage(e.getMessage());
		}
		
		sendJson(res,resp);
	}
}










