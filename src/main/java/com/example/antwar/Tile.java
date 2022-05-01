package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tile {
    ArrayList<Resource> resources = new ArrayList<Resource>();
    ArrayList<Ant> Ants = new ArrayList<Ant>();
    ;
    //Map<AnthillColor Color, int Pheromone> pheromones;
    Anthill anthill;
    public final int x_pos;
    public final int y_pos;

    public Tile(Anthill who, int x_pos, int y_pos) {

        this.anthill = who;
        this.x_pos = x_pos;
        this.y_pos = y_pos;

        for (ResourceType collecte : ResourceType.values()) {
            int a = Constants.randomInt(0, 30);
            for (int i = 0; i < a; i++) {
                this.resources.add(new Resource(collecte));

            }

        }

    }


    /**
     * ajoute une fourmi a la fourmiliere
     *
     * @param ant
     */
    public void addAnt(Ant ant) {
        if (!Ants.contains(ant)) {
            Ants.add(ant);
        } else {
        }
    }

    /**
     * supprime fourmi de la fourmiliere
     *
     * @param ant
     */
    public void removeAnt(Ant ant) {
        if (Ants.contains(ant)) {
            Ants.remove(ant);
        } else {
        }
    }

    /**
     * dépose la nouriture a la fourmiliere
     *
     * @param nourriture
     * @param ant
     */
    public void dropRessource(Resource nourriture, Ant ant) {
        //enlever nourriture de la fourmi
        //ajouter nourriture a fourmiliere
        //ajout de point ??

    }

    public void draw(GraphicsContext gc) {
        if (this.resources.size() > 0) {
            gc.setFill(Color.rgb(255, 0, 0));

        }
        gc.fillRect(this.x_pos * 2.5, this.y_pos * 2.5, 2.5, 2.5);
        gc.setLineWidth(0.5);
        gc.setStroke(Color.rgb(14, 25, 8));
        gc.strokeRect(this.x_pos * 2.5, this.y_pos * 2.5, 2.5, 2.5);
    }

    /*
    public Resource TakeResource() {
        //enlever 1 de ressource sur la case
        //ajoutter un de ressource sur la fourmi
        // return quoi ?
    }

    public int getTileResourceQuantity(){

    }

    public ResourceType getResourceType(){


    }*/


}
