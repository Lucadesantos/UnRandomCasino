package com.example.unrandomcasino.RNG;

import com.example.unrandomcasino.Perso;

public class NoRNG extends RNG {
    @Override
    public int getNumber(int bound) {
        return Perso.getSeed()%bound;
    }
}
