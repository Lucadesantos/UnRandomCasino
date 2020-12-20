package com.example.unrandomcasino;

import com.example.unrandomcasino.RNG.JavaRNG;
import com.example.unrandomcasino.RNG.RNG;

public class Perso {
    private static int Money = 1000;
    static RNG selectedRNG = new JavaRNG();
    static int selectPos = 0;
    static int seed = 0;
    static public int getSeed(){return seed;}
    static public int getMoney(){return Money;}
    static void setMoney(int money){Money = money;}
    static public RNG getSelectedRNG(){return selectedRNG;}
    static void setRNG(RNG newRNG){selectedRNG = newRNG;}
}
