// Generated from grammars/PreLogo.g4 by ANTLR 4.5

  package prelogoparsing;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PreLogoParser}.
 */
public interface PreLogoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PreLogoParser#prestat}.
	 * @param ctx the parse tree
	 */
	void enterPrestat(PreLogoParser.PrestatContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreLogoParser#prestat}.
	 * @param ctx the parse tree
	 */
	void exitPrestat(PreLogoParser.PrestatContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreLogoParser#impt}.
	 * @param ctx the parse tree
	 */
	void enterImpt(PreLogoParser.ImptContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreLogoParser#impt}.
	 * @param ctx the parse tree
	 */
	void exitImpt(PreLogoParser.ImptContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreLogoParser#commande}.
	 * @param ctx the parse tree
	 */
	void enterCommande(PreLogoParser.CommandeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreLogoParser#commande}.
	 * @param ctx the parse tree
	 */
	void exitCommande(PreLogoParser.CommandeContext ctx);
}