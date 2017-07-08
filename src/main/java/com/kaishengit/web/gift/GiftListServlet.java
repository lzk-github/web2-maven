package com.kaishengit.web.gift;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Gift;
import com.kaishengit.service.GiftService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/gift/list")
public class GiftListServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private GiftService giftService = new GiftService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String page = req.getParameter("p");
		
		Page<Gift> pageObj = giftService.findPageByPage(page);
		
		req.setAttribute("pageObj", pageObj);
		forward(req, resp, "gift/gift_list.jsp");
	}
}







