package com.example.antwar;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Flow;

import static com.example.antwar.QueenOrders.GO_ANTHILL;

public class CommanderAnt extends Ant implements Flow.Subscriber, Flow.Subscription {

    private QueenOrders OrdreReine = QueenOrders.GO_FIND_RESSOURCE;

    public CommanderAnt(AnthillColor Color, int x, int y, int IndexAnthill) {
        super(Color, x, y, IndexAnthill);

    }

    /**
     * update du commander ( deplacement et ordre)
     */
    public void update() {
        //ressource
        //deplacement
        //random sur ma liste possible de déplacement
        Tile tile;
        ArrayList<Integer> IntList = new ArrayList<Integer>();
        Random random = new Random();


        System.out.println("update");


        if (Map.getInstance().getTile(this.XPos, this.YPos - 1) != null) { //vers le haut
            IntList.add(0);
        }
        if (Map.getInstance().getTile(this.XPos, this.YPos + 1) != null) {//vers le bas
            IntList.add(1);
        }
        if (Map.getInstance().getTile(this.XPos + 1, this.YPos) != null) {//vers la droite
            IntList.add(2);
        }
        if (Map.getInstance().getTile(this.XPos - 1, this.YPos) != null) {//vers la gauche
            IntList.add(3);
        }

        int nb = IntList.get(random.nextInt(IntList.size())); //random sur la list de int

        switch (OrdreReine) {
            case GO_ANTHILL:
                int XposAnthill = Map.getInstance().anthills[IndexAnthill].XPos;
                int YposAnthill = Map.getInstance().anthills[IndexAnthill].YPos;
                if (this.XPos < XposAnthill) { //doit allé a droite
                    tile = Map.getInstance().getTile(this.XPos + 1, this.YPos);
                    Map.getInstance().moveTo(this, tile);
                    this.XPos++;
                } else if (this.XPos > XposAnthill) { // doit alle a gauche
                    tile = Map.getInstance().getTile(this.XPos - 1, this.YPos);
                    Map.getInstance().moveTo(this, tile);
                    this.XPos--;
                } else if (this.YPos < YposAnthill) { // doit monter (affichage)
                    tile = Map.getInstance().getTile(this.XPos, this.YPos - 1);
                    Map.getInstance().moveTo(this, tile);
                    this.YPos--;
                } else if (this.YPos > YposAnthill) { //doit descendre (affichage)
                    tile = Map.getInstance().getTile(this.XPos, this.YPos + 1);
                    Map.getInstance().moveTo(this, tile);
                    this.YPos++;
                }

                break;
            default:


                switch (nb) {
                    case 0: // deplacement vers le haut

                        tile = Map.getInstance().getTile(this.XPos, this.YPos - 1);
                        Map.getInstance().moveTo(this, tile);
                        this.YPos--;

                        break;
                    case 1: // deplacement vers le bas
                        tile = Map.getInstance().getTile(this.XPos, this.YPos + 1);
                        Map.getInstance().moveTo(this, tile);
                        this.YPos++;


                        break;
                    case 2: // deplacement vers la droite
                        tile = Map.getInstance().getTile(this.XPos + 1, this.YPos);
                        Map.getInstance().moveTo(this, tile);
                        this.XPos++;


                        break;
                    case 3: // deplacement vers la gauche
                        tile = Map.getInstance().getTile(this.XPos - 1, this.YPos);
                        Map.getInstance().moveTo(this, tile);
                        this.XPos--;

                        break;
                }
                //verifie diff de nul avant déplacement
                // int[] randomCoord = this.lenomdetamethodedansAnt(this.this.XPos], this.this.YPos])
                //Map.getInstance().MoveTo(this,Map.getInstance().getTiles()[this.XPos][this.YPos],Map.getInstance().getTiles()[random[0][random[1]);
        }
    }

    public void run() {
        System.out.println("hello from commander");
        while (true) { // infini (refelchir a finir)
            try {
                update();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * encore necessaire ??
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
