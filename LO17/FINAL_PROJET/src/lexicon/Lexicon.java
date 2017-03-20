package lexicon;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generic class for Lemmas and StopList
 * @author romain
 */
public abstract class Lexicon {
	protected HashMap<String, String> lexique;
	
	public Lexicon(ArrayList<String> lex) throws Exception {
		this.lexique = new HashMap<String, String>(); // mot, lemme
		addWords(lex);
	}
	
	public void addWords(ArrayList<String> lex) {
		final String empty = "";
		
		for (String s : lex) {
			if (s.trim().isEmpty())
				continue;
			final String[] tokens = s.split("(\\s+)+");
			String tm = lexique.put(tokens[0].trim(), tokens.length > 1 ? tokens[1].trim() : empty);
			if (tm != null) {
				//throw new Exception("Word already existing: ".concat(tm)); // IGNORE
			}
		}
	}
	
	/**
	 * Returns the lemme associated to the given word
	 * @param s The word we want the lemme for
	 * @return The lemme or null if we don't have one
	 */
	protected String getLemme(String s) {
		return lexique.get(s);
	}
}
