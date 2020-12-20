package com.example.unrandomcasino;

public class NoRNG extends RNG{
    @Override
    int getNumber(int bound) {
        return 31;
    }
}
