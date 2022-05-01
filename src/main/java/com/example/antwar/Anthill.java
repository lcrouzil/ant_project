package com.example.antwar;

import java.util.ArrayList;

public class Anthill {

    ArrayList<WorkerAnt> workers = new ArrayList<WorkerAnt>();
    ArrayList<CommanderAnt> commanders = new ArrayList<CommanderAnt>();
    ArrayList<Resource> resources = new ArrayList<Resource>();
    AnthillColor color;


    /**
     * merci intelij
     *
     * @param Color
     */
    public Anthill(AnthillColor Color) {

        this.color = color;
    }

    /**
     * retourne la couleur de la fourmi
     *
     * @return
     */
    public AnthillColor Color() {
        return this.color;
    }


    /**
     * retirer la fourmi morte de la liste
     *
     * @param ant
     * @return
     */
    public boolean KillAnt(Ant ant) {
        //si la fourmi appartient a la liste des ouvriere la retirer
        if (workers.contains(ant)) {
            return workers.remove(ant);
        }
        //si la fourmi appartient a la liste des commandant la retirer
        if (commanders.contains(ant)) {
            return commanders.remove(ant);
        }
        // pas de suppression
        return false;

    }

    //TODO
    public int getScore() {

        return 0;
    }

    public void run() {

    }

    /*public void addResource(Resource[]){

    }*/
}
