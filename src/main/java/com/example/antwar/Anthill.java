package com.example.antwar;

import java.util.ArrayList;

public class Anthill extends Ant {

    private int XPOS;
    private int YPOS;
    ArrayList<WorkerAnt> workers = new ArrayList<WorkerAnt>();
    ArrayList<CommanderAnt> commanders = new ArrayList<CommanderAnt>();
    ArrayList<Resource> resources = new ArrayList<Resource>();
    AnthillColor color;
    public ArrayList<Resource> resource = new ArrayList<Resource>();
    private int score;


    /**
     * constructeur
     *
     * @param Color, x ,y
     */
    public Anthill(AnthillColor Color, int x, int y, int IndexAnthill) {
        super(Color, x, y, IndexAnthill);


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

    /**
     * ajout de resource a la fourmiliere
     *
     * @param recolte
     */
    public void addResource(Resource recolte) {
        this.resource.add(recolte);
    }

    //TODO
    public int getScore() {
        return 0;
    }

    public void run() {
        System.out.println("hello from Queen");
    }
}
