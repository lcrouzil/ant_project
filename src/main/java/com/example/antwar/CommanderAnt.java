package com.example.antwar;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;


import static com.example.antwar.QueenOrders.GO_ANTHILL;

public class CommanderAnt extends Ant implements Subscriber, Subscription {

    private QueenOrders OrdreReine = QueenOrders.GO_FIND_RESSOURCE;
    private SubmissionPublisher<QueenOrders> CommanderOrder;
    private Subscription AboDuCommander;


    public CommanderAnt(AnthillColor Color, int x, int y, int IndexAnthill, Anthill MaQueen) {
        super(Color, x, y, IndexAnthill);
        this.CommanderOrder = new SubmissionPublisher<>();
        MaQueen.DemandeAboReine(this);

    }

    /**
     * information pour abonnement
     *
     * @param AboCommander
     */
    public void DemandeAboCommander(Subscriber<QueenOrders> AboCommander) {
        this.CommanderOrder.subscribe(AboCommander);
    }

    /**
     * update du commander ( deplacement et ordre)
     */
    public void update() {
        Tile tile;
        switch (OrdreReine) {
            case GO_ANTHILL: //retour fourmiliere
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
                } else if (this.YPos > YposAnthill) { // doit monter (affichage)
                    tile = Map.getInstance().getTile(this.XPos, this.YPos - 1);
                    Map.getInstance().moveTo(this, tile);
                    this.YPos--;
                } else if (this.YPos < YposAnthill) { //doit descendre (affichage)
                    tile = Map.getInstance().getTile(this.XPos, this.YPos + 1);
                    Map.getInstance().moveTo(this, tile);
                    this.YPos++;
                }
                break;

            default: //pas d'ordre retour fourmiliere
                ArrayList<Integer> IntList = new ArrayList<Integer>();
                Random random = new Random();
                //ajout dans une arraylist les deplacements possible
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
                //random sur l'array
                int nb = IntList.get(random.nextInt(IntList.size())); //random sur la list de int

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
        }
    }

    /**
     * run du commandant
     */
    public void run() {

        while (!Constants.FinGame) { // Tant que jeux pas fini
            try {
                update();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void onSubscribe(Subscription subscription) {
        this.AboDuCommander = subscription;
        this.AboDuCommander.request(1); // recevoir le prochain message

    }

    @Override
    public void onNext(Object item) {
        this.OrdreReine = (QueenOrders) item;
        this.CommanderOrder.offer(this.OrdreReine, (sub, order) -> {
            return true;
        });
        this.AboDuCommander.request(1); // demande le prochain message


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
