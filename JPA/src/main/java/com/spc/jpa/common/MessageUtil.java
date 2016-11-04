package com.spc.jpa.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
 
/**
 * MessageUtil
 * @author lKJ
 */
public class MessageUtil {
 
    private MessageSourceAccessor messageSourceAccessor = null;
     
    public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
        this.messageSourceAccessor = msAcc;
    }
     
    public String getMessage(String key) {
    	if( null == RequestContextHolder.getRequestAttributes() ) {
    		return messageSourceAccessor.getMessage(key);
    	}
    	else {
    		Locale locale = LocaleContextHolder.getLocale();
    		return messageSourceAccessor.getMessage(key, locale);
    	}
    }
    
}
