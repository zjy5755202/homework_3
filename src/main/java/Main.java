import java.awt.Color;
import java.io.IOException;
import java.util.*;

import UI.MainScene;
import VO.WordCloudBuilder;
import com.github.davidmoten.guavamini.Lists;

public class Main {

    public static void main(String[] args) throws IOException {
        MainScene main=new MainScene();


        String[] words = new String[]{
                "词频","java","考试","小明","毕业"
        };
        List<String> s = Lists.newArrayList(words);
        Color[] colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
        }

        WordCloudBuilder.buildWordCouldByWords(200,200,4,20,10,s,new Color(-1),"data.png",colors);
    }

}





