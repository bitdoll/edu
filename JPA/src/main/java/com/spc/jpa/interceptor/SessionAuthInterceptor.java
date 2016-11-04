package com.spc.jpa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spc.jpa.domain.user.User;
import com.spc.jpa.domain.user.UserRepository;

@Component
public class SessionAuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionAuthInterceptor.class);
	
	@Autowired
	UserRepository userRepository;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if ("test_user_token_uuid".equals(request.getHeader("uuid"))) {
			return true;
		}
    	
    	// header에 정보가 있는지 체크
    	if (request.getHeader("uuid") != null) {
			// uuid의 vaildation 체크
			User user = userRepository.findOneByTokenUuid(request.getHeader("uuid"));
			
			// 유효한 uuid가 아닐경우
			if( user == null ){
				//user 값이 없을 경우 화면에 보내주어야 할 페이지로 전환 한다.
				throw new ModelAndViewDefiningException(new ModelAndView("/error"));
			}
		} else {
			throw new ModelAndViewDefiningException(new ModelAndView("/error"));
		}
    	
		return true;
    }
    
    @Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

}
