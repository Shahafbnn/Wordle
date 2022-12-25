package com.example.wordle;

import android.util.Log;

public class Word {
    private char[] wordArray;
    public String secretWord;

    public Word(String secretWord){
        this.secretWord = secretWord;
        Log.v("Word", "secretWord: " + this.secretWord);
        this.wordArray = secretWord.toCharArray();
    }

    public void restart(String secretWord){
        this.secretWord = secretWord;
        this.wordArray = secretWord.toCharArray();
    }

    public boolean check(char[] word){
        if( word.equals(Dictionary.getWord().toCharArray()) )return true;
        else return false;
    }

    public int checkLetter(char letter, int index){
        if(this.wordArray[index] == letter) return 2;
        else if(this.secretWord.indexOf(letter) >= 0) return 1;
        else return 0;
    }

    public int[] checkWord(char[] word){
        Log.e("WordcheckWord", "secretWord: " + secretWord);
        //0 = incorrect letter,
        // 1 = correct letter & incorrect place,
        // 2 = correct letter & place

        int[] checked = new int[Math.max(this.wordArray.length, word.length)];
        for(int i = 0; i < checked.length; i++){
            //if(secretWord == null) checked[i] = -1;
             if(this.wordArray[i] == word[i]) checked[i] = 2;
            else if(this.secretWord.indexOf(word[i]) >= 0) checked[i] = 1;
            else checked[i] = 0;
        }
        return checked;
    }
}
