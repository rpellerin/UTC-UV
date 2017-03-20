package logoparsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.Group;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FuncProc.Function;
import logogui.Log;
import logogui.Traceur;
import logoparsing.LogoParser.ArouleContext;
import logoparsing.LogoParser.AvContext;
import logoparsing.LogoParser.BcContext;
import logoparsing.LogoParser.BooleanExprContext;
import logoparsing.LogoParser.DonneContext;
import logoparsing.LogoParser.FccContext;
import logoparsing.LogoParser.FposContext;
import logoparsing.LogoParser.FunContext;
import logoparsing.LogoParser.FunctionContext;
import logoparsing.LogoParser.HazContext;
import logoparsing.LogoParser.IfElseContext;
import logoparsing.LogoParser.InstructionContext;
import logoparsing.LogoParser.IntContext;
import logoparsing.LogoParser.LcContext;
import logoparsing.LogoParser.Liste_instructionsContext;
import logoparsing.LogoParser.LoopContext;
import logoparsing.LogoParser.MutlContext;
import logoparsing.LogoParser.ParentheseContext;
import logoparsing.LogoParser.ProcContext;
import logoparsing.LogoParser.ReContext;
import logoparsing.LogoParser.RepeteContext;
import logoparsing.LogoParser.ReturnContext;
import logoparsing.LogoParser.SumContext;
import logoparsing.LogoParser.TantQueContext;
import logoparsing.LogoParser.TdContext;
import logoparsing.LogoParser.TgContext;
import logoparsing.LogoParser.VarContext;
import logoparsing.LogoParser.VeContext;

public class LogoTreeVisitor extends LogoBaseVisitor<Integer> {
	
	private static final String RETURN_VAL = "return";
	
	Traceur traceur;
	ParseTreeProperty<Object> atts = new ParseTreeProperty<Object>();
	Stack<Integer> loop = new Stack<Integer>(); // pour loop
	
	
	Stack<TableSymboles> stackVars = new Stack<TableSymboles>(); // stack de liste de variables
	
	HashMap<String, Function> funcProc = new HashMap<String, Function>(); // liste des proc�dures et functions

	public LogoTreeVisitor() {
		super();
		TableSymboles vars = new TableSymboles(); // liste des variables actuellement dans le "contexte" d'�xecution
		stackVars.add(vars);
	}
	public void initialize(Group g) {
	      traceur = new Traceur();
	      traceur.setGraphics(g);
    }
	public void setAttValue(ParseTree node, Object value) { 
		atts.put(node, value);
	}
	public Boolean getBooleanValue(ParseTree node) { return (Boolean)atts.get(node); }
	
	@Override
	public Integer visitHaz(HazContext ctx) {
		visitChildren(ctx);
		setAttValue(ctx, ThreadLocalRandom.current().nextInt(0, getAttValue(ctx.exp()) + 1));
		return 0;
	}
	public int getAttValue(ParseTree node) {
		return (int)atts.get(node);
	}
	@Override
	public Integer visitAv(AvContext ctx) {
		visitChildren(ctx);
		traceur.avance(getAttValue(ctx.exp()));
		Log.appendnl("visitAv");
		return 0;
	}
	@Override
	public Integer visitTd(TdContext ctx) {
		visitChildren(ctx);
		traceur.td(getAttValue(ctx.exp()));
		Log.append("visitTd\n" );
		return 0;
	}
	@Override
	public Integer visitTg(TgContext ctx) {
		visitChildren(ctx);
		traceur.tg(getAttValue(ctx.exp()));
		Log.append("visitTg\n" );
		return 0;
	}
	@Override
	public Integer visitLc(LcContext ctx) {
		visitChildren(ctx);
		traceur.lc();
		Log.append("visitLc\n" );
		return 0;
	}
	@Override
	public Integer visitBc(BcContext ctx) {
		visitChildren(ctx);
		traceur.bc();
		Log.append("visitBc\n" );
		return 0;
	}
	@Override
	public Integer visitVe(VeContext ctx) {
		visitChildren(ctx);
		traceur.ve();
		Log.append("visitVe\n" );
		return 0;
	}
	
