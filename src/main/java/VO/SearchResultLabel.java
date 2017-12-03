package VO;

import UI.CloudFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

public class SearchResultLabel extends JLabel{
    public int NewId;
    private StockInfo temp1;
    private String[] Keywords;
    private List<String> Keylist;

    public void set(StockInfo temp) throws IOException {
        String Sourse=temp.getID() + " " + temp.getTITLE();
        String Result= ChangeToRed(Sourse,Keylist);
        this.setText("<html>"+Result+"</html>");

    }

    public SearchResultLabel(StockInfo temp, final StockInfo[] stocks, List<String> list) throws IOException {
        this.Keylist=list;
        this.NewId = Integer.parseInt(temp.getID());
        this.set(temp);
        temp1 = temp;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println(temp1.getID());
                    CloudFrame cloudFrame = new CloudFrame(stocks[Integer.parseInt(temp1.getID())], stocks);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }





            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }


    private static String ChangeToRed(String strSourse, List<String> keyWords){
        if (strSourse == null) return null;
        String strResult = strSourse;
        for (String keyWord: keyWords) {
            strResult = Change(strResult,keyWord);
        }
        return strResult;
    }

    private static String Change(String strToShow, String keyWord){

        //存储转化的结果
        StringBuffer result = new StringBuffer(strToShow.length());

        int index = strToShow.indexOf(keyWord,0);
        int indexBegin = 0;
        while (index >= 0){
            result.append(strToShow.toCharArray(),indexBegin,index-indexBegin)
                    .append("<font color='red'><b>")
                    .append(keyWord)
                    .append("</b></font>");
            indexBegin = index+keyWord.length();
            index = strToShow.indexOf(keyWord,indexBegin);
        }
        result.append(strToShow.toCharArray(),indexBegin,strToShow.length()-indexBegin);
        return result.toString();
    }

}
