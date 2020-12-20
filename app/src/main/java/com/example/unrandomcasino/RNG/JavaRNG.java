package com.example.unrandomcasino.RNG;

import java.util.Random;

public class JavaRNG extends RNG {
    public int getNumber(int bound){
        return new Random().nextInt(bound);
    }
}
