package GetData;

import VO.StockInfo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GetDataFormFile {
    //将文件解析为StockInfo数组
    public static StockInfo[] getStockInfoFromFile(File file) throws IOException {
        int nCount= 0,x = 0;

        Scanner inputFromFile1 =  new Scanner(file,"UTF-8");
        while(inputFromFile1.hasNextLine()){
            inputFromFile1.nextLine();
            nCount++;
        }
        StockInfo[] stockInfos = new StockInfo[nCount-1];
        Scanner inputFromFile2 =  new Scanner(file,"UTF-8");
        inputFromFile2.nextLine();
        for (int i =0;i<nCount-1;i++){
            String content = inputFromFile2.nextLine();
            stockInfos[i] = new StockInfo(content.split("\\t"));
        }
        return stockInfos;
    }
}
