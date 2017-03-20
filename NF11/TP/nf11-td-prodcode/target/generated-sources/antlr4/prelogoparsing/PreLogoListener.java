// Generated from PreLogo.g4 by ANTLR 4.4

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
	void enterPrestat(@NotNull PreLogoParser.PrestatContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreLogoParser#prestat}.
	 * @param ctx the parse tree
	 */
	void exitPrestat(@NotNull PreLogoParser.PrestatContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreLogoParser#impt}.
	 * @param ctx the parse tree
	 */
	void enterImpt(@NotNull PreLogoParser.ImptContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreLogoParser#impt}.
	 * @param ctx the parse tree
	 */
	void exitImpt(@NotNull PreLogoParser.ImptContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreLogoParser#commande}.
	 * @param ctx the parse tree
	 */
	void enterCommande(@NotNull PreLogoParser.CommandeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreLogoParser#commande}.
	 * @param ctx the parse tree
	 */
	void exitCommande(@NotNull PreLogoParser.CommandeContext ctx);
}