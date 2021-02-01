package org.example.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

public class IkanalyzerDemo {

    public static void main(String[] args) throws IOException {

        IKAnalyzer analyzer=new IKAnalyzer();

        //2.调用Analyzer对象的tokenStream方法获取TokenStream对象，此对象包含了所有的分词结果
        TokenStream tokenStream = analyzer.tokenStream("col1", "展银行");
        //3.给tokenStream对象设置一个指针，指针在哪当前就在哪一个分词上
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4.调用tokenStream对象的reset方法，重置指针，不调用会报错
        tokenStream.reset();
        //5.利用while循环，拿到分词列表的结果  incrementToken方法返回值如果为false代表读取完毕  true代表没有读取完毕
        while (tokenStream.incrementToken()){
            System.out.println(charTermAttribute.toString());
        }
        //6.关闭
        tokenStream.close();

    }



}
