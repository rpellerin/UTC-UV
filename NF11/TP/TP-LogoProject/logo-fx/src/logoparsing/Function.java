package logoparsing;

import java.util.ArrayList;
import java.util.List;

import logoparsing.LogoParser.Liste_instructionsContext;

import org.antlr.v4.runtime.tree.TerminalNode;


// TODO delete
public class Function {

	private String name;
	
	// Parameters
	private ArrayList<String> args;
	
	// Instructions
	private Liste_instructionsContext inst;
	
	public Function(String n, List<TerminalNode> args, Liste_instructionsContext li) {
		this.name = n;
		this.args = new ArrayList<String>();
		if (args != null && args.size() > 0) {
			for (TerminalNode tn : args) {
				this.args.add(tn.getText());
			}
		}
		this.inst = li;
	}
	
	public Integer execute(LogoTreeVisitor l) {
		l.visitListe_instructions(inst);
		return 0;
	}
	
	public ArrayList<String> getArgs() {
		return this.args;
	}
	
}
