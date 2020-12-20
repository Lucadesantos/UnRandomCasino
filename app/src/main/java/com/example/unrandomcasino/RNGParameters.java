package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class RNGParameters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rng_parameters);

        Spinner choice = findViewById(R.id.RNG);
        choice.setSelection(Perso.selectPos);
        choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Perso.setRNG(new JavaRNG());
                }
                if (position == 1){
                    Perso.setRNG(new NoRNG());
                }
                Perso.selectPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Spinner choice = findViewById(R.id.RNG);
        choice.setSelection(Perso.selectPos);
    }
}