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

import java.util.Arrays;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;

public class Roulette extends AppCompatActivity {
    String choice = " ";
    Integer currBet;
    List<Integer> redA = Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36);
    List<Integer> blackA = Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35);
    List<String> doubleA = Arrays.asList("odd","even","black","red");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        Button red = findViewById(R.id.red);
        Button black = findViewById(R.id.black);
        Button odd = findViewById(R.id.odd);
        Button even = findViewById(R.id.even);
        Button confirm = findViewById(R.id.confirm);
        Spinner number = findViewById(R.id.number);
        EditText bet = findViewById(R.id.bet);
        TextView currentMoney = findViewById(R.id.money2);
        TextView choiceTV = findViewById(R.id.choice);
        TextView result = findViewById(R.id.result);



        currentMoney.setText(String.valueOf(Perso.getMoney()));
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "red";
                choiceTV.setText(choice);
                choiceTV.setTextColor(Color.parseColor("#FF0000"));
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "black";
                choiceTV.setText(choice);
                choiceTV.setTextColor(Color.parseColor("#000000"));
            }
        });

        even.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "even";
                choiceTV.setText(choice);
                choiceTV.setTextColor(Color.parseColor("#000000"));
            }
        });

        odd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "odd";
                choiceTV.setText(choice);
                choiceTV.setTextColor(Color.parseColor("#000000"));
            }
        });

        number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                choice = String.valueOf(position);
                choiceTV.setText(choice);
                if (redA.contains(position)) {
                    choiceTV.setTextColor(Color.parseColor("#FF0000"));
                }
                if (blackA.contains(position)) {
                    choiceTV.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String betStr = bet.getText().toString();
                if (TextUtils.isEmpty(betStr)) {
                    bet.setError("Please place a bet");
                } else {
                    currBet = Integer.parseInt(betStr);
                    if (currBet == 0) {
                        return;
                    }
                    if (Integer.parseInt(bet.getText().toString()) > Perso.getMoney()) {
                        currBet = Perso.getMoney();
                        bet.setText(String.valueOf(Perso.getMoney()));
                    }
                    Perso.setMoney(Perso.getMoney() - currBet);
                    currentMoney.setText(getString(R.string.money, String.valueOf(Perso.getMoney())));


                    int randNum = Perso.getSelectedRNG().getNumber(36);

                    rollAnim(randNum);


                }
            }
        });
    }
    void showResult(int randNum){
        TextView currentMoney = findViewById(R.id.money2);
        List<String> resArray = Arrays.asList(" "," "," ");
        resArray.set(0,String.valueOf(randNum));

        if (redA.contains(randNum)){
            resArray.set(1, "red");
        }
        if (blackA.contains(randNum)) {
            resArray.set(1, "black");
        }

        if ( randNum % 2 == 0) {
            resArray.set(2, "even");
        }else{
            resArray.set(2, "odd");
        }

        if (resArray.contains(choice)){
            // WIN WIN
            int gainsMultiplyer;
            int confLen;
            int particles;
            if (doubleA.contains(choice)) {
                gainsMultiplyer = 2;
                confLen = Math.min(currBet*10, 1500);

                particles = 300;
            } else {
                gainsMultiplyer = 36;
                confLen = 5000;
                particles = 500;
            }
            Perso.setMoney(Perso.getMoney() + currBet*gainsMultiplyer);

            final KonfettiView conf = findViewById(R.id.viewKonfetti);
            android.view.WindowManager win = getWindowManager();
            Confetti.make(conf, win,confLen, particles);

        }
        currentMoney.setText(getString(R.string.money,String.valueOf(Perso.getMoney())));
    }
    void rollAnim(int randNum){
        TextView result = findViewById(R.id.result);
        ValueAnimator animator = ValueAnimator.ofInt(0, 36);
        animator.setDuration(800); //Duration is in milliseconds
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                result.setText(animation.getAnimatedValue().toString());
                if (redA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#FF0000"));
                }
                if (blackA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        ValueAnimator animator2 = ValueAnimator.ofInt(0, 36);
        animator2.setDuration(1300); //Duration is in milliseconds
        animator2.setInterpolator(new LinearInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                result.setText(animation.getAnimatedValue().toString());
                if (redA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#FF0000"));
                }
                if (blackA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        ValueAnimator animator3 = ValueAnimator.ofInt(0, 36);
        animator3.setDuration(2000); //Duration is in milliseconds
        animator3.setInterpolator(new LinearInterpolator());
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                result.setText(animation.getAnimatedValue().toString());
                if (redA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#FF0000"));
                }
                if (blackA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        ValueAnimator animator4 = ValueAnimator.ofInt(0, randNum);
        animator4.setDuration(120*randNum); //Duration is in milliseconds
        animator4.setInterpolator(new DecelerateInterpolator());
        animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                result.setText(animation.getAnimatedValue().toString());
                if (redA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#FF0000"));
                }
                if (blackA.contains(animation.getAnimatedValue())) {
                    result.setTextColor(Color.parseColor("#000000"));
                }
            }

        });
        animator4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showResult(randNum);
            }
        });
        AnimatorSet s = new AnimatorSet();
        s.playSequentially(animator,animator2,animator3,animator4);
        s.start();
    }
}