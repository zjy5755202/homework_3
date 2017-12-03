package ChineseSegmenter;


import VO.StockInfo;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChineseSegmenter {

    public  static  List<String> getWordsFromInput(StockInfo temp) throws IOException {
        List<String> list = new ArrayList<String>();
        //去除停用词
        HashMap<String, String> strHashMap = new HashMap<String, String>();
        String stopWordTable =  "stopWordTable.txt";
        File f = new File(stopWordTable);
        FileInputStream fileInputStream = new FileInputStream(f);
        //读入停用词文件
        InputStreamReader isr = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader StopWordFileBr = new BufferedReader(isr);
        String stopWord = null;
        for(; (stopWord = StopWordFileBr.readLine()) != null;){
            strHashMap.put(stopWord , "_stop");
        }
        StopWordFileBr.close();
        FilterModifWord.setUpdateDic(strHashMap);
        Result result = ToAnalysis.parse(temp.getANSWER()+temp.getCONTENT()+temp.getTITLE());
        List<Term> terms= FilterModifWord.modifResult(result.getTerms());;

        //FilterModifWord.modifResult(terms);
        for (int j = 0;j<terms.size();j++) {
            //System.out.println(terms.get(j).getName());
            list.add(terms.get(j).getName());
        }
        return list;
    }

}
