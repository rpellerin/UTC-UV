package utils;

/**
 * Class used to represent an error in JSON
 * @author romain
 *
 */
public class ErrorJSON {
	private final String error;

	public ErrorJSON() {
	  this("Unknown error");
	}
	
	public ErrorJSON(String msg) {
		  this.error = msg;
	}
	
	public final String getError() {
		return error;
	}
}
