package com.example.antwar;

public abstract class Ant {
    boolean isInjured = false;
    AnthillColor AntColor;
    int XPos;
    int YPos;

    public Ant(AnthillColor Color,int x,int y) {
        this.AntColor=Color;
        this.XPos=x;
        this.YPos=y;
        //Map.getInstance().getTiles()[x][y].addAnt(this);

    }

    void setInjured(boolean injuried){
        this.isInjured = injuried;

    }

    public boolean getInjured(){
        return this.isInjured;
    }

    public AnthillColor getAntColor() {

        return this.AntColor;
    }


    public int getXPos() {
        return this.XPos;
    }

    public void setXPos(int NewXPos) {
        this.XPos = NewXPos;
    }

    public int getYPos() {
        return this.YPos;
    }

    public void setYPos(int NewYPos) {
        this.YPos = NewYPos;
    }
}
