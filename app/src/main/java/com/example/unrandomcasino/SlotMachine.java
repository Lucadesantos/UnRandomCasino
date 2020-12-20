package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;


import nl.dionsegijn.konfetti.KonfettiView;
public class SlotMachine extends AppCompatActivity {
    List<Integer> results = Arrays.asList(9,9,9);
    Integer currBet;


    public static void displaySym(int pos, String num, TextView t1,TextView t2,TextView t3) {
        if (pos==0){t1.setText(num);}
        if (pos==1){t2.setText(num);}
        if (pos==2){t3.setText(num);}
    }

    public static void finished(){
        // After animation
    }

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

                    SlotAnim.rollAnim(8, randNum1, randNum2, randNum3,choice2,choice3,choice4);

                    results.set(0, randNum1);
                    results.set(1, randNum2);
                    results.set(2, randNum3);
                    showResult();
                }
            }
        });
    }

    void showResult(){
        TextView currentMoney = findViewById(R.id.money3);
        if(results.get(0).equals(results.get(1)) && results.get(1).equals(results.get(2))){
            //WIN WIN
            int confLen = Math.min(currBet*10, 5000);
            int particles = 500;

            Perso.setMoney(Perso.getMoney() + currBet*results.get(0));

            final KonfettiView conf = findViewById(R.id.viewKonfetti2);
            android.view.WindowManager win = getWindowManager();
            Confetti.make(conf,win ,confLen, particles);
        }
        currentMoney.setText(getString(R.string.money,String.valueOf(Perso.getMoney())));
    }
}