package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.unrandomcasino.RNG.*;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner choice = findViewById(R.id.RNG);
        choice.setSelection(Perso.selectPos);
        EditText seed = findViewById(R.id.seed);
        choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Perso.setRNG(new JavaRNG());
                    Log.d("TEST","JAVARNG");
                }
                else if (position == 1){
                    Perso.setRNG(new NoRNG());
                    Log.d("TEST","NORNG");
                }
                Perso.selectPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        seed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(seed.getText().toString())) {
                    Perso.seed = Integer.parseInt(seed.getText().toString());
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Spinner choice = findViewById(R.id.RNG);
        EditText seed = findViewById(R.id.seed);
        if (Perso.getSeed()!=0){seed.setText(String.valueOf(Perso.getSeed()));}
        choice.setSelection(Perso.selectPos);

    }
}