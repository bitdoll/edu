package com.spc.jpa.common;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * JSON String을 REST API 로 전달하기 위한 Wrapper Class
 * @author lKJ
 */
public class RawJsonString {
	private final String value;

	public RawJsonString(String value) {
		this.value = value;
	}

	@JsonValue
	@JsonRawValue
	public String value() {
		return value;
	}
}
