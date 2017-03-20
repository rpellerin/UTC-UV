package io;
import java.io.*;

import utils.ConsoleOutputBuffer;

public class STDin {
	
	private String chaine;
	private InputStream in;
	
	public STDin(InputStream in) {
		this.in = in;
	}
	
	public void prompt() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(this.in));
			ConsoleOutputBuffer.getInstance().addLine("NEW REQUEST TO PERFORM IN NATURAL LANGAGE: ");
			chaine = br.readLine();
		}catch(EOFException e) {
	        br.close();
	    }
	}
	
	public STDin toLowerCase() {
		this.chaine = this.chaine.toLowerCase();
		return this;
	}

	@Override
	public String toString() {
		return chaine;
	}
}