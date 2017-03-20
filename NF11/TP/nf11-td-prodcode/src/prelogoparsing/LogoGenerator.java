package prelogoparsing;

import main.MINE;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import prelogoparsing.PreLogoParser.CommandeContext;
import prelogoparsing.PreLogoParser.ImptContext;
import prelogoparsing.PreLogoParser.PrestatContext;

public class LogoGenerator extends PreLogoBaseVisitor<Integer> {
	STGroup group = new STGroupFile("templates/prelogo.stg");
	ST logost = group.getInstanceOf("logo");
	
	public LogoGenerator() {
		super();
	}
	public String render() {
		return logost.render();
	}
	@Override
	public Integer visitImpt(ImptContext ctx) {
		System.out.println("visitImpt: "+ctx.ID().getText());
		
		ST a = group.getInstanceOf(ctx.ID().getText());
		logost.add("procedures", a.render());
		return 0;
	}
	@Override
	public Integer visitCommande(CommandeContext ctx) {
		System.out.println("visitCommande: "+ctx.ID().getText());
		
		ST a = group.getInstanceOf(ctx.ID().getText());
		logost.add("procedures", a.render());
		
		MINE m = new MINE(ctx);
		logost.add("commandes", m);
		
		return 0;
	}
	@Override
	public Integer visitPrestat(PrestatContext ctx) {
		visitChildren(ctx);
		return 0;
	}
}
