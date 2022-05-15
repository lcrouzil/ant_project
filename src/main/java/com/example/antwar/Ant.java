package com.example.antwar;

import java.io.Serializable;

public abstract class Ant implements Serializable, Runnable { // Runnable comme thread mais en implements permet de garder le extend
    boolean isInjured = false;
    AnthillColor AntColor;
    int XPos;
    int YPos;
    int IndexAnthill;

    public Ant(AnthillColor Color, int x, int y, int IndexAnthill) {
        this.AntColor = Color;
        this.XPos = x;
        this.YPos = y;
        this.IndexAnthill = IndexAnthill;

    }

    /**
     * fourmi blesse
     *
     * @param injuried
     */
    void setInjured(boolean injuried) {
        this.isInjured = injuried;
    }

    public boolean getInjured() {
        return this.isInjured;
    }

    /**
     * couleur fourmi
     *
     * @return
     */
    public AnthillColor getAntColor() {

        return this.AntColor;
    }

    /**
     * recupere la position en x
     *
     * @return
     */
    public int getXPos() {
        return this.XPos;
    }

    public void setXPos(int NewXPos) {
        this.XPos = NewXPos;
    }

    /**
     * recupere la position en y
     *
     * @return
     */
    public int getYPos() {
        return this.YPos;
    }

    public void setYPos(int NewYPos) {
        this.YPos = NewYPos;
    }
}
