package org.example.elasticsearch.antlr.esparseq.vistor;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.example.elasticsearch.antlr.esparseq.EsParserEqBaseVisitor;
import org.example.elasticsearch.antlr.esparseq.EsParserEqParser;

public class QueryBuilderVistor extends EsParserEqBaseVisitor<NODE> {

    @Override
    public NODE visitVAL(EsParserEqParser.VALContext ctx) {
        String text = ctx.getText();
        NODE node = new NODE();
        node.setVal(text);
        return node;
    }

    @Override
    public NODE visitOP(EsParserEqParser.OPContext ctx) {
        EsParserEqParser.ExprContext expr = ctx.expr(0);
        NODE visit = visit(expr);
        EsParserEqParser.ExprContext expr1 = ctx.expr(1);
        NODE visit1 = visit(expr1);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(visit.getKey(), visit1.getVal());
        NODE node = new NODE();
        node.setQueryBuilder(termQueryBuilder);
        return node;

    }

    @Override
    public NODE visitKEY(EsParserEqParser.KEYContext ctx) {
        String text = ctx.getText();
        NODE node = new NODE();
        node.setKey(text);
        return node;
    }
}
