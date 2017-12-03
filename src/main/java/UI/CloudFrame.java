package UI;

import ChineseSegmenter.ChineseSegmenter;
import VO.StockInfo;
import javafx.util.Pair;
import tfidf.Tfidf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import VO.WordCloudBuilder;


public class CloudFrame extends JFrame implements ActionListener{
    JButton SaveButton;
        ImageIcon icon;
    List<String> Resultlist;
    Color[] colors;

     public CloudFrame(StockInfo temp,StockInfo[] stock) throws IOException {
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation(new Point((int) (lx / 2)-400 , (int) (ly / 2)-350 ));// 设定窗口出现位置
        this.setSize(450, 450);
        this.setTitle("词云");
        SaveButton=new JButton("保存图片");

        SaveButton.setBounds(200,20,90,30);

//        icon=new ImageIcon();
//        img=icon.getImage();
//        super.paintComponents();
//
        //添加图片
        List<String> templist = new ArrayList<String>();
         templist=ChineseSegmenter.getWordsFromInput(temp);
         Pair<String, Double>[] pairs = new Pair[templist.size()];
         pairs= Tfidf.getResult(temp,stock);
         System.out.println(pairs[0].getKey());
          Resultlist = new ArrayList<String>();
        for(int i=0;i<40;i++)
        {
            Resultlist.add(pairs[i].getKey());
        }
          colors = new Color[10];
         for (int i = 0; i < colors.length; i++) {
             colors[i] = new Color(
                     (new Double(Math.random() * 128)).intValue() + 128,
                     (new Double(Math.random() * 128)).intValue() + 128,
                     (new Double(Math.random() * 128)).intValue() + 128);
         }

         WordCloudBuilder.buildWordCouldByWords(350,350,4,20,10,Resultlist,new Color(-1),"data"+temp.getID()+".png",colors);
         JLabel imagLabel=new JLabel();
         icon=new ImageIcon("data"+temp.getID()+".png");
         imagLabel.setIcon(icon);
         imagLabel.setBounds(70,70,300,300);




   // 保存图片
//         SaveButton.addActionListener( new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 JFileChooser jfc = new JFileChooser();
//                 jfc.setFileSelectionMode(1);
//                 jfc.showDialog(new JLabel(), "选择");
//                 File f = jfc.getSelectedFile();
//                 File PngFile=new File(f.getAbsolutePath()+"\\wordCloud.png");
//
//
//                 }
//         });

         SaveButton.addActionListener(this);




        this.add(SaveButton);
        this.add(imagLabel);
        this.setLayout(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // 相应的逻辑判断
        if(e.getSource()==SaveButton)
        {
            JFileChooser jfc = new JFileChooser();
                 jfc.setFileSelectionMode(1);
                 jfc.showDialog(new JLabel(), "选择");
                 File f = jfc.getSelectedFile();

                 WordCloudBuilder.buildWordCouldByWords(350,350,4,20,10,Resultlist,new Color(-1),f.getAbsolutePath()+"\\wordCloud.png",colors);
        }

        }


}
