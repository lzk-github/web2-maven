package com.kaishengit.web.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Project;
import com.kaishengit.entity.Result;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.AbstractServlet;
@WebServlet("/pro/proChangeInInvest")
public class ProjectChangeInInvestServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private ProjectService ps = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		Result result = new Result();
		try {
			Project project = ps.findById(id);
			result.setState("success");
			result.setData(project);
		} catch (Exception e) {
			result.setState("error");
			result.setMessage(e.getMessage());
		}
		
		sendJson(result,resp);
	}
}








