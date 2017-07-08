package com.kaishengit.web.acc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Result;
import com.kaishengit.service.AccService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/acc/del")
public class DelAccServlet extends AbstractServlet {
	
	private static final long serialVersionUID = 1L;
	private AccService as = new AccService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		Result result = null;
		if(StringUtils.isNumeric(id)) {
			try {
				as.delAccById(Integer.parseInt(id));
				result = new Result("success");
			} catch (Exception e) {
				result = new Result("error",e.getMessage());
			}
			result = new Result("success");
		} else {
			result = new Result("error","传入参数异常!");
		}
		sendJson(result,resp);
	}
}
