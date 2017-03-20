package webservice;

import com.google.gson.Gson;

import utils.ErrorJSON;

public abstract class API {
	private final String error = "Unknown error";
	protected final Gson json = new Gson();
	
	/**
	 * Handle an exception and returns a JSON containing useful information
	 * @param e The {@link Exception}
	 * @return A JSON giving information about the exception
	 */
	protected String handleException(Exception e) {
		e.printStackTrace();
		String err = e.getMessage();
		err = err != null && !err.trim().isEmpty() ? err : error;
		return json.toJson(new ErrorJSON(err));
	}
}
