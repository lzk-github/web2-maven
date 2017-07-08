package com.kaishengit.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.kaishengit.util.Config;

@WebFilter("/*")
public class EncodingFilter extends AbstractFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(Config.get("encoding"));
		resp.setCharacterEncoding(Config.get("encoding"));
		chain.doFilter(req, resp);
	}

}
