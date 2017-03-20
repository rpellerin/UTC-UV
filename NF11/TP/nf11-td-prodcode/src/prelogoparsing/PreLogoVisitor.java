// Generated from grammars/PreLogo.g4 by ANTLR 4.5

  package prelogoparsing;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PreLogoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PreLogoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PreLogoParser#prestat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrestat(PreLogoParser.PrestatContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreLogoParser#impt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImpt(PreLogoParser.ImptContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreLogoParser#commande}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommande(PreLogoParser.CommandeContext ctx);
}