package com.kaishengit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.kaishengit.util.StringUtils;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("file");
		String downloadName = req.getParameter("downloadName");
		
		File parentFile = new File("f:/picture");
		
		if(!parentFile.exists()) {
			parentFile.mkdir();
		}
		
		if(StringUtils.isNotEmpty(downloadName)) {
			resp.setContentType("application/octet-stream");
			resp.addHeader("Content-Disposition", "attachment;filename="+downloadName);
		}
		
		
		
		
		InputStream in = new FileInputStream(new File(parentFile,fileName));
		OutputStream out = resp.getOutputStream();
		IOUtils.copy(in, out);
		
		out.flush();
		out.close();
		in.close();
	}

}







