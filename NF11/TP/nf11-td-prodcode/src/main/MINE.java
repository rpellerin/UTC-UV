package main;

import prelogoparsing.PreLogoParser.CommandeContext;

public class MINE {
	
	public final String couleur;
	
	public final String nb;
	public final String commande;
	public final String arg1;
	public final String arg2;
	
	public final boolean shouldRepet; 
	
	public MINE(CommandeContext cc) {
		couleur = cc.COULEUR() != null ? cc.COULEUR().getText() : "0";
		this.nb = cc.INT(2) != null ? cc.INT(2).getText() : "1";
		
		this.arg1 = cc.INT(0).getText();
		this.arg2 = cc.INT(1).getText();
		
		this.commande = cc.ID().getText();
		
		shouldRepet = !nb.equals("1");
	}
}
