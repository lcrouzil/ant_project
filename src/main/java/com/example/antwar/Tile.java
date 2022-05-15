package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tile {
    int resources;
    public ArrayList<Ant> ants;
    public ArrayList<Resource> Resources = new ArrayList<Resource>();

    ;
    //Map<AnthillColor Color, int Pheromone> pheromones;
    public Anthill anthill;
    public final int x_pos;
    public final int y_pos;

    public Tile(Anthill who, int x_pos, int y_pos) {

        this.anthill = who;
        this.ants = new ArrayList<>();
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
    public synchronized void addAnt(Ant ant) {
        if (ants != null && !ants.contains(ant)) {
            ants.add(ant);
        } else {

        }
    }

    /**
     * supprime fourmi (sert aussi au déplcament)
     *
     * @param ant
     */
    public synchronized void removeAnt(Ant ant) {
        if (ants.contains(ant)) {
            ants.remove(ant);
        } else {
        }
    }

    /**
     * creation du contexte graphic
     *
     * @param gc
     */
    public void draw(GraphicsContext gc) {

        if (this.anthill != null) { //fourmiliere
            switch (this.anthill.getAntColor()) {
                case GREEN -> gc.setFill(Color.GREEN);
                case BLUE -> gc.setFill(Color.BLUE);
                case YELLOW -> gc.setFill(Color.YELLOW);

            }

            gc.fillRect(this.x_pos * (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), this.y_pos * (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y), (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y));
        } else if (this.ants.size() > 0) { // ant a dessiner
            Ant antadessiner = this.ants.get(this.ants.size() - 1);
            if (antadessiner != null) {
                switch (antadessiner.AntColor) {
                    case GREEN -> gc.setFill(Color.DARKGREEN);
                    case BLUE -> gc.setFill(Color.DARKBLUE);
                    case YELLOW -> gc.setFill(Color.DARKGOLDENROD);
                }
                gc.fillOval(this.x_pos * (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), this.y_pos * (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y), (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y));
            }
        } else { // les ressources avec degradé pour indiqué la quantité
            int a = this.Resources.size() * 255 / 50;
            gc.setFill(Color.rgb(a, 0, a));
            gc.fillRect(this.x_pos * (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), this.y_pos * (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y), (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y));
        }


        //gc.fillRect(this.x_pos * (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), this.y_pos * (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y), (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y));
        gc.setLineWidth(0.5);
        gc.setStroke(Color.rgb(14, 25, 8));
        gc.strokeRect(this.x_pos * (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), this.y_pos * (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y), (Constants.WINDOW_SIZE_X / Constants.MAP_SIZE_X), (Constants.WINDOW_SIZE_Y / Constants.MAP_SIZE_Y));
    }


    /**
     * ramasse les resources de la case
     *
     * @return
     */
    public synchronized Resource TakeResource() {
        //sécurité
        if (Resources.isEmpty()) {
            return null;
        }
        //enlever 1 de ressource sur la case et retourne la ressource
        return Resources.remove(0);
    }

    /**
     * recupere la quantite de ressource
     *
     * @return
     */
    public int getTileResourceQuantity() {
        //sécurité
        if (Resources.isEmpty()) {
            return 0;
        }
        //retourne la taille des ressources de la case
        return Resources.size();
    }

    /**
     * array de ants
     *
     * @return
     */
    public ArrayList<Ant> getAnts() {
        return ants;
    }

    //TODO


    //public ResourceType getResourceType(){}

    public void setAnts(ArrayList<Ant> ants) {
        this.ants = ants;
    }
}
