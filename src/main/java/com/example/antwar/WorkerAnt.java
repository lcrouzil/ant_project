package com.example.antwar;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Flow;

public class WorkerAnt extends Ant implements Flow.Subscriber{
    public ArrayList<Resource> resources = new ArrayList<Resource>();
    private QueenOrders CommanderOrder;

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
        //ordre emis reine
        //ordre recu capitain
        //ordre emis capitaine 
        //ordre recu fourmi
        //ordre enum donc ? for each ?
        switch (CommanderOrder){
            case GO_FIND_RESSOURCE: //obligatoire
                //fourmi ressource sur le dos
                if (resources.size()<6){

                }

                //fourmi pleine
                else {
                    //Map.getInstance().anthills[]
                }
                    //retour base
                // verifier ressource sur la case
                //Map.getInstance().MoveTo();
                break;
            case FOCUS_FOOD:
                //fct
                break;
            case GO_ANTHILL: //obligatoire
                //fct
                break;
            case FOCUS_POINT:
                //fct
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + CommanderOrder);
        }
    }


    public void run() {
        System.out.println("hello from Slave");
        while(true) { // infini (refelchir a finir)
            update();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
