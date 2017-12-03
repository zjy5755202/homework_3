package UI;

import ChineseSegmenter.ChineseSegmenter;
import GetData.GetDataFormFile;
import SearchNews.SearchNew;
import VO.SearchResultLabel;
import VO.StockInfo;
import com.github.davidmoten.guavamini.Lists;
import javafx.util.Pair;

import javax.naming.directory.SearchResult;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScene extends JFrame  {
    JMenuBar Jmb;
    JMenu ChooseFile;
    JMenuItem Choose;
    JFileChooser jfc;
    JLabel FindLabel;
    JTextField Find;
    JButton Findbutton;
    JPanel SearchNews;
    JPanel RecommondNews;
    private static StockInfo[] stocks;
    private double[] SearchResult;
    private int[] SearchNewsId = new int[10];

    public  MainScene() {
        //Frame 属性
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation(new Point((int) (lx / 2)-400 , (int) (ly / 2)-350 ));// 设定窗口出现位置
        this.setSize(600, 640);

        this.setTitle("朱江源-java第三次实验");


        //菜单栏
        Jmb = new JMenuBar();
        ChooseFile = new JMenu("文件");
        Choose=new JMenuItem("打开文件");
        ChooseFile.add(Choose);
        Jmb.add(ChooseFile);

        //搜索栏
        FindLabel = new JLabel("关键字搜索");
        Find = new JTextField(20);
        Findbutton = new JButton("查询");

        FindLabel.setBounds(60, 30, 80, 25);
        Find.setBounds(140, 30, 300, 25);
        Findbutton.setBounds(450, 30, 80, 25);

//搜索结果
        SearchNews=new JPanel();
        SearchNews.setBounds(50,60,500,450);
        SearchNews.setBackground(Color.white);
        this.add(SearchNews);


//添加事件监听

        Choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "选择");
                File file = jfc.getSelectedFile();
                try {
                    stocks=GetDataFormFile.getStockInfoFromFile(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        });

        Findbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String Keyword = Find.getText();
               try {
                   SearchNews.removeAll();
                   List<String> templist = SearchNew.Spiltword(Keyword);
                   SearchResult = SearchNew.Search(templist, stocks);
                   SearchNewsId=SearchNew.sort(SearchResult);
                   for(int i=0;i<10;i++)
                   {

                       SearchResultLabel searchResultLabel=new SearchResultLabel(stocks[SearchNewsId[i]],stocks,templist);
                       searchResultLabel.setBounds(10,40*i,480,30);
                       searchResultLabel.setVerticalAlignment(SwingConstants.TOP);
                       //添加点击事件








                      SearchNews.setLayout(null);
                       SearchNews.add(searchResultLabel);
                       SearchNews.setVisible(false);
                       SearchNews.setVisible(true);

                       System.out.println();
                   }
              System.out.println();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }



            }

        });


        this.setJMenuBar(Jmb);
        this.add(FindLabel);
        this.add(Find);
        this.add(Findbutton);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}