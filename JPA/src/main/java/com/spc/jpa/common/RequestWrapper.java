package com.spc.jpa.common;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
public class RequestWrapper extends HttpServletRequestWrapper {
	
	private byte[] b;

	public RequestWrapper(HttpServletRequest request) throws IOException {
		
		super(request);
		
		InputStream is = super.getInputStream();
 		
 		b = IOUtils.toByteArray(is);
 		
 		String requestStringBody = new String(b);
 		
 		// request body 를 변경 하는 부분   
 		// request body 에 해당 하는 byte[] 을 바꿔주고 getInputStream 2번이 안되는 문제를 해결   
 		if(StringUtils.isEmpty(requestStringBody) == false){
 			
 			b = new String(filter(requestStringBody)).getBytes();
 		}
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		
		final ByteArrayInputStream bis = new ByteArrayInputStream(b);
 		
 		return new ServletInputStreamImpl(bis);
 	}
 	
 	class ServletInputStreamImpl extends ServletInputStream{
 		private InputStream is;
 		
 		public ServletInputStreamImpl(InputStream bis){
 			is = bis;
 		}
 		
 		public int read() throws IOException {
 			return is.read();
 		}
 		
 		public int read(byte[] b) throws IOException {
 			return is.read(b);
 		}

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
			// TODO Auto-generated method stub
			
		}
 	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values == null) {
			return null;
		}

		int count = values.length;
		String[] encodeValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodeValues[i] = filter(values[i]);
		}
		return encodeValues;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value == null) {
			return null;
		}
		return filter(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		return filter(value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String,String[]> getParameterMap(){
		Map<String,String[]> parameterMap = super.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		Iterator<String>  itrator = keySet.iterator();
		Map<String,String[]> cleanMap = new HashMap<String, String[]>();
		while(itrator.hasNext()){
			String key = itrator.next();
			String[] paramValues = parameterMap.get(key);
			if(paramValues == null){
				cleanMap.put(key, paramValues);
			} else{
				int count = paramValues.length;
				String[] encodedValues = new String[count];
				for (int i = 0; i < count; i++) {
					encodedValues[i] = filter(paramValues[i]);
				}
				cleanMap.put(key, encodedValues);
			}
		}

		return cleanMap;
	}


	private String filter(String input) {
		if (input == null) {
			return null;
		}

		String clean = input.replaceAll("<script>", "<scr1pt>");
		clean = clean.replaceAll("</script>", "</scr1pt>");
		clean = clean.replaceAll("alert", "a1ert");
		
		clean = input.replaceAll("<", "&lt;");
		clean = clean.replaceAll(">", "&gt;");
		clean = clean.replaceAll("\"", "&quot;");
		clean = clean.replaceAll("\'", "&#39;");
		clean = clean.replaceAll("%", "&#37;");
		clean = input.replaceAll("&", "&amp;");

		return clean;
	}

}

