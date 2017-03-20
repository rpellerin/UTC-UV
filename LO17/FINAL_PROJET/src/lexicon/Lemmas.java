package lexicon;
import java.util.ArrayList;
import java.util.HashSet;

import utils.ConsoleOutputBuffer;

public class Lemmas extends Lexicon {
	
	public final static int SEUIL_MIN     = 4; // minimum 3 lettres par mot
	public final static int SEUIL_MAX     = 1; // differences entre 2 longueurs de mota
	public final static double SEUIL_PROX = 85.0;
	
	private ArrayList<ArrayList<String>> suggestions;
	
	public Lemmas(ArrayList<String> lex) throws Exception {
		super(lex);
	}
	
	/**
	 * Try to correct typos
	 * @param word The word for which we're looking for a lemma
	 * @param comparedTo The potential lemma
	 * @param score The Levenshtein score we obtained for those two words
	 * @return The new score weighted
	 */
	private double testInversions(String word,String comparedTo, int score){
		double newScore = (double) score;
		switch (score) {
		case 0:
			return (double)0;
			default:
				break;
		}
		
		boolean problem = false;
		for (int i = 0; i < word.length() && i < comparedTo.length(); i++) {
			if (word.charAt(i) == comparedTo.charAt(i))
				continue;
			else if (!problem) {
				problem = true;
				if (i + 1 < comparedTo.length() && word.charAt(i) != comparedTo.charAt(i+1)) {
					break;
				}
			}
			else if (problem) {
				if (word.charAt(i) == comparedTo.charAt(i - 1)) {
					problem = false;
					// C'était une inversion
				}
				else {
					break;
				}
			}
		}
		newScore = !problem && word.length() == comparedTo.length() ?
				0
				:
					(word.length() == comparedTo.length() ?
							newScore - 0.1
							:
								newScore);
		return newScore;
	}
	
	/**
	 * Calcule la distance entre 2 mots (+ c'est grand mieux c'est)
	 * @param mot1
	 * @param mot2
	 * @return Distance
	 */
	private double prox(String mot1,String mot2) {
		int l1 = mot1.length(), l2 = mot2.length();
		
		if (l1 < SEUIL_MIN || l2 < SEUIL_MIN) {
			return 0;
		}
		else if (Math.abs(l1 - l2) > SEUIL_MAX) {
			return 0;
		}
		else {
			int i = 0;
			while ((i < Math.min(l1, l2))&& mot1.charAt(i) == mot2.charAt(i)) {
				i++;
			}
			double ret = (double)((i * 100)/ (Math.max(l1, l2)));
			if (ret > SEUIL_PROX)
				return ret;
			else return 0.0;
		}
	}
	
	private HashSet<String> findBestLemmes(String mot) {
		HashSet<String> res = new HashSet<String>(); // liste de lemmes candidats
		double highestscore = 0.0;
		
		for (String s : lexique.keySet()) {
			double tmp = prox(mot, s);
			if (tmp >= highestscore && tmp != 0.0) {
				if (tmp > highestscore) {
					res.clear();
				}
				highestscore = tmp;
				res.add(this.getLemme(s));
			}
 		}
		
		return res;
	}
	
	private int cout(Character a, Character b) {
		if (a == null || b == null) {
			return 1;
		}
		if (a.equals(b)) return 0;
		return 1;
	}
	 
	private int levenshteinDistance(String motA, String motB) {
		int i, j,
			dist[][] = new int[motA.length() + 1][motB.length() + 1];
		
		// Initialisation
		dist[0][0] = 0;
		for (i = 0; i <= motA.length(); i++) {
			dist[i][0] = i;
		}
		for (i = 0; i <= motB.length(); i++) {
			dist[0][i] = i;
		}
		
		for (i = 1; i <= motA.length(); i++) {
			for (j = 1; j <= motB.length(); j++) {
				int d1 = dist[i - 1][j - 1] + cout(motA.charAt(i-1), motB.charAt(j-1));
				int d2 = dist[i - 1][j] + cout(motA.charAt(i-1), null);
				int d3 = dist[i][j - 1] + cout(null, motB.charAt(j-1));
				dist[i][j] = Math.min(d3, Math.min(d1, d2));
			}
		}
		return dist[motA.length()][motB.length()];
	}
	
