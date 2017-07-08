package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AbstractServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void forward(HttpServletRequest req,HttpServletResponse resp,String path) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/" + path).forward(req, resp);
	}

	public void sendJson(Object obj,HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(new Gson().toJson(obj));
		out.flush();
		out.close();
	}
	public void sendText(Object obj,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(obj);
		out.flush();
		out.close();
	}
}
