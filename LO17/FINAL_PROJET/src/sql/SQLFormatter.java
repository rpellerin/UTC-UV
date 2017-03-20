package sql;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.ConsoleOutputBuffer;

public class SQLFormatter {
	
	private final static String DATE       = "date";
	private final static String DATE_JOUR  = "datejour";
	private final static String DATE_MOIS  = "datemois";
	private final static String DATE_ANNEE = "dateannee";
	
	private final static String[] months = {
			"janvier",
			"fevrier",
			"mars",
			"avril",
			"mai",
			"juin",
			"juillet",
			"aout",
			"septembre",
			"octobre",
			"novembre",
			"decembre"
		}; // Without French accents
	
	/**
	 * Used to format a {@link String} created using lemmas in order to be sent
	 * to the grammar
	 * @param req The {@link String} composed of lemmas
	 * @return The formatted {@link String} ready to be processed by the grammar
	 */
	public static String preFormat(String req) {
		req = req.replaceAll("\\s+", " ")
				.trim();
		
		StringBuffer finalRes = new StringBuffer();
		
		String date      = null,
			   lastDate  = null,
			   lastToken = null;
		
	    
		req = req.replaceAll("annee (\\d{4})", "date $1")
				 .replaceAll("année (\\d{4})", "date $1")
				 .replace("et rubrique", "rubrique")
				 .replace("et contenir", "contenir")
				 .replace("et date", "date");
		
		for (String mois : months) {
			req = req.replaceAll("mois ("+mois+")", "date $1");
		}
		
		for (int i = 0; i < months.length; i++) {
			req = req.replaceAll(months[i], "0"+((String.valueOf(i+1).length() == 1) ? "0" : "")+String.valueOf(i+1));
		}
		
		for (String token: req.split("\\s+")) {
			date = getTypeDate(token);
			
			if (lastToken == null) {
				// most likely token is "vouloir"
				date = null;
			}
			else if (lastToken.equals("contenir")) {
				// we're looking for a word
				date = null;
			}else if (lastToken.equals("date")) {
				// cool already here
			}
			else if (date != null && lastDate == null && !lastToken.equals("ou")) {
				finalRes.append((lastToken.equals("date") ? "" : DATE)+" ");
			}
			if (date != null) {
				token = token.toLowerCase();
			}
			
			finalRes.append(token+" ");
			
			lastDate = date;
			lastToken = token;
		}
		
		return finalRes
				.toString()
				.replaceAll("\\s+", " ")
				.trim();
	}
	
	private static String getTypeDate(String token) {
		String tempToken = token.toLowerCase();
		tempToken = Normalizer
			           .normalize(tempToken, Normalizer.Form.NFD)
			           .replaceAll("\\p{M}", "");
		
		for (String month: months) {
			if (tempToken.equals(month)) {
				return DATE_MOIS;
			}
			if (tempToken.toLowerCase().equals(month)) {
				return DATE_MOIS;
			}
		}
		
		if (tempToken.matches("(1|2)\\d{3}")) {
			return DATE_ANNEE;
		}
		
		if (tempToken.matches("(\\d|1\\d|2\\d|30|31)")) {
			return DATE_JOUR;
		}
		if (tempToken.matches("0[0-3][0-9]")) {
			return DATE_MOIS;
		}
		return null;
	}
	
	public static String addOrRemoveLinksBetweenTables(String req) {
//		String regex = "from\\s+(.*)\\s+where";
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(req);
//		m.find();
		if (!(req.contains("jour") || req.contains("mois") || req.contains("annee"))) {
			req = req.replaceFirst(", date d where d\\.fichier = te\\.fichier( and)?", " where ");
		}
		System.err.println(req);
		String regex = "select(.*?)where";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(req);
		m.find();
		ConsoleOutputBuffer.getInstance().printBufferOnStandardOutput();
		String needed = m.group(1);
		
		int i = 0;
		if (req.contains("INTERSECT")) {
			String regex2 = "\\(\\(.*?INTERSECT\\s+(.*?)\\s+\\)\\)";
			Pattern p2 = Pattern.compile(regex2);
			Matcher m2 = p2.matcher(req);
			
			while (m2.find()) {
				String mot = m2.group(1);
				
				req = req.replaceFirst("\\(\\(.*?INTERSECT.*?\\)\\)", "");
				req = "select table"+(i)+".* from ("+req+") as table"+(i++)+" INNER JOIN "
						+ "(select "+needed+" where mot = "+mot+") as table"+(i++)+ " "
								+ "on table"+(i-2)+".fichier = table"+(i-1)+".fichier ";
			}
			System.out.println(req);
		}
		
		return req;
	}
	
	/**
	 * Used to format a {@link String} created using a grammar in order to be sent
	 * to the DBMS
	 * @param req The {@link String} outputted from the grammar
	 * @return The formatted {@link String} ready to be sent as a SQL request
	 */
	public static String postFormat(String req) {
		req = req
				.replaceAll("^\\(", "")
				.replaceAll("$\\)", "")
				.replaceAll(" \\(", "")
				.replaceAll(" \\)", "")
				.replaceAll("!\\(", "(")
				.replaceAll("!\\)", ")")
				.trim()
				.replaceAll("and\\s+and", "and ")
				.replaceAll("and$", "")
				.replaceAll("where$", "")
				.trim();
		
		return addOrRemoveLinksBetweenTables(req).trim().replaceAll("where$", "");
	}
}
