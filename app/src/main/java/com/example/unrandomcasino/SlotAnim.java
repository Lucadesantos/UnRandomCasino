package com.example.unrandomcasino;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class SlotAnim {
    static void rollAnim(int max, int randNum1, int randNum2, int randNum3,TextView t1,TextView t2,TextView t3){
        ValueAnimator animator = ValueAnimator.ofInt(0, max);
        animator.setDuration(1000); //Duration is in milliseconds
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlotMachine.displaySym(0,animation.getAnimatedValue().toString(),t1,t2,t3);
            }
        });


        ValueAnimator animator2 = ValueAnimator.ofInt(0, randNum1);
        animator2.setDuration(120*randNum1); //Duration is in milliseconds
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
        animator5.setDuration(120*randNum2); //Duration is in milliseconds
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
        animator9.setDuration(1000*randNum3); //Duration is in milliseconds
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
                SlotMachine.finished();
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
