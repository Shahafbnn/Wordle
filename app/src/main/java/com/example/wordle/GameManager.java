package com.example.wordle;

import android.util.Log;

import java.util.Arrays;

public class GameManager {
    private Dictionary dictionary;
    private WordleActivity wordleUI;
    private Word word;
    private int attempts;
    int num;
    private char status;

    private String currentGuess;


    public GameManager(WordleActivity wordleUI){
        this.dictionary = new Dictionary();
        this.word = new Word(dictionary.getWord());
        this.attempts = 0;
        this.status = 'P';

        this.currentGuess = "";

    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public WordleActivity getWordleUI() {
        return wordleUI;
    }

    public void setWordleUI(WordleActivity wordleUI) {
        this.wordleUI = wordleUI;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(String currentGuess) {
        this.currentGuess = currentGuess;
    }


    void restart(){
        this.attempts = 0;
        this.status = 'P';
        this.dictionary.restart();
        this.word.restart(dictionary.getWord());
        this.wordleUI.restart();
    } /* restarts the game – go through all the properties
    and check how it should affect them. Remember to call the clear
    method in wordleUI.*/
    void checkWord(String guess){
        wordleUI.updateLetters(word.checkWord(guess));
        attempts++;

    } /* Checks the word guessed by the
    user, and changes the properties accordingly. Remember to call the
    updateLetters method in wordleUI.*/
    char getGameStatus(){return status;} /* returns the game status (in
    progress/victory/loss).*/

    int addCurrentGuess(char letter){
        if(this.currentGuess.length() < 5) {
            int i = 0;
            this.currentGuess = this.currentGuess + letter;
            return 0;
        }
        Log.d("currentGuess", "GameManager.addCurrentGuess: currentGuess is full.");
        return -1;
    }


}
