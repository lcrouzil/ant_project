package com.example.antwar;

public class Constants {
    public static final int MAP_SIZE_X=500;
    public static final int MAP_SIZE_Y=500;

    public static int randomInt(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);
        return (int) (Math.floor(Math.random() * (max - min)) + min);
    }
}

