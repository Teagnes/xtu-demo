package org.example.elasticsearch.antlr.esparseq.vistor;

import org.elasticsearch.index.query.QueryBuilder;

public class NODE {
    private QueryBuilder queryBuilder;

    private String key;

    private String val;


    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "NODE{" +
                "queryBuilder=" + queryBuilder +
                ", key='" + key + '\'' +
                ", val='" + val + '\'' +
                '}';
    }
}