	private HashSet<String> findLemmesLevenshtein(String mot) {
		HashSet<String> res = new HashSet<String>(); // liste de lemmes candidats
		
		double lowestscore = 1000.0;
		
		for (String s : lexique.keySet()) {
			int score = this.levenshteinDistance(mot, s);
			double newScore = testInversions(mot,s,score);
			if (newScore <= lowestscore) {
				if (newScore < lowestscore) {
					res.clear();
				}
				lowestscore = newScore;
				res.add(this.getLemme(s));
			}
 		}
		
		return res;
	}
	
	public ArrayList<String> getSuggestions() {
		int nbLines = 1;
		
		for (ArrayList<String> tmp : suggestions) {
			nbLines *= tmp.size();
		}
		
		ArrayList<String> res = new ArrayList<String>();
		
		String strong1;
		String strong2;
		for (int column = 0; column < suggestions.size(); column++) {
			for (int line = 0; line < nbLines; line++) {
				
				strong1 = suggestions.get(column).size()>1?"<strong>":"";
				strong2 = strong1.isEmpty()?strong1:"</strong>";
				
				if (res.size() < line+1) {
					res.add(line, strong1+suggestions.get(column).get(line%suggestions.get(column).size())+strong2);
				}
				else {
					res.set(line, res.get(line)+strong1+suggestions.get(column).get(line%suggestions.get(column).size())+strong2);
				}
			}
		}
		return res;
	}
	
	/**
	 * Replacing words with their lemmas
	 */
	public String replaceWordsWithLemmas(String s) {
		StringBuffer requestWithoutStoplist = new StringBuffer();
		String lemme;
		
		suggestions = new ArrayList<ArrayList<String>>();
		
		HashSet<String> candidateLemmes = null; // will be populated for each word
		
		for (String str : s.split("\\s+")) {
			lemme = null;
			if (app.App.DEBUG) ConsoleOutputBuffer.getInstance().addLine("Searching a lemme for \""+str+"\"...");
			
			// 1. Keep words containing digits
			if (str.matches(".*\\d+.*")) {
				lemme = str;
				if (app.App.DEBUG) ConsoleOutputBuffer.getInstance().addLine("Keeping cause it contains digits.");
			}
			
			// 2. Try to find the perfect lemme already stored
			if (lemme == null) {
				lemme = this.getLemme(str);
				if (lemme != null) if (app.App.DEBUG) ConsoleOutputBuffer.getInstance().addLine("Found a matching lemme for \""+str+"\".");
			}
			
			// 3. Try to find lemmes with Prefix algorithm
			if (lemme == null) {
				candidateLemmes = this.findBestLemmes(str);
				if (candidateLemmes.size() > 0) {
					lemme = (String) (candidateLemmes.toArray())[0];
					if (app.App.DEBUG) ConsoleOutputBuffer.getInstance().addLine("Found with Prefix.");
				}
			}
			
			// 4. Try to find lemmes with Levenshtein algorithm
			if (lemme == null) {
				candidateLemmes = this.findLemmesLevenshtein(str);
				if (candidateLemmes.size() == 0) {
					if (app.App.DEBUG) ConsoleOutputBuffer.getInstance().addErrorLine("Giving up, nothing found.");
					lemme = str;
				}
				else {
					lemme = (String) (candidateLemmes.toArray())[0];
					if (app.App.DEBUG) ConsoleOutputBuffer.getInstance().addLine("Found with Levenshtein.");
				}
			}
			
			
			ArrayList<String> candidates = new ArrayList<String>(); // candidates that we'll add to the suggestions
			if (candidateLemmes == null || candidateLemmes.size() == 0) {
				candidates.add(lemme + " ");
			}
			else {
				for (Object c: candidateLemmes.toArray()) {
					candidates.add(((String)c)+ " ");
				}
			}
			suggestions.add(candidates);
			if (candidateLemmes != null) {
				candidateLemmes.clear();
			}
			
			if (app.App.DEBUG) ConsoleOutputBuffer.getInstance().addLine("==> "+str +" replaced with '"+lemme+"'");
			requestWithoutStoplist.append(lemme + " ");
		}
		return requestWithoutStoplist.toString();
	}
}
