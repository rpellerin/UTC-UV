package utils;

public final class StringSanitizer {
	
	private StringSanitizer() {
		// No instantiation
	}
	
	/**
	 * Sanitize the string by removing d' and l' and qu'
	 * @param s The {@link String} to sanitize
	 * @return The {@link String} cleaned
	 */
	public static String removeSingleQuote(String s) {
		String str = new String(s);
		String[] tmp = str.split("'");
		if (tmp.length == 1) {
			tmp = str.split("’");
		}
		if (tmp.length > 1 && (tmp[0].equals("l") || tmp[0].equals("d") || tmp[0].equals("qu"))) {
			str = tmp[1];
		}
		return str;
	}
	
	public static String removePunctuation(String s) {
		return s.replace("!", "")
				.replace(".", "")
				.replace("?", "")
				.replaceAll("\\s+", " ")
				.trim();
	}
	
	/**
	 * Remove punctuation marks, leading and trailing spaces, double spaces
	 * @param s The {@link String} to sanitize
	 * @return The sanitized {@link String}
	 */
	public static String sanitize(String s) {
		return s.replace("\"", "")
				.replace("“", "")
				.replace("”", "")
				.replaceAll("\\s+", " ")
				.trim();
	}
}
