package FuncProc;

import java.util.ArrayList;
import java.util.List;

import logoparsing.LogoTreeVisitor;
import logoparsing.LogoParser.Liste_instructionsContext;
import logoparsing.LogoParser.ReturnContext;

import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Cette classe repr�sente une fonction ou une proc�dure (qui n'est autre qu'une fonction qui ne renvoit rien)
 * @author rpelleri
 *
 */
public class Function {

	protected String name;
	
	// Parameters
	protected ArrayList<String> args;
	
	// Instructions
	protected Liste_instructionsContext inst;
	
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

		l.visit(inst);
		return 0;
	}
	
	public ArrayList<String> getArgs() {
		return this.args;
	}
	
}
