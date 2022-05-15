package com.example.antwar;

public class Constants {
    public static final int MAP_SIZE_X = 100; // taille en x de la map
    public static final int MAP_SIZE_Y = 100; // taille en y de la map
    public static final int WINDOW_SIZE_X = 1200; // taille en x de la fenetre
    public static final int WINDOW_SIZE_Y = 1000; // taille en y de la map
    public static final int AntLoadMax = 5; //charge max de la fourmi

    public static final int TIMERMAX = 120000; // temps max

    public static boolean RetournerMaison = false; //bool pour ordre retour maison
    public static boolean FinGame = false; //bool fin du game


    /**
     * random de int de 0 a 500 (exclue)
     */
    public static int randomInt(int min, int max) {

        return (int) (Math.ceil(Math.random() * (max - min)) + min);
    }
}

