package com.example.unrandomcasino;

import java.util.Random;

public class JavaRNG extends RNG{
    int getNumber(int bound){
        return new Random().nextInt(bound);
    }
}
