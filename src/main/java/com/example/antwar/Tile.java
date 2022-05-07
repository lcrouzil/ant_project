package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tile {
    int resources;
    ArrayList<Ant> Ants = new ArrayList<Ant>();
    ArrayList<Resource> Resources = new ArrayList<Resource>();

    ;
    //Map<AnthillColor Color, int Pheromone> pheromones;
    Anthill anthill;
    public final int x_pos;
    public final int y_pos;

    public Tile(Anthill who, int x_pos, int y_pos) {

        this.anthill = who;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.resources = Constants.randomInt(0, 25);
        for (int i = 0; i < resources; i++) {
            this.Resources.add(new Resource(ResourceType.FOOD));// a optimiser (50 au mieux des deux sinon 25 max bouffe)
            this.Resources.add(new Resource(ResourceType.POINT));

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
            System.out.println("fourmi ajouté");
        } else {
            System.out.println("else du add");
        }
    }

    /**
     * supprime fourmi (sert aussi au déplcament)
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

        if (this.anthill != null) {
            switch (this.anthill.getAntColor()) {
                case GREEN -> gc.setFill(Color.GREEN);
                case BLUE -> gc.setFill(Color.BLUE);
                case YELLOW -> gc.setFill(Color.YELLOW);

            }
        }else if (this.Ants.size()>0) {
            switch (this.Ants.get(0).AntColor) {
                case GREEN -> {
                    gc.fillOval(1, 1, 1, 1);
                }

                case BLUE -> gc.setFill(Color.BLUE);
                case YELLOW -> gc.setFill(Color.YELLOW);
            }
        }
        else if (this.resources > 0) {
            int a = this.resources * 255 / 30;
            gc.setFill(Color.rgb(a, 0, 0));
        }


        gc.fillRect(this.x_pos * (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), this.y_pos * (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y), (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y));
        gc.setLineWidth(0.5);
        gc.setStroke(Color.rgb(14, 25, 8));
        gc.strokeRect(this.x_pos * (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), this.y_pos * (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y), (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y));
    }


    public Resource TakeResource() {
        //sécurité
        if (Resources.isEmpty())
        {
            return null;
        }
        //enlever 1 de ressource sur la case et retourne la ressource
        return Resources.remove(0);
    }

    public int getTileResourceQuantity(){
        //sécurité
        if (Resources.isEmpty())
        {
            return 0;
        }
        //retourne la taille des ressources de la case
        return Resources.size();
    }

    /*public ResourceType getResourceType(){


    }*/


}
