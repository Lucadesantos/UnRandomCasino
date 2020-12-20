package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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


    public static void displaySym(int pos, String num, TextView t1, TextView t2, TextView t3) {
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
                    Log.d("TESTAAAAAAA", String.valueOf(Perso.selectPos));
                    Log.d("TESTAAAAAAA", String.valueOf(randNum2));
                    Log.d("TESTAAAAAAA", String.valueOf(randNum3));
                    choice2.setText(String.valueOf(randNum1));
                    choice3.setText(String.valueOf(randNum2));
                    choice4.setText(String.valueOf(randNum3));
                    results.set(0, randNum1);
                    results.set(1, randNum2);
                    results.set(2, randNum3);
                    rollAnim(8, randNum1, randNum2, randNum3,choice2,choice3,choice4);


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


    void rollAnim(int max, int randNum1, int randNum2, int randNum3,TextView t1,TextView t2,TextView t3){
        ValueAnimator animator = ValueAnimator.ofInt(0, max);
        animator.setDuration(1000); //Duration is in milliseconds
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(0,animation.getAnimatedValue().toString(),t1,t2,t3);
            }
        });


        ValueAnimator animator2 = ValueAnimator.ofInt(0, randNum1);
        animator2.setDuration(80*randNum1); //Duration is in milliseconds
        animator2.setInterpolator(new DecelerateInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(0,animation.getAnimatedValue().toString(),t1,t2,t3);
            }

        });

        // -------- 2 -----------------

        ValueAnimator animator3 = ValueAnimator.ofInt(0, max);
        animator3.setDuration(1000); //Duration is in milliseconds
        animator3.setInterpolator(new LinearInterpolator());
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(1,animation.getAnimatedValue().toString(),t1,t2,t3);
            }
        });

        ValueAnimator animator4 = ValueAnimator.ofInt(0, max);
        animator4.setDuration(1000); //Duration is in milliseconds
        animator4.setInterpolator(new LinearInterpolator());
        animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(1,animation.getAnimatedValue().toString(),t1,t2,t3);
            }
        });

        ValueAnimator animator5 = ValueAnimator.ofInt(0, randNum2);
        animator5.setDuration(80*randNum2); //Duration is in milliseconds
        animator5.setInterpolator(new DecelerateInterpolator());
        animator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(1,animation.getAnimatedValue().toString(),t1,t2,t3);
            }

        });

        // -------- 3 -----------------

        ValueAnimator animator6 = ValueAnimator.ofInt(0, max);
        animator6.setDuration(1000); //Duration is in milliseconds
        animator6.setInterpolator(new LinearInterpolator());
        animator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(2,animation.getAnimatedValue().toString(),t1,t2,t3);
            }
        });

        ValueAnimator animator7 = ValueAnimator.ofInt(0, max);
        animator7.setDuration(1000); //Duration is in milliseconds
        animator7.setInterpolator(new LinearInterpolator());
        animator7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(2,animation.getAnimatedValue().toString(),t1,t2,t3);
            }
        });

        ValueAnimator animator8 = ValueAnimator.ofInt(0, max);
        animator8.setDuration(1000); //Duration is in milliseconds
        animator8.setInterpolator(new LinearInterpolator());
        animator8.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(2,animation.getAnimatedValue().toString(),t1,t2,t3);
            }
        });

        ValueAnimator animator9 = ValueAnimator.ofInt(0, randNum3);
        animator9.setDuration(80*randNum3); //Duration is in milliseconds
        animator9.setInterpolator(new DecelerateInterpolator());
        animator9.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(2,animation.getAnimatedValue().toString(),t1,t2,t3);
            }

        });

        animator9.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showResult();
            }
        });

        AnimatorSet s1 = new AnimatorSet();
        s1.playSequentially(animator, animator2);
        s1.start();
        AnimatorSet s2 = new AnimatorSet();
        s2.playSequentially(animator3, animator4, animator5);
        s2.start();
        AnimatorSet s3 = new AnimatorSet();
        s3.playSequentially(animator6, animator7, animator8, animator9);
        s3.start();
    }
}