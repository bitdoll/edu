package com.spc.jpa.interceptor;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LocaleInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	private LocaleResolver localeResolver;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 클라이언트 Locale
		Locale locale = request.getLocale();

		if (null != locale) {
			// 한글 제외하고는 모두 영어로 설정
			if (locale.getLanguage().indexOf("ko") == -1) {
				locale = Locale.US;
			}
			localeResolver.setLocale(request, response, locale);
		}
		return true;
		
	}
	
}
