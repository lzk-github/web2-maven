package com.kaishengit.web.acc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Acc;
import com.kaishengit.entity.Company;
import com.kaishengit.entity.Result;
import com.kaishengit.service.AccService;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/acc/edit")
public class EditAccServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private AccService as = new AccService();
	private CompanyService cs = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		if(StringUtils.isNumeric(id)) {
			try {
				Acc acc = as.findById(Integer.parseInt(id));
				req.setAttribute("acc", acc);
			} catch (Exception e) {
				resp.sendError(404,e.getMessage());
			}
		} else {
			resp.sendError(404,"传入参数异常");
		}
		
		List<Company> comList = cs.findAll();
		List<String> deptList = cs.findAllDept();
		req.setAttribute("comList", comList);
		req.setAttribute("deptList", deptList);
		forward(req, resp, "acc/acc_edit.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String companyId = req.getParameter("company"); 
		String dept = req.getParameter("dept");
		
		Result res = new Result();
		try {
			as.edit(id,companyId,dept);
			res.setState("success");
		} catch (Exception e) {
			res.setState("error");
			res.setMessage(e.getMessage());
		}
		
		sendJson(res, resp);
	}

}






