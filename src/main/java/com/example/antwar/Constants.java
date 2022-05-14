package com.example.antwar;

public class Constants {
    public static final int MAP_SIZE_X = 50;
    public static final int MAP_SIZE_Y = 50;
    public static final int WINDOW_SIZE_X = 500;
    public static final int WINDOW_SIZE_Y = 500;
    public static final int AntLoadMax = 5;

    public static final int TIMERMAX = 120000;

    public static boolean RetournerMaison = false;
    public static boolean FinGame = false;



    /**
     * random de int de 0 a 500 (exclue)
     */
    public static int randomInt(int min, int max) {

        return (int) (Math.ceil(Math.random() * (max - min)) + min);
    }
}

