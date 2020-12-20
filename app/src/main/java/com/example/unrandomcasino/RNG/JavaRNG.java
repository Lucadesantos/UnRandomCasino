package com.example.unrandomcasino.RNG;

import com.example.unrandomcasino.Perso;

import java.util.Random;

public class JavaRNG extends RNG {
    public int getNumber(int bound){
        return new Random(Perso.getSeed()).nextInt(bound);
    }
}
