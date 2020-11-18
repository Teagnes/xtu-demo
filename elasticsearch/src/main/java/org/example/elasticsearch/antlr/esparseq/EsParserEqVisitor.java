// Generated from D:/git-workspace/xtu-demo/elasticsearch/src/main/resources\EsParserEq.g4 by ANTLR 4.8
package org.example.elasticsearch.antlr.esparseq;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EsParserEqParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EsParserEqVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code VAL}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVAL(EsParserEqParser.VALContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OP}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOP(EsParserEqParser.OPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code KEY}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKEY(EsParserEqParser.KEYContext ctx);
}