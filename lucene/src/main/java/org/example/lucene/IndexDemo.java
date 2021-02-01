package org.example.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.nio.file.Paths;

public class IndexDemo {
    private static String inputTextPath="D:\\lucene\\inputData.txt";
    private static String defaultIndexDirPath="D:\\lucene\\lucene_dir";
    private static String colName="col1";

    public static void main(String[] args) {
        IndexDemo indexDemo = new IndexDemo();
        indexDemo.index();
    }



    public void index(){
        IndexWriter indexWriterInstance = null;
        try {
            FileReader fileReader = new FileReader(inputTextPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            indexWriterInstance = getIndexWriterInstance();
            while ((line=bufferedReader.readLine())!=null){
                Document doc = createDoc(line);
                indexWriterInstance.addDocument(doc);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(indexWriterInstance!= null){
                try {
                    indexWriterInstance.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }



    public IndexWriter getIndexWriterInstance() {
        File file = new File(defaultIndexDirPath);
        if(file.exists()){
            boolean delete = file.delete();
            System.out.println(delete);
        }
        file.mkdir();
        try{
            Directory directory = FSDirectory.open(Paths.get(defaultIndexDirPath));
            IKAnalyzer ikAnalyzer = new IKAnalyzer();
            ikAnalyzer.useSmart();
            //设置相应的分词器
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(ikAnalyzer);
            return new IndexWriter(directory, indexWriterConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Document createDoc(String inputString){
        Document document = new Document();
        document.add(new TextField(colName ,inputString, Field.Store.YES));
        return document;
    }



}