	@Override
	public Integer visitRe(ReContext ctx) {
		visitChildren(ctx);
		traceur.re(getAttValue(ctx.exp()));
		Log.appendnl("visitRe");
		return 0;
	}
	@Override
	public Integer visitFpos(FposContext ctx) {
		visitChildren(ctx);
		traceur.fpos(getAttValue(ctx.exp(0)), getAttValue(ctx.exp(1)));
		Log.appendnl("visitFpos");
		return 0;
	}
	@Override
	public Integer visitFcc(FccContext ctx) {
		visitChildren(ctx);
		traceur.fcc(getAttValue(ctx.exp()));
		Log.append("visitFcc\n" );
		return 0;
	}
	@Override
	public Integer visitMutl(MutlContext ctx) {
		visitChildren(ctx);
		int left = getAttValue(ctx.exp(0));
		int right = getAttValue(ctx.exp(1));
		setAttValue(ctx, ctx.getChild(1).getText().equals("*") ?
			left * right : left / right);
		return 0;
	}
	@Override
	public Integer visitSum(SumContext ctx) {
		visitChildren(ctx);
		int left = getAttValue(ctx.exp(0)); // .exp() contient le seul fils expression si unique, sinon faire .exp(x)
		int right = getAttValue(ctx.exp(1));
		setAttValue(ctx, ctx.getChild(1).getText().equals("+") ?
			left + right : left - right);
		return 0;
	}
	
	@Override
	public Integer visitInt(IntContext ctx) {
		visitChildren(ctx);
		String intText = ctx.INT().getText();
		setAttValue(ctx, Integer.valueOf(intText));
		return 0;
	}
	
	@Override
	public Integer visitParenthese(ParentheseContext ctx) {
		visitChildren(ctx);
		setAttValue(ctx, getAttValue(ctx.exp()));
		return 0;
	}
	@Override
	public Integer visitAroule(ArouleContext ctx) {
		visit(ctx.atom());
		setAttValue(ctx, getAttValue(ctx.atom()));
		return 0;
	}
	@Override
	public Integer visitRepete(RepeteContext ctx) {
		System.out.println("repete");
		visit(ctx.atom());
		Integer nb = getAttValue(ctx.atom());
		for (int i = 0; i < nb; i++) {
			loop.add(i+1);
			if (visit(ctx.liste_instructions()) != 0) {
				System.out.println("Returning -1");
				return -1;
			}
		}
		return 0;
	}
	@Override
	public Integer visitLoop(LoopContext ctx) {
		setAttValue(ctx, loop.pop().intValue());
		return 0;
	}
	@Override
	public Integer visitDonne(DonneContext ctx) {
		visitChildren(ctx);
		String var = ctx.VAR().getText().substring(1);
		stackVars.peek().put(var, getAttValue(ctx.exp()));
		return 0;
	}
	@Override
	public Integer visitVar(VarContext ctx) {
		String var = ctx.USE().getText().substring(1);
		System.err.println("accessing "+var);
		setAttValue(ctx, stackVars.peek().containsKey(var) ? stackVars.peek().get(var) : 0);
		return 0;
	}
	@Override
	public Integer visitBooleanExpr(BooleanExprContext ctx) {
		visitChildren(ctx);
		int left = getAttValue(ctx.exp(0));
		int right = getAttValue(ctx.exp(1));
		boolean result = true;
		String op = ctx.BOOLEAN().getText();
		if(op.equals("<")){
			result = left < right;
		}
		else if(op.equals(">")){
			result = left > right;
		}
		else if(op.equals("==")){
			result = left == right;
		}
		else if(op.equals("!=")){
			result = left != right;
		}
		else if(op.equals( "<=")){
			result = left <= right;
		}
		else if(op.equals(">=")){
			result = left >= right;
		}
		else {
			System.err.println("Erreur booleen");
		}
		setAttValue(ctx, new Boolean(result));
		return 0;
	}
	@Override
	public Integer visitIfElse(IfElseContext ctx) {
		System.out.println("ifElse");
		visit(ctx.booleanExpr());
		Boolean cond = getBooleanValue(ctx.booleanExpr());
		if(cond.booleanValue()){
			if (visit(ctx.liste_instructions(0)) != 0)
				return -1;
		}
		else if (ctx.liste_instructions().size() > 1) {
			if (visit(ctx.liste_instructions(1)) != 0) {
				return -1;
			}
		}
		return 0;
	}
	
