package com.spc.jpa.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spc.jpa.common.ControllerConfig;

/**
 * @author lKJ
 */
@ControllerAdvice
public class ControllerConfig {

	private static Logger LOGGER = LoggerFactory.getLogger(ControllerConfig.class);

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e) {
		LOGGER.error("MessageNotReadable cause by \n{}", e.getMessage());
		throw e;
	}
}
