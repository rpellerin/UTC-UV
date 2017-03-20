package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import prelogoparsing.LogoGenerator;
import prelogoparsing.PreLogoLexer;
import prelogoparsing.PreLogoParser;
import prelogoparsing.PreLogoParser.PrestatContext;

public class LogoGeneratorMain {
    public static String fileName = "programs/prelogo.txt";
	public static void main(String[] args) {
		generate();
	}
public static void generate() {
	ANTLRInputStream str;
	try {
		str = new ANTLRInputStream(new FileReader(fileName));
		PreLogoLexer lexer = new PreLogoLexer(str);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PreLogoParser parser = new PreLogoParser(tokens);

		PrestatContext tree = parser.prestat();
		LogoGenerator visitor = new LogoGenerator();
	    visitor.visit(tree);
	    System.out.println(visitor.render());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
