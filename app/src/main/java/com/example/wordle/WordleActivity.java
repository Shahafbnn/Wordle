package com.example.wordle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WordleActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button[] buttonsLetters;
    private Button buttonEnter, buttonDelete;
    private Button[][] wordle;
    private GameManager gm;

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

        for (Button button: this.buttonsLetters)
            button.setOnClickListener(this);

        this.buttonEnter.setOnClickListener(this);
        this.buttonDelete.setOnClickListener(this);

    }

    public void restart(){
        for(int y = 0; y < this.wordle.length; y++){
            for(int x = 0; x < this.wordle[y].length; x++){
                this.wordle[y][x].setText("");
                this.wordle[y][x].setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        for (int i = 0; i < this.buttonsLetters.length; i++) {
            this.buttonsLetters[i].setBackgroundColor(getResources().getColor(R.color.light_grey));
            this.buttonsLetters[i].setClickable(true);
        }

    }

    public void updateLetters(int[] guessed){
        Log.v("updateLetters", "guessed: " + guessed.toString());
        for(int i = 0; i < guessed.length; i++){
            Log.v("updateLetters", "guessed[" + i + "]: " + guessed[i]);
        }
        for(int x = 0; x < this.wordle[gm.getAttempts()].length; x++){
            if(guessed[x] == 0) {
                this.wordle[gm.getAttempts()][x].setBackgroundColor(getResources().getColor(R.color.light_grey));
                Log.e("updateLettersID", "the ID: " + findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess()[x]), "id", getPackageName())));
                findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess()[x]), "id", getPackageName())).setBackgroundColor(getResources().getColor(R.color.light_grey));
            }
            else if(guessed[x] == 1) {
                this.wordle[gm.getAttempts()][x].setBackgroundColor(getResources().getColor(R.color.yellow));
                findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess()[x]), "id", getPackageName())).setBackgroundColor(getResources().getColor(R.color.yellow));

            }
            else if(guessed[x] == 2) {
                this.wordle[gm.getAttempts()][x].setBackgroundColor(getResources().getColor(R.color.green));
                findViewById(getResources().getIdentifier("button" + (gm.getCurrentGuess()[x]), "id", getPackageName())).setBackgroundColor(getResources().getColor(R.color.green));
            }
        }

    }

    private void disableKeyboard(){
        for (int i = 0; i < this.buttonsLetters.length; i++) {
            this.buttonsLetters[i].setClickable(false);
        }
    } // loops on all buttons in abc and sets
    //clickable=false (for end of game)

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
                Log.v("onClick", "buttonEnter was clicked" + gm.getCurrentGuessLast());

                if(gm.getCurrentGuessLast() >= 5){
                    for(int i = 0; i < gm.getCurrentGuess().length; i++) {
                        Log.v("onClick", "buttonEnter is inside.");
                        Log.v("onClick", "gm.getCurrentGuess(): " + i + gm.getCurrentGuess()[i]);
                    }
                    Word wor = gm.getWord();
                    char[] cg = gm.getCurrentGuess();
                    int[] arrz = wor.checkWord(cg);
                    Log.e("onClick", "wor.checkWord(cg):" + wor.checkWord(cg));
                    this.updateLetters(arrz);
                    gm.setAttempts(gm.getAttempts()+1);
                    gm.setCurrentGuessLast(0);
                }
            }
            for (int i = 0; i < this.buttonsLetters.length; i++) {
                if (viewId == this.buttonsLetters[i].getId()){
                    gm.addCurrentGuess(this.buttonsLetters[i].getText().charAt(0));
                    if(gm.getCurrentGuessLast() < 5) {
                        this.wordle[gm.getAttempts()][gm.getCurrentGuessLast()].setText(this.buttonsLetters[i].getText());
                        gm.addCurrentGuess(this.buttonsLetters[i].getText().charAt(0));
                        Log.v("onClick", "buttonsLetters[i].getText(): " + this.buttonsLetters[i].getText());
                        Log.v("onClick", "this.wordle[gm.getAttempts()][gm.num]:" + this.wordle[gm.getAttempts()][gm.num].getText());
                        gm.setCurrentGuessLast(gm.getCurrentGuessLast()+1);
                    }

                }
            }

        }
    }
}