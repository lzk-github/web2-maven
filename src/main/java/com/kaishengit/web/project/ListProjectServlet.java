package com.kaishengit.web.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Project;
import com.kaishengit.service.ProjectService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/project/list")
public class ListProjectServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private ProjectService ps = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		String page = req.getParameter("page");
		
		int pageNo = 1;
		if(StringUtils.isNumeric(page)) {
			pageNo = Integer.parseInt(page);
		}
		
		Page<Project> pageList = ps.findPageByParams(pageNo,key,value);
		req.setAttribute("pageList", pageList);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		
		forward(req, resp, "project/project_list.jsp");
		
	}
}












