package lexicon;
import java.util.ArrayList;

public class StopList extends Lexicon {
	
	public StopList(ArrayList<String> lex) throws Exception {
		super(lex);
	}
	
	/**
	 * Removing words from stop list or keeping them if not in the stop list
	 */
	public String removeWordsFromStopList(String s) {
		StringBuffer requestWithoutStoplist = new StringBuffer();
		String wordFromStopList = null;
		for (String str : s.split("\\s+")) {
			str = utils.StringSanitizer.removeSingleQuote(str);
			
			wordFromStopList = this.getLemme(str); // will return empty String ("") if str is in the stop list
			if (wordFromStopList != null)
				str = wordFromStopList;
			else {
				str = str + " ";
			}
			requestWithoutStoplist.append(str);
		}
		
		return requestWithoutStoplist.toString();
	}
}
