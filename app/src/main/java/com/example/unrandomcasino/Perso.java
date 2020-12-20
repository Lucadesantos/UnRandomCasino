package com.example.unrandomcasino;

public class Perso {
    private static int Money = 1000;
    static RNG selectedRNG = new JavaRNG();
    static int getMoney(){return Money;}
    static void setMoney(int money){Money = money;}
    static RNG getSelectedRNG(){return selectedRNG;}
    static void setRNG(RNG newRNG){selectedRNG = newRNG;}
}
