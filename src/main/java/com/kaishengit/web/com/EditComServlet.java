package com.kaishengit.web.com;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Company;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.util.Config;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/com/edit")
public class EditComServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		if(StringUtils.isNumeric(id)) {
			try {
				Company com = cs.editById(Integer.parseInt(id));
				req.setAttribute("com", com);
				
				List<String> cityList = Arrays.asList(Config.get("company.city").split("-"));
				req.setAttribute("cityList", cityList);
				forward(req, resp, "com/com_edit.jsp");
				
			} catch (ServiceException e) {
				
				resp.sendError(404,"编辑company,传入id不存在");
			}
			
		} else {
			resp.sendError(404,"编辑company,传入参数异常");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String companyName = req.getParameter("companyName");
		String tel = req.getParameter("companyTel");
		String city = req.getParameter("city");
		String controller = req.getParameter("controller");
		String remark = req.getParameter("remark");
		
		Result res = new Result();
		try {
			cs.editByParams(companyName,tel,city,controller,remark);
			res.setState("success");
		} catch (ServiceException e) {
			res.setState("error");
			res.setMessage(e.getMessage());
		}
		
		sendJson(res,resp);
	}

}
