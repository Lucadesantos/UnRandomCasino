package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class SlotMachine extends AppCompatActivity {
    List<Integer> poss = Arrays.asList(1,2,3);
    Integer currBet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);
        Button spin = findViewById(R.id.spin);

        TextView currentMoney = findViewById(R.id.money3);
        EditText bet = findViewById(R.id.bet2);

        TextView choice2 = findViewById(R.id.choice2);
        TextView choice3 = findViewById(R.id.choice3);
        TextView choice4 = findViewById(R.id.choice4);

        currentMoney.setText(getString(R.string.money,String.valueOf(Perso.getMoney())));

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String betStr = bet.getText().toString();
                if (TextUtils.isEmpty(betStr)) { // S'il n'y a pas de mise.
                    bet.setError("Please place a bet");
                    return;
                } else {
                    currBet = Integer.parseInt(betStr);
                    if (currBet == 0) { //Si il mise 0;
                        return;
                    }
                    if (Integer.parseInt(bet.getText().toString()) > Perso.getMoney()) {
                        currBet = Perso.getMoney();
                        bet.setText(String.valueOf(Perso.getMoney())); //S'il mise plus que son porte-feuille
                    }
                    Perso.setMoney(Perso.getMoney() - currBet);
                    currentMoney.setText(getString(R.string.money, String.valueOf(Perso.getMoney())));
                    int randNum1 = Perso.getSelectedRNG().getNumber(8);
                    int randNum2 = Perso.getSelectedRNG().getNumber(8);
                    int randNum3 = Perso.getSelectedRNG().getNumber(8);
                    choice2.setText(String.valueOf(randNum1));
                    choice3.setText(String.valueOf(randNum2));
                    choice4.setText(String.valueOf(randNum3));

                }

            }
        });

    }
}