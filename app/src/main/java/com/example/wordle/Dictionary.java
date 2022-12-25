package com.example.wordle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dictionary {
    private static String[] wordsArray;
    private static int wordNum;

    public Dictionary(){
        this.wordNum = 0;
        this.wordsArray = new String[] {"ROSSA", "JETTY", "WIZZO", "CUPPA",
                "COHOE", "GURKS", "SQUAD", "BEISA", "SHRUG",
                "FOSSA", "FLUYT", "CAMUS", "SPEED", "MAMIL",
                "ARRAY", "POLIO", "BARNS", "PANES", "SOUTS",
                "LIMAS", "FETCH", "QUECK", "TWINK", "GRAZE",
                "CROCK", "ALMUD", "OOHED", "COLOG", "WISHT",
                "BEARD", "SAMEL", "AHIND", "BRUNG", "BARCA",
                "MAHAL", "JAMBE", "PLUSH", "BRUJA", "HOWRE",
                "MIDDY"};
        this.shuffleWordsArray();
    }

    public void shuffleWordsArray(){
        List<String> wordsList = Arrays.asList(this.wordsArray);
        Collections.shuffle(wordsList);
        wordsList.toArray(this.wordsArray);
    }

    public void restart(){
        this.wordNum = 0;
        shuffleWordsArray();
    }

    public static String getWord(){
        return wordsArray[wordNum];
    }

    public void nextWord(){
        if(wordNum < wordsArray.length) this.wordNum = this.wordNum + 1;
        else restart();
    }
}
