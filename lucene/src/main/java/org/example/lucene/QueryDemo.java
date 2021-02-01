package org.example.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class QueryDemo {
    private static String inputTextPath="D:\\lucene\\inputData.txt";
    private static String defaultIndexDirPath="D:\\lucene\\lucene_dir";
    private static String colName="col1";


    public void query(String searchStr){
        Analyzer analyzer = new IKAnalyzer();
        File file = new File(defaultIndexDirPath);

        Path indexPath = file.toPath();
        Directory directory = null;
        DirectoryReader ireader = null;
        try {
            directory = FSDirectory.open(indexPath);
            ireader = DirectoryReader.open(directory);

            IndexSearcher isearcher = new IndexSearcher(ireader);
            // Parse a simple query that searches for "text":
            QueryParser parser = new QueryParser(colName, analyzer);
            Query query = parser.parse(searchStr);
            ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;


            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
                System.out.println("hit [ "+i+" ] "+hitDoc.get(colName));;
            }
            ireader.close();
            directory.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        QueryDemo queryDemo = new QueryDemo();
        queryDemo.query("发展银行");

    }


}
