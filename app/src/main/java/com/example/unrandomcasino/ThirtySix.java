package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirtySix extends AppCompatActivity {
    Integer currBet, _pot, _turn, _score, _dice;
    String myTurn = "your turn", oppTurn = "opponent turn";
    Boolean firsThrow = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirty_six);

        TextView text_pot = findViewById(R.id.text_pot);
        TextView text_turn = findViewById(R.id.text_turn);
        TextView text_score = findViewById(R.id.text_score);
        Button throw_dice = findViewById(R.id.throw_dice);

        // money and bet (lucas)
        EditText bet = findViewById(R.id.bet);
        TextView currentMoney = findViewById(R.id.money2);


        currentMoney.setText(String.valueOf(Perso.getMoney()));
        initTest();
        String p = _pot + "$";
        text_pot.setText(p);
        text_turn.setText(myTurn);
        text_score.setText(String.valueOf(_score));
        throw_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String betStr = bet.getText().toString();
                // vérfier si 1er lancé
                if (firsThrow) {
                    if (TextUtils.isEmpty(betStr)) {
                        bet.setError("Please place a bet");
                    }
                    else {
                        firsThrow = false;
                        currBet = Integer.parseInt(betStr);
                        if (currBet == 0) { return; }
                        if (Integer.parseInt(bet.getText().toString()) > Perso.getMoney()) {
                            currBet = Perso.getMoney();
                            bet.setText(String.valueOf(Perso.getMoney()));
                        }
                        _pot += currBet;
                        String p = _pot + "$";
                        text_pot.setText(p);
                        System.out.println("Le pot est de " + _pot);

                        Perso.setMoney(Perso.getMoney() - currBet);
                        currentMoney.setText(getString(R.string.money, String.valueOf(Perso.getMoney())));
                        int randNum = Perso.getSelectedRNG().getNumber(6);
                        System.out.println("vous jouez " + (randNum+1));
                        _score += (randNum+1);
                        updateScreen(text_pot, text_turn, text_score);
                        opponentPlay();
                        updateScreen(text_pot, text_turn, text_score);
                    }
                }
                else {
                    int randNum = Perso.getSelectedRNG().getNumber(6);
                    _score += (randNum+1);
                    System.out.println("vous jouez " + (randNum+1));
                    updateScreen(text_pot, text_turn, text_score);
                    checkWin();
                    updateScreen(text_pot, text_turn, text_score);
                    opponentPlay();
                    updateScreen(text_pot, text_turn, text_score);
                    checkWin();
                    updateScreen(text_pot, text_turn, text_score);
                }
            }
        });

    } // end onCreate

    void initTest(){
        _score = 0;
        _pot = 50;
        _turn = 0;
    };

    void checkWin() {
        if (_score > 36) {
            if (_turn == 0) {
                System.out.println("Vous avez perdu");
            }
            else {
                System.out.println("Vous avez gagné");
                Perso.setMoney(Perso.getMoney() + _pot);
            }
            _turn = 0;
            firsThrow = true;
            _score = 0;
            _pot = 50;
        }else if (_score == 36) {
            if (_turn == 0) {
                System.out.println("Vous avez perdu");
            }
            else {
                System.out.println("Vous avez gagné");
                Perso.setMoney(Perso.getMoney() + _pot);
            }
            _turn = 0;
            firsThrow = true;
            _score = 0;
            _pot = 50;
        }else {
            _turn++; _turn %= 2;
            System.out.println("personne n'a gagné");
        }
    };

    void opponentPlay() {
        int randNum = Perso.getSelectedRNG().getNumber(6);
        _score += (randNum+1);
        System.out.println("adversaire joue " + (randNum+1));
    };

    void updateScreen(TextView pot, TextView turn, TextView score) {
        String p = _pot + "$";
        pot.setText(p);
        if (_turn == 0) { turn.setText(myTurn); }
        else { turn.setText(oppTurn); }
        score.setText(String.valueOf(_score));
    };
} // end ThirtySix