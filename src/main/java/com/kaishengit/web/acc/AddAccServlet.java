package com.kaishengit.web.acc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kaishengit.entity.Company;
import com.kaishengit.service.AccService;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/acc/add")
public class AddAccServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private AccService as = new AccService();
	private CompanyService cs = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Company> comList = cs.findAll();
		List<String> deptList = cs.findAllDept();
		
		req.setAttribute("comList", comList);
		req.setAttribute("deptList", deptList);
		forward(req,resp,"acc/acc_add.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("userName");
		String idNo = req.getParameter("idNo");
		String tel = req.getParameter("tel");
		String companyId = req.getParameter("company");
		String dept = req.getParameter("dept");
		
		Map<String,Object> map = new HashMap<>();
		try {
			as.save(name,idNo,tel,companyId,dept);
			map.put("state", "success");
		} catch (RuntimeException e) {
			map.put("state", "error");
			map.put("message", e.getMessage());
		}
		resp.setContentType("aplication/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(new Gson().toJson(map));
		out.flush();
		out.close();
	}
}







