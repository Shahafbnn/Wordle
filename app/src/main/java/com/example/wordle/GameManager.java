package com.example.wordle;

import android.util.Log;

public class GameManager {
    private Dictionary dictionary;
    private WordleActivity wordleUI;
    private Word word;
    private int attempts;
    int num;
    private char status;

    private char[] currentGuess;
    private int currentGuessLast;


    public GameManager(WordleActivity wordleUI){
        this.dictionary = new Dictionary();
        this.word = new Word(dictionary.getWord());
        this.attempts = 0;
        this.status = 'P';

        this.currentGuess = new char[5];
        for(int i = 0; i < currentGuess.length; i++){
            currentGuess[i] = ' ';
        }
        this.currentGuessLast = 0;

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

    public char[] getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(char[] currentGuess) {
        this.currentGuess = currentGuess;
    }
    public int getCurrentGuessLast() {
        return currentGuessLast;
    }

    public void setCurrentGuessLast(int currentGuessLast) {
        this.currentGuessLast = currentGuessLast;
    }

    void restart(){
        this.attempts = 0;
        this.status = 'P';
        this.currentGuessLast = 0;
        this.dictionary.restart();
        this.word.restart(dictionary.getWord());
        this.wordleUI.restart();
    } /* restarts the game – go through all the properties
    and check how it should affect them. Remember to call the clear
    method in wordleUI.*/
    void checkWord(String guess){
        wordleUI.updateLetters(word.checkWord(guess.toCharArray()));
        attempts++;

    } /* Checks the word guessed by the
    user, and changes the properties accordingly. Remember to call the
    updateLetters method in wordleUI.*/
    char getGameStatus(){return status;} /* returns the game status (in
    progress/victory/loss).*/

    int addCurrentGuess(char letter){
        if(this.currentGuessLast < 5) {
            this.currentGuessLast++;
            int i = 0;
            while(i < this.currentGuess.length){
                if(this.currentGuess[i] == ' ') {
                    this.currentGuess[i] = letter;
                    return 0;
                }
                i++;
            }
        }
        Log.d("currentGuess", "GameManager.addCurrentGuess: currentGuess is full.");
        return -1;
    }


}
