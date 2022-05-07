package com.example.antwar;

import java.util.ArrayList;

public class Anthill extends Ant {

    private int XPOS;
    private int YPOS;
    ArrayList<WorkerAnt> workers = new ArrayList<WorkerAnt>();
    ArrayList<CommanderAnt> commanders = new ArrayList<CommanderAnt>();
    ArrayList<Resource> resources = new ArrayList<Resource>();
    AnthillColor color;
    private int resource ;
    private int score ;


    /**constructeur
     * @param Color, x ,y
     */
    public Anthill(AnthillColor Color, int x, int y) {
        super(Color,x,y);

        this.resource = 0;

//        WorkerAnt workerAnt = new WorkerAnt(color,XPOS,YPOS);
//        this.workers.add(workerAnt);
//        for (int i = 0; i < 50; i++) {
//            WorkerAnt workerAnt = new WorkerAnt(color,XPOS,YPOS);
////            this.workers.add(workerAnt);
//
//
//
//        }

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

    /**ajout de resource a la fourmiliere
     *
     * @param recolte
     */
    public void addResource(int recolte) {
        this.resource += recolte ;
    }
    //TODO
    public int getScore() {
        return 0;
    }

    public void run() {
        System.out.println("hello from Queen");
    }

    /*public void addResource(Resource[]){

    }*/
}
