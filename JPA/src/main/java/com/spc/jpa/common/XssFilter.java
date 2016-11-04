package com.spc.jpa.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StringUtils;

public class XssFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		String contentType = ((HttpServletRequest) request).getHeader("Content-Type");
		String accept = ((HttpServletRequest) request).getHeader("Accept");
		boolean isAjax = (accept != null && accept.toLowerCase().indexOf("application/json") != -1);
		boolean isMultipart = (contentType != null && contentType.toLowerCase().indexOf("multipart/form-data") != -1);
		
		// 1. Multipart 요청이거나
		// 2. JSON 호출(Ajax)이 아니거나
		// 3. Git File 수정 URL은 필터 제외
		if( isMultipart == true
			|| isAjax == false ){
			
			// 커스텀 필터 태우지 않음
			chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest)request), response);
		}
		else{
			
			// 커스텀 필터 태움
			chain.doFilter(new RequestWrapper((HttpServletRequest)request), response);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

}
