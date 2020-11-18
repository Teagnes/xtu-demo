package org.example.elasticsearch.antlr.esparseq.vistor;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.elasticsearch.index.query.QueryBuilder;
import org.example.elasticsearch.antlr.esparseq.EsParserEqLexer;
import org.example.elasticsearch.antlr.esparseq.EsParserEqParser;

public class QueryBuilderUtil {

    public static QueryBuilder parser(String expression){
        CharStream inputStream = CharStreams.fromString(expression);
        EsParserEqLexer esParserEqLexer = new EsParserEqLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(esParserEqLexer);
        EsParserEqParser parser = new EsParserEqParser(tokenStream);
        EsParserEqParser.ExprContext parserTree = parser.expr();
        QueryBuilderVistor queryBuilderVistor = new QueryBuilderVistor();
        NODE visit1 = queryBuilderVistor.visit(parserTree);
        return visit1.getQueryBuilder();
    }



}
