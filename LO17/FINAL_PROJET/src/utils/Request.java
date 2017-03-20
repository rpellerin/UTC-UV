package utils;

import javax.servlet.http.HttpServletRequest;

public class Request {
	private Request() { /* no instanciation */ }
	
	public static String getRequestValue(HttpServletRequest request, String fieldName) {
		String val = request.getParameter(fieldName);
		if (val == null || val.trim().length() == 0) {
			return null;
		} else {
			return val;
		}
	}
}

