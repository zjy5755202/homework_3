package VO;

import javafx.util.Pair;


public class StockSorter {

        public void sort(Pair<String, Double>[] pairs) {
            for (int i = 0; i < pairs.length; i++) {
                for (int j = i + 1; j < pairs.length; j++) {
                    if (pairs[i].getValue() < pairs[j].getValue()) {
                        Pair<String, Double> pair = pairs[i];
                        pairs[i] = pairs[j];
                        pairs[j] = pair;
                    }
                }
            }


        }
    }


