package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.MoveTo;

import java.util.Random;
import java.math.*;

/**
 * une seul map par jeu
 */
public class Map implements InterfaceMap {
    private static Map singleton; // static car une seul map
    //    private Tile[][] tiles = new Tile[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y];
    public Tile[][] tiles;
    public Anthill[] anthills = new Anthill[3];

    private Map() {
        // création de la map vide
        tiles = new Tile[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y];
        for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
            for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
                this.tiles[i][j] = new Tile(null, i, j);
            }
        }

        //placement des fourmilieres avec fourmis(50 par fourmiliere) et commandants (5 par fourmiliere)
        //for (AnthillColor color : AnthillColor.values()) {
        AnthillColor color;
        for (int j = 0; j < 3; j++) {
            switch (j){
                case 0 :
                    color=AnthillColor.BLUE;
                    break;
                case 1 :
                    color=AnthillColor.GREEN;
                    break;
                case 2 :
                    color=AnthillColor.YELLOW;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + j);
            }

            int XPosAntHill = Constants.randomInt(0, Constants.MAP_SIZE_X - 1);
            int YPosAntHill = Constants.randomInt(0, Constants.MAP_SIZE_Y - 1);
            if (this.tiles[XPosAntHill][YPosAntHill].anthill == null) {
                Anthill TempoAntHill = new Anthill(color, XPosAntHill, YPosAntHill);
                this.tiles[XPosAntHill][YPosAntHill].anthill = TempoAntHill; // recupere dans une tempo les anthills
                anthills[j] = TempoAntHill;


                for (int i = 0; i < 5; i++) {
                    this.tiles[XPosAntHill][YPosAntHill].addAnt(new CommanderAnt(color,XPosAntHill,YPosAntHill));
                }
                Thread thread = new Thread(this.tiles[XPosAntHill][YPosAntHill].Ants.get(0)); // rend l'objet en thread
                thread.start(); //lance le thread
                ;
                for (int i = 0; i < 50; i++) {
                    this.tiles[XPosAntHill][YPosAntHill].addAnt(new WorkerAnt(color,XPosAntHill,YPosAntHill));
                }
                System.out.println(this.tiles[XPosAntHill][YPosAntHill].Ants.size());
            }
        }
    }

    public static Map getInstance() {
        if (singleton == null) {
            singleton = new Map();
        }
        return singleton;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void draw(GraphicsContext gc) {
        for (Tile[] tiles : this.tiles) { //tableau de tableau
            for (Tile tile : tiles) { // recupere les elements du tableau le plus bas
                tile.draw(gc);
            }
        }
    }

    /**
     * recupere la tile de la fourmiliere
     * @param anthill
     * @return
     */
    public Tile GetTile(Anthill anthill) {
        if(anthill!=null) {
            return tiles[anthill.XPos][anthill.YPos];
        }
        return null;
    }

    /**
     * recuepre le tile de la fourmi
     * @param ant
     * @return
     */
    public Tile GetTile(Ant ant) {
        if(ant!=null) {
            return tiles[ant.getXPos()][ant.getYPos()];
        }
        return null;
    }

    public Tile TileOnLeft(Ant ant) {

        if(ant.getYPos()-1!=-1) { //verif bord de map gauche
            return tiles[ant.getXPos()][ant.getYPos()-1];
        }
        return null;
    }

    public Tile TileOnRight(Ant ant) {

        if(ant.getYPos()+1!=Constants.MAP_SIZE_Y) { //verif bord de map droite
            return tiles[ant.getXPos()][ant.getYPos()+1];
        }
        return null;
    }

    public Tile TileOnTop(Ant ant) {

        if(ant.getXPos()+1!=Constants.MAP_SIZE_X) { //verif bord de map sup
            return tiles[ant.getXPos()+1][ant.getYPos()];
        }
        return null;
    }

    public Tile TileOnBottom(Ant ant) {

        if(ant.getXPos()-1!=-1) { //verif bord de map sup
            return tiles[ant.getXPos()-1][ant.getYPos()];
        }
        return null;
    }

    /**
     * déplacement de fourmi
     * @param ant
     * @param tile
     */
    public void MoveTo(Ant ant,Tile tile){
        Tile OldTile = GetTile(ant);
        synchronized (OldTile){
        OldTile.removeAnt(ant);
        }
        synchronized (tile){
        tile.addAnt(ant);
        }
    }

    //TODO
//    public void threadRun() {
//        Tile tempoTile=GetTile(anthills[0]);
//        Thread[] threads = new Thread[tempoTile.ALants.size()];
//        int i = 0;
//        Thread thread = new Thread(anthills[0]);
//        thread.start();
//        for(Ant e: tempoTile.ALants) {
//            threads[i++] = new Thread(e);
//        }
//        for(Thread t : threads) {
//            t.start();
//        }
//        tempoTile=GetTile(anthills[2]);
//        threads = new Thread[tempoTile.ALants.size()];
//        i = 0;
//        thread = new Thread(anthills[1]);
//        thread.start();
//        for(Ant e: tempoTile.ALants) {
//            threads[i++] = new Thread(e);
//        }
//        for(Thread t : threads) {
//            t.start();
//        }
//        tempoTile=GetTile(anthills[1]);
//        threads = new Thread[tempoTile.ALants.size()];
//        i = 0;
//        thread = new Thread(anthills[2]);
//        thread.start();
//        for(Ant e: tempoTile.ALants) {
//            threads[i++] = new Thread(e);
//        }
//        for(Thread t : threads) {
//            t.start();
//        }
//
//    }

}

