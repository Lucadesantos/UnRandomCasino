package com.example.unrandomcasino;

import android.graphics.Color;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Confetti extends AppCompatActivity {
    public static void make(KonfettiView conf, android.view.WindowManager win, int length, int density){
        DisplayMetrics display = new DisplayMetrics();
        win.getDefaultDisplay().getMetrics(display);
        conf.build()
                .addColors(Color.parseColor("#fc0e9b"), Color.parseColor("#f6d336"), Color.parseColor("#f63f55"),Color.parseColor("#4cf8f4"),Color.parseColor("#9bdf5b"))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, display.widthPixels + 50f, -50f, -50f)
                .streamFor(density, length);

    }
}
