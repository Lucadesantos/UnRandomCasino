package com.example.unrandomcasino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Roulette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        String choice;
        Button red = findViewById(R.id.red);
        Button black = findViewById(R.id.black);
        Button odd = findViewById(R.id.odd);
        Button even = findViewById(R.id.even);
        Button confirm = findViewById(R.id.confirm);
        Spinner number = findViewById(R.id.number);
        EditText bet = findViewById(R.id.bet);
        TextView currentMoney = findViewById(R.id.money2);
        TextView choiceTV = findViewById(R.id.choice);

    }
}