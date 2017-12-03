package tfidf;

import ChineseSegmenter.ChineseSegmenter;
import VO.StockInfo;
import VO.StockSorter;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tfidf {
    //计算tf idf
    public static Pair<String, Double>[] getResult(StockInfo temp, StockInfo[] stockInfos) throws IOException {
        List<String> words = ChineseSegmenter.getWordsFromInput(temp);
        Map<String, Double> term_freq_map = new HashMap<String, Double>();// 词，该词在该文档中出现的频率
        Map<String, Double> tfidf_freq_map = new HashMap<String, Double>(); // 词，文档频率
        int doc_numbers_total = stockInfos.length; // 总的文档数目
        Double doc_numbers_term = 0.0;// 包含该词条的文档数目
        String word = "";


        //计算各个单词的tf
        for (int i = 0; i < words.size(); i++) {
            word = words.get(i);
            if (term_freq_map.containsKey(word)) {
                Double value = term_freq_map.get(word) + 1.0;
                term_freq_map.put(word, value);
            } else {
                term_freq_map.put(word, 1.0);
            }
        }


        //计算各个单词的idf
        for (int i = 0; i < words.size(); i++) {
            word = words.get(i);
            double idfcount = 0;
            if (tfidf_freq_map.get(word) != null)
                continue;
            for (StockInfo stockInfo : stockInfos) {
                if (stockInfo.getCONTENT().contains(word)) idfcount++;
            }

            double tf = term_freq_map.get(word) / words.size();
            double idf = Math.log10(stockInfos.length / (1 + idfcount));
            double result = tf * idf;
            tfidf_freq_map.put(word, result);
        }


        //排序

        Pair<String, Double>[] pairs = new Pair[tfidf_freq_map.size()];
        int n = 0;
        for (Map.Entry<String, Double> entry : tfidf_freq_map.entrySet()) {
            Pair<String, Double> pair = new Pair<String, Double>(entry.getKey(), entry.getValue());
            pairs[n] = pair;
            n++;
        }

        new StockSorter().sort(pairs);
        return pairs;
    }
}
