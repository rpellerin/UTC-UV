package utils;

import javax.servlet.http.HttpServletRequest;

public class Requests {
	private Requests() { }
	
	public static String getRequestValue(HttpServletRequest request, String fieldName) {
		String val = request.getParameter(fieldName);
		if (val == null || val.trim().length() == 0) {
			return null;
		} else {
			return val;
		}
	}
}
