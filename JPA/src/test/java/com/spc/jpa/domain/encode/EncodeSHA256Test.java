package com.spc.jpa.domain.encode;

import org.junit.Test;

import com.spc.jpa.AbstractIntegrationTest;
import com.spc.jpa.common.Utils;

public class EncodeSHA256Test extends AbstractIntegrationTest{
	
	Utils utils = new Utils();
	
	@Test
	public void encodeSHA265Test() {
		String testStr = "bliex";
		System.out.println(testStr);
		
		testStr = utils.encodeSHA256(testStr);
		System.out.println(testStr);
    }
}
