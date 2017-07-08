package com.kaishengit.web.acc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Acc;
import com.kaishengit.service.AccService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/acc/list")
public class ListAccServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private AccService as = new AccService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		String recivePageNo = req.getParameter("page");
		
		if(StringUtils.isNotEmpty(value)) {
			value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		int pageNo = 1;
		
		
		if(StringUtils.isNumeric(recivePageNo)) {
			pageNo = Integer.parseInt(recivePageNo);
		}
		
		Page<Acc> pageList = null;;
		try {
			pageList = as.findByParams(pageNo,key,value);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("pageList", pageList);
		
		forward(req, resp, "acc/acc_list.jsp");
	}

}
