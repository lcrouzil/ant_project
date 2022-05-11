package com.example.antwar;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Flow;

public class WorkerAnt extends Ant implements Flow.Subscriber {
    public ArrayList<Resource> resources = new ArrayList<Resource>();
    private QueenOrders CommanderOrder;

    /**
     * merci intelij
     *
     * @param Color
     * @param x
     * @param y
     */
    public WorkerAnt(AnthillColor Color, int x, int y) {
        super(Color, x, y);
    }

    /**
     * mise a jour des ordres
     */
    public void update() {
        //random entre 1 et 4 ( 4 possibilite de déplacement)
        Random random = new Random();
        int nb = random.nextInt(4);

        System.out.println("update");
        Tile tile;
        switch (nb) {
            case 0: // deplacement vers le haut

                tile = Map.getInstance().getTile(this.XPos, this.YPos - 1);

                if (tile != null) {
                    Map.getInstance().moveTo(this, tile);
                }
                break;
            case 1:
                tile = Map.getInstance().getTile(this.XPos, this.YPos + 1);

                if (tile != null) {
                    Map.getInstance().moveTo(this, tile);
                }

                break;
            case 2:
                tile = Map.getInstance().getTile(this.XPos + 1, this.YPos);

                if (tile != null) {
                    Map.getInstance().moveTo(this, tile);
                }

                break;
            case 3:
                tile = Map.getInstance().getTile(this.XPos - 1, this.YPos);

                if (tile != null) {
                    Map.getInstance().moveTo(this, tile);
                }
                break;
        }
        //verifie diff de nul avant déplacement
        // int[] randomCoord = this.lenomdetamethodedansAnt(this.this.XPos], this.this.YPos])
        //Map.getInstance().MoveTo(this,Map.getInstance().getTiles()[this.XPos][this.YPos],Map.getInstance().getTiles()[random[0][random[1]);
    }

    /**
     * run des fourmi
     */
    public void run() {
        System.out.println("hello from Slave");
        while (true) { // infini (refelchir a finir)
            try {
                update();
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
