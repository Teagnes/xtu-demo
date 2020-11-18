// Generated from D:/git-workspace/xtu-demo/elasticsearch/src/main/resources\EsParserEq.g4 by ANTLR 4.8
package org.example.elasticsearch.antlr.esparseq;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EsParserEqParser}.
 */
public interface EsParserEqListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code VAL}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVAL(EsParserEqParser.VALContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VAL}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVAL(EsParserEqParser.VALContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OP}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOP(EsParserEqParser.OPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OP}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOP(EsParserEqParser.OPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code KEY}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterKEY(EsParserEqParser.KEYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code KEY}
	 * labeled alternative in {@link EsParserEqParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitKEY(EsParserEqParser.KEYContext ctx);
}