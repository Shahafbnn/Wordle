package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Locale;

public class WordleActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button[] buttonsLetters;
    private Button buttonEnter,buttonDelete,buttonRestart,buttonState;
    private Button[][] wordle;
    private GameManager gm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordle);

        gm = new GameManager(this);
        this.buttonsLetters = new Button[26];
        this.wordle = new Button[6][5];


        for (int i = 0; i < this.buttonsLetters.length; i++) {
            this.buttonsLetters[i] = findViewById(getResources().getIdentifier("button" + (char) ('A' + i), "id", getPackageName()));
            this.buttonsLetters[i].setOnClickListener(this);
        }

        for(int y = 0; y < this.wordle.length; y++){
            for(int x = 0; x < this.wordle[y].length; x++){
                this.wordle[y][x] = findViewById(getResources().getIdentifier("buttonBox" + y + "_" + x, "id", getPackageName()));
                this.wordle[y][x].setText("");
                this.wordle[y][x].setBackgroundColor(getResources().getColor(R.color.white));
                this.wordle[y][x].setTextColor(getResources().getColor(R.color.black));

            }
        }

        this.buttonEnter = findViewById(R.id.buttonEnter);
        this.buttonDelete = findViewById(R.id.buttonDelete);
        this.buttonRestart = findViewById(R.id.buttonRestart);
        this.buttonState = findViewById(R.id.buttonState);


        for (Button button: this.buttonsLetters)
            button.setOnClickListener(this);

        this.buttonEnter.setOnClickListener(this);
        this.buttonDelete.setOnClickListener(this);
        this.buttonRestart.setOnClickListener(this);

        tests();

    }

    public static String N(Object is, Object should, Object func_name){
        String s = "";
        s += ("Starting tests for " + func_name + "...\n");
        if(is.equals(should)) s += ("[OK]\n");
        else {
            s += ("[ERROR]: is: " + is + ", when it should be: " + should + "\n");
        }
        s += ("Ending tests for " + func_name + "...\n");
        return s;
    }

    public void WordTests(){
        Word w = new Word("steal".toUpperCase(Locale.ROOT));
        Log.v("WordTests", "" + N("" + w.secretWord, "STEAL", "secretWord"));
        String secretWord = w.secretWord;
        Log.v("WordTests", "" + N("" + Arrays.toString(w.checkWord(secretWord)), "[2, 2, 2, 2, 2]", "secretWord"));

    }
    public void DictionaryTests(){}
    public void GameManagerTests(){}
    public void WordleActivityTests(){}
    public void tests(){
        WordTests();
        DictionaryTests();
        GameManagerTests();
        WordleActivityTests();
    }

    public void restart(){
        for (Button[] buttons : this.wordle) {
            for (Button button : buttons) {
                button.setText("");
                button.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        for (Button buttonsLetter : this.buttonsLetters) {
            buttonsLetter.setBackgroundColor(getResources().getColor(R.color.light_grey));
            buttonsLetter.setClickable(true);
        }
        this.buttonState.setVisibility(View.INVISIBLE);
        this.enableKeyboard();


    }

    public void updateLetters(int[] guessed){
        Log.v("updateLetters", "guessed: " + Arrays.toString(guessed));
        for(int i = 0; i < guessed.length; i++){
            Log.v("updateLetters", "guessed[" + i + "]: " + guessed[i]);
        }
        for(int x = 0; x < this.wordle[gm.getAttempts()].length; x++){
            if(guessed[x] == 0) {
                this.wordle[gm.getAttempts()][x].setBackgroundColor(getResources().getColor(R.color.dark_grey));
                Log.e("updateLettersID", "the ID: " + findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess().charAt(x)), "id", getPackageName())));
                findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess().charAt(x)), "id", getPackageName())).setBackgroundColor(getResources().getColor(R.color.dark_grey));
            }
            else if(guessed[x] == 1) {
                this.wordle[gm.getAttempts()][x].setBackgroundColor(getResources().getColor(R.color.yellow));
                findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess().charAt(x)), "id", getPackageName())).setBackgroundColor(getResources().getColor(R.color.yellow));

            }
            else if(guessed[x] == 2) {
                this.wordle[gm.getAttempts()][x].setBackgroundColor(getResources().getColor(R.color.green));
                findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess().charAt(x)), "id", getPackageName())).setBackgroundColor(getResources().getColor(R.color.green));
            }
        }

    }

    private void gameEnd(){
        if(gm.getGameStatus() == 'V') {
            this.buttonState.setText("You won! \n RESTART to try again!");
            this.disableKeyboard();
            this.buttonState.setVisibility(View.VISIBLE);
        }
        else if(gm.getGameStatus() == 'L') {
            this.buttonState.setText("You lost! \n RESTART to try again!");
            this.disableKeyboard();
            this.buttonState.setVisibility(View.VISIBLE);
        }

    }

    private void disableKeyboard(){
        for (Button buttonsLetter : this.buttonsLetters) {
            buttonsLetter.setClickable(false);
        }
        buttonEnter.setClickable(false);
        buttonDelete.setClickable(false);
        //buttonRestart.setClickable(false);
    } // loops on all buttons in abc and sets
    //clickable=false (for end of game)
    private void enableKeyboard(){
        for (Button buttonsLetter : this.buttonsLetters) {
            buttonsLetter.setClickable(true);
        }
        buttonEnter.setClickable(true);
        buttonDelete.setClickable(true);
        //buttonRestart.setClickable(true);
    }

    public void changeButtonColor(Button button, int color){ //int red = getResources().getColor(R.color.red);
        button.setBackgroundColor(color);
    }

    public void changeButtonVisibility(Button button, final int visibility){
        //final int visible = View.VISIBLE;
        button.setVisibility(visibility);
    }


    public void onClick(View view)
    {

        if (view != null)
        {
            int viewId = view.getId();
            if (viewId == this.buttonEnter.getId()){
                Log.v("onClick", "buttonEnter was clicked" + gm.getCurrentGuess().length());

                if(gm.getCurrentGuess().length() >= 5){
                    Log.v("onClick", "buttonEnter is inside.");
                    Log.v("onClick", "gm.getCurrentGuess(): " + gm.getCurrentGuess());

                    Word w1 = gm.getWord();
                    String cg = gm.getCurrentGuess();
                    int[] arrz = w1.checkWord(cg);
                    Log.e("onClick", "w1.checkWord(cg):" + Arrays.toString(w1.checkWord(cg)));
                    this.updateLetters(arrz);
                    gm.setAttempts(gm.getAttempts()+1);

                    gm.updateGameStatus();
                    this.gameEnd();
                }
            }
            if (viewId == this.buttonDelete.getId()){
                if(gm.getCurrentGuess().length() > 0) {
                    this.wordle[gm.getAttempts()][gm.getCurrentGuess().length() - 1].setText(" ");
                    gm.setCurrentGuess(gm.getCurrentGuess().substring(0, gm.getCurrentGuess().length() - 1));
                }
            }
            if (viewId == this.buttonRestart.getId()){
                gm.restart();
            }
            for (Button buttonsLetter : this.buttonsLetters) {
                if (viewId == buttonsLetter.getId()) {
                    if (gm.getCurrentGuess().length() < 5) {
                        this.wordle[gm.getAttempts()][gm.getCurrentGuess().length()].setText(buttonsLetter.getText());
                        gm.addCurrentGuess(buttonsLetter.getText().charAt(0));
                        Log.v("onClick", "buttonsLetters[i].getText(): " + buttonsLetter.getText());
                        Log.v("onClicks", "this.wordle[gm.getAttempts()][gm.num]:" + this.wordle[gm.getAttempts()][gm.num].getText());
                    }
                    else if(gm.getCurrentGuess().length() >= 4){
                        gm.setCurrentGuess("");
                    }

                }
            }

        }
    }
}