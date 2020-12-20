package com.example.unrandomcasino.RNG;

import com.example.unrandomcasino.Perso;

import java.util.Random;

public class JavaRNG extends RNG {
    Random rand = new Random(Perso.getSeed());
    public int getNumber(int bound){
        return rand.nextInt(bound);
    }
}
