package com.kaishengit.web.settings;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kaishengit.entity.Result;
import com.kaishengit.service.SettingsService;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/settings/set")
public class SettingsServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private SettingsService ss = new SettingsService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commissionValue = ss.getValueBySettingName("commission");
		String giftPointRate = ss.getValueBySettingName("giftPointRate");
		
		req.setAttribute("commission", commissionValue);
		req.setAttribute("giftPointRate", giftPointRate);
		
		req.getRequestDispatcher("/WEB-INF/views/settings/settings.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commission = req.getParameter("commission");
		String giftPointRate = req.getParameter("giftPointRate");
		
		Result res = new Result();
		try {
			ss.updateByKeyAndValue("commission",commission);
			ss.updateByKeyAndValue("giftPointRate",giftPointRate);
			res.setState("success");
		} catch (Exception e) {
			res.setState("error");
			res.setMessage(e.getMessage());
		}
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(new Gson().toJson(res));
	}
	
	
	
}






