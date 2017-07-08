package com.kaishengit.web.interest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.InterestVO;
import com.kaishengit.service.InterestService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/interest/list")
public class InterestVOListServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private InterestService ins = new InterestService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("page");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		Page<InterestVO> pageObj = ins.findListByParams(p,key,value);
		req.setAttribute("pageObj", pageObj);
		
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		forward(req, resp, "interest/interest_list.jsp");
	}
}





