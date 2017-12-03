package SearchNews;

import VO.StockInfo;
import VO.StockSorter;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

import ChineseSegmenter.ChineseSegmenter;
import ChineseSegmenter.FilterModifWord;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class SearchNew {

    public static List<String> Spiltword(String keywords) throws IOException {
        List<String> list = new ArrayList<>();
        Result result = ToAnalysis.parse(keywords);
        List<Term> terms = FilterModifWord.modifResult(result.getTerms());
        ;
        for (int j = 0; j < terms.size(); j++) {

            list.add(terms.get(j).getName());
        }
        return list;
    }


    public static double[] Search(List<String> keywords, StockInfo[] stocks) throws IOException {
        double[] Result = new double[stocks.length];
        for (int i = 0; i < stocks.length; i++) {
            //对stocks[i]分词
            List<String> list = new ArrayList<String>();
            list= ChineseSegmenter.getWordsFromInput(stocks[i]);
            //统计词频
            double tfcount = 0;
            for (int j = 0; j < keywords.size(); j++) {
                String tempword = keywords.get(j);
                for (int m = 0; m < list.size(); m++) {
                    if (list.get(m)==tempword)
                        tfcount++;
                }
            }
            Result[i] =  tfcount /(double) list.size();
        }

        return Result;

    }

    public static int[] sort(double []temp)
    {   int []result=new int [10];
        HashMap map=new HashMap();
        for(int i=0;i<temp.length;i++)
        {
            map.put(temp[i],i); //将值和下标存入Map
        }

       //排列
        List list=new ArrayList();
        Arrays.sort(temp); //升序排列
        for(int i=0;i<temp.length;i++)
        {
            list.add(temp[i]);
        }
        Collections.reverse(list); //逆序排列,变为降序
        for(int i=0;i<list.size();i++)
        {
            temp[i]=(Double) list.get(i);
        }

//查找原始下标
        for(int i=0;i<10;i++)
        {
            result[i]=(int)map.get(temp[i]);
        }


        return  result;
    }
}
