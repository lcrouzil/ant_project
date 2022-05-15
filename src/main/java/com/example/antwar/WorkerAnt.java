package com.example.antwar;

import java.util.ArrayList;

import java.util.Random;

import java.util.TimerTask;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class WorkerAnt extends Ant implements Subscriber {
    public ArrayList<Resource> resources = new ArrayList<Resource>();
    private QueenOrders CommanderOrder = QueenOrders.GO_FIND_RESSOURCE;
    int XposAnthill;
    int YposAnthill;
    private boolean GO_HOME = false;
    private Subscription AboDuWorkerAnt;

    /**
     * @param Color
     * @param x
     * @param y
     */
    public WorkerAnt(AnthillColor Color, int x, int y, int IndexAnthill, CommanderAnt MonCommander) {
        super(Color, x, y, IndexAnthill);
        MonCommander.DemandeAboCommander(this);

    }

    /**
     * mise a jour du worker ant
     * deplacement/ordre/ressource
     */
    public void update() {
        Tile tile;
        ArrayList<Integer> IntList = new ArrayList<Integer>();
        Random random = new Random();

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
        switch (CommanderOrder) {
            case GO_ANTHILL:
                XposAnthill = Map.getInstance().anthills[IndexAnthill].XPos;
                YposAnthill = Map.getInstance().anthills[IndexAnthill].YPos;

                if (this.XPos < XposAnthill) { //doit allé a droite
                    tile = Map.getInstance().getTile(this.XPos + 1, this.YPos);
                    Map.getInstance().moveTo(this, tile);
                    this.XPos++;
                } else if (this.XPos > XposAnthill) { // doit alle a gauche
                    tile = Map.getInstance().getTile(this.XPos - 1, this.YPos);
                    Map.getInstance().moveTo(this, tile);
                    this.XPos--;
                } else if (this.YPos > YposAnthill) { // doit descendre
                    tile = Map.getInstance().getTile(this.XPos, this.YPos - 1);
                    Map.getInstance().moveTo(this, tile);
                    this.YPos--;
                } else if (this.YPos < YposAnthill) { //doit monter
                    tile = Map.getInstance().getTile(this.XPos, this.YPos + 1);
                    Map.getInstance().moveTo(this, tile);
                    this.YPos++;
                } else if (!this.resources.isEmpty()) { //doit decharger les ressources
                    Map.getInstance().anthills[IndexAnthill].addResource(this.resources.remove(this.resources.size() - 1));
                }

                break;

            default:

                if (GO_HOME) { // si fourmi pas pleine
                    XposAnthill = Map.getInstance().anthills[IndexAnthill].XPos;
                    YposAnthill = Map.getInstance().anthills[IndexAnthill].YPos;

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
                    } else if (!this.resources.isEmpty()) { //doit decharger les ressources
                        Map.getInstance().anthills[IndexAnthill].addResource(this.resources.remove(this.resources.size() - 1));
                        if (this.resources.isEmpty()) { // vide donc plus besoin de rester a la maison
                            GO_HOME = false;
                        }
                    }

                } else if (Map.getInstance().getTile(this.XPos, this.YPos).getTileResourceQuantity() > 0) { // si ressource sur case
                    resources.add(Map.getInstance().getTile(this.XPos, this.YPos).TakeResource()); //prend la ressource et l'ajoute
                    if (resources.get(resources.size() - 1) == null) { //dans le cas d'une mauvaise synchro et ressource acquise null retire de la liste (perte d'un tour a améliorer)
                        resources.remove(resources.size() - 1);// retrait de la resource null
                    }
                    if (resources.size() == Constants.AntLoadMax) { //fourmi chargé
                        GO_HOME = true;
                    }
                } else {
                    switch (nb) { //déplacement
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
    }

    /**
     * run des fourmi
     */
    public void run() {
        while (!Constants.FinGame) { // tant que game pas fini
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
        this.AboDuWorkerAnt = subscription;
        this.AboDuWorkerAnt.request(1); // recevoir le prochain message
    }

    @Override
    public void onNext(Object item) {
        this.CommanderOrder = (QueenOrders) item;
        this.AboDuWorkerAnt.request(1); // demande le prochain message
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
