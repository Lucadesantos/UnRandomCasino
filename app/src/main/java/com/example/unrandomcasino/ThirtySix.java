package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirtySix extends AppCompatActivity {
    Integer currBet, _pot, _turn, _score, _dice, _iaBet = (int)(Perso.getMoney() * 0.1);
    String _myTurn = "your turn", _oppTurn = "IA turn";
    String _ia_bet = "IA bet %d $", _player_bet = "You bet %d  $", _ia_plays = "IA played %d", _player_plays = "You played %d", _ia_wins = "IA won", _player_wins = "you won";
    Boolean _firsThrow = true, _newGame  = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirty_six);

        TextView text_pot = findViewById(R.id.text_pot);
        TextView text_turn = findViewById(R.id.text_turn);
        TextView text_score = findViewById(R.id.text_score);
        TextView text_dice = findViewById(R.id.text_dice);
        TextView information = findViewById(R.id.information);
        Button throw_dice = findViewById(R.id.throw_dice);

        // money and bet (lucas)
        EditText bet = findViewById(R.id.bet);
        TextView currentMoney = findViewById(R.id.money2);

        currentMoney.setText(String.valueOf(Perso.getMoney()));
        resetGame();
        text_pot.setText("0 $");
        text_turn.setText(_myTurn);
        text_score.setText(String.valueOf(_score));
        text_dice.setText(String.valueOf(0));
        information.setText(String.format(_ia_bet, _iaBet));
        throw_dice.setText("New game");

        throw_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String betStr = bet.getText().toString();
                if (_newGame) {
                    _firsThrow = true;
                    _newGame = false;
                    throw_dice.setText("Throw dice");
                    information.setText(String.format(_ia_bet, _iaBet));
                    _pot += _iaBet;
                    String p = _pot + "$";
                    text_pot.setText(p);
                }else {
                    // vérfier si 1er lancé
                    if (_firsThrow) {
                        if (TextUtils.isEmpty(betStr)) {
                            bet.setError("Please place a bet");
                        }
                        else {
                            _firsThrow = false;
                            currBet = Integer.parseInt(betStr);
                            if (currBet == 0) { return; }
                            if (Integer.parseInt(bet.getText().toString()) > Perso.getMoney()) {
                                currBet = Perso.getMoney();
                                bet.setText(String.valueOf(Perso.getMoney()));
                            }
                            _pot += currBet;
                            information.setText(String.format(_player_bet, currBet));
                            String p = _pot + "$";
                            text_pot.setText(p);
                            System.out.println("Le pot est de " + _pot);

                            Perso.setMoney(Perso.getMoney() - currBet);
                            currentMoney.setText(getString(R.string.money, String.valueOf(Perso.getMoney())));
                            int randNum = Perso.getSelectedRNG().getNumber(6);
                            System.out.println("vous jouez " + (randNum+1));
                            _score += (randNum+1);
                            _dice = (randNum+1);
                            information.setText(String.format(_player_plays, _dice));

                            nextTurn();
                            updateScreen(text_pot, text_turn, text_score, text_dice);
                            throw_dice.setText("Next turn");
                        }
                    }
                    else {
                        if (_turn == 0) {
                            int randNum = Perso.getSelectedRNG().getNumber(6);
                            _score += (randNum+1);
                            _dice = (randNum+1);
                            System.out.println("vous jouez " + (randNum+1));
                            information.setText(String.format(_player_plays, _dice));
                            throw_dice.setText("Next turn");
                        }
                        else {
                            opponentPlay();
                            information.setText(String.format(_ia_plays, _dice));
                            throw_dice.setText("Throw dice");
                        }
                        updateScreen(text_pot, text_turn, text_score, text_dice);
                        if (checkWin()) {
                            if (_turn == 0) {
                                Perso.setMoney(Perso.getMoney() + _pot);
                                currentMoney.setText(String.valueOf(Perso.getMoney()));
                                information.setText(_player_wins);
                            }
                            else { information.setText(_ia_wins); };
                            resetGame();
                            _newGame = true;
                            throw_dice.setText("New game");
                        }
                        else { nextTurn();}
                        updateScreen(text_pot, text_turn, text_score, text_dice);
                    }
                }
            }
        });

    } // end onCreate

    void resetGame(){
        _turn = 0;
        _firsThrow = false;
        _score = 0;
        _pot = 0;
        _dice = 0;
    };

    Boolean checkWin() { return _score >= 36 ; };

    void nextTurn() { _turn++; _turn %= 2; };

    void opponentPlay() {
        int randNum = Perso.getSelectedRNG().getNumber(6);
        _score += (randNum+1);
        _dice = (randNum+1);
        System.out.println("adversaire joue " + (randNum+1));
    };

    void updateScreen(TextView pot, TextView turn, TextView score, TextView dice) {
        // mettre a jour argent du jouuer
        System.out.println("screen update");
        String p = _pot + "$";
        pot.setText(p);
        if (_turn == 0) { turn.setText(_myTurn); }
        else { turn.setText(_oppTurn); }
        score.setText(String.valueOf(_score));
        dice.setText(String.valueOf(_dice));
    };
} // end ThirtySix