package com.example.antwar;

import java.util.concurrent.Flow;

public class CommanderAnt extends Ant implements Flow.Subscriber, Flow.Subscription {

    public CommanderAnt(AnthillColor Color, int x, int y) {
        super(Color,x,y);

    }

    public void update() {

    }

    public void run() {
        System.out.println("hello from Commander");

    }

    /**encore necessaire ??
     *
     */
    public void addObserver() {

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

    @Override
    public void request(long n) {

    }

    @Override
    public void cancel() {

    }
}
