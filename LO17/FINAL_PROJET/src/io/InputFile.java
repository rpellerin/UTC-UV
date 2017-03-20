package io;
import java.io.*;
import java.util.ArrayList;

/**
 * Read a file
 * @author romain
 *
 */
public class InputFile {
	private String in;
	
	public InputFile(String input) {
		this.in = input;
	}
	
	public ArrayList<String> read() throws IOException {
		String chaine;
		ArrayList<String> res = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(this.in))) {
	        while ((chaine=br.readLine())!=null) {
	        	String s = chaine.toString().trim();
	        	res.add(s);
	        }
		}
		return res;
	}
}