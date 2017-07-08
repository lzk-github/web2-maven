package com.kaishengit.web.com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Company;
import com.kaishengit.service.CompanyService;
import com.kaishengit.util.Page;
import com.kaishengit.web.AbstractServlet;

@WebServlet("/com/list")
public class ListComServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private CompanyService cs = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String page = req.getParameter("p");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		key = key != null ? (new String(key.getBytes("ISO-8859-1"),"UTF-8")) : key;
		value = value != null ? (new String(value.getBytes("ISO-8859-1"),"UTF-8")) : value;
		//需求,前端页面传来了key,value,page参数,根据传入的参数获取Page集合,里面封装Company对象
		int pageNo = 1;
		if(StringUtils.isNumeric(page)) {
			pageNo = Integer.parseInt(page);
		}
		Page<Company> pageList = cs.findByparams(pageNo,key,value);
		
		req.setAttribute("key", key);
		req.setAttribute("value",value);
		req.setAttribute("pageList", pageList);
		forward(req, resp, "com/com_list.jsp");
	}

}