	@Override
	public Integer visitTantQue(TantQueContext ctx) {
		System.out.println("tantQue");
		visit(ctx.booleanExpr());
		while(getBooleanValue(ctx.booleanExpr()).booleanValue()){
			if (visit(ctx.liste_instructions()) != 0)
				return -1;
			visit(ctx.booleanExpr());
		}
		return 0;
	}
	
	// on prend le visitFunctions par d�faut car il va appeler les visitFunction() tout seul
	
	// methode ci dessous appel�e quand on APPELLE une fonction
	@Override
	public Integer visitProc(ProcContext ctx) {
		TableSymboles vars = new TableSymboles();
		
		String procName = ctx.NAME().getText();
		Function p = funcProc.get(procName);
		System.out.println("Appel de la proc "+procName);
		
		// attacher les variables dont on a besoin dans cette nouvelle table
		if (ctx.exp() != null) {
			
			ArrayList<String> args = p.getArgs();
			
			System.out.println("size: "+String.valueOf(ctx.exp().size()));
			for (int i = 0; i < ctx.exp().size(); i++) {
				System.out.println(args.get(i));
				visit(ctx.exp(i));
				vars.put(args.get(i).substring(1), getAttValue(ctx.exp(i)));
				System.err.println("Arg_name= "+args.get(i).substring(1));
				System.err.println("Arg_value= "+getAttValue(ctx.exp(i)));
			}
		}
		
		// call proc
		stackVars.push(vars);
		
		p.execute(this);
		
		// Unstack the local stack and restore the previous one
		stackVars.pop();
		vars = stackVars.peek();
		return 0;
	}
	
	
	
	@Override
	public Integer visitFun(FunContext ctx) {
		TableSymboles vars = new TableSymboles();
		
		String funcName = ctx.NAME().getText();
		Function f = funcProc.get(funcName);

		System.out.println("Appel de la func "+funcName);
		
		// attacher les variables dont on a besoin dans cette nouvelle table
		if (ctx.exp() != null) {
			
			ArrayList<String> args = f.getArgs();
			
			System.out.println("Number of parameters: "+String.valueOf(ctx.exp().size()));
			for (int i = 0; i < ctx.exp().size(); i++) {
				System.out.println(args.get(i));
				visit(ctx.exp(i));
				vars.put(args.get(i).substring(1), getAttValue(ctx.exp(i)));
				System.err.println("Arg_name= "+args.get(i).substring(1));
				System.err.println("Arg_value= "+getAttValue(ctx.exp(i)));
			}
		}
		
		// call proc
		stackVars.push(vars);
		
		f.execute(this);
		Integer returnValue = stackVars.peek().get(RETURN_VAL);
		if (returnValue != null) {
			System.out.println("returnValue found");
			setAttValue(ctx, returnValue);
		}
		else {
			System.out.println("returnValue NOT found");
		}
		
		// Unstack the local stack and restore the previous one
		stackVars.pop();
		vars = stackVars.peek();
		return 0;
	}
	@Override
	public Integer visitReturn(ReturnContext ctx) {
		System.out.println("Visite return");
		visitChildren(ctx);
		Integer previous = stackVars.peek().put(RETURN_VAL, getAttValue(ctx.exp()));
		if (previous != null) {
			System.out.println("Weird, there was a value previously stored as a return value. ("+String.valueOf(previous)+")");
			stackVars.peek().put(RETURN_VAL, previous);
			return -2;
		}
		return 0;
	}
	@Override
	public Integer visitListe_instructions(Liste_instructionsContext ctx) {
		System.out.println("Visite list instructions");
		//visitChildren(ctx);
		int ret;
		if (stackVars.peek().get(RETURN_VAL) != null) {
			System.out.println("YAY");
			return -2; // empeche l'execution d'instructions apr�s un "rends" // TODO cette ligne est LIKELY inutile
		}
		for (InstructionContext ic : ctx.instruction()) {
			ret = visit(ic);
			if (ret == -1 || ic instanceof ReturnContext) {
				System.out.println("la liste d'instructions contenait un return, on abort");
				return -1; // quand un visitReturn aura renvoy� un -1
			}
		}
		return 0;
	}
	@Override
	public Integer visitFunction(FunctionContext ctx) {
		System.out.println("Visiting proc");
		Function p = new Function(ctx.NAME().getText(), ctx.USE(), ctx.liste_instructions());
		funcProc.put(ctx.NAME().getText(), p);
		return 0;
	}
}
