package com.example.antwar;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Flow;

public class WorkerAnt extends Ant implements Flow.Subscriber{
    public ArrayList<Resource> resources = new ArrayList<Resource>();

    /**merci intelij
     *
     * @param Color
     * @param x
     * @param y
     */
    public WorkerAnt(AnthillColor Color, int x, int y){
        super(Color,x,y);

    }
    public void update(){

    }
    public void run() {

    }


    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(Object item) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
