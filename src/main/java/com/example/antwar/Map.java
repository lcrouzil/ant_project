package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

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
                //   this.tiles[i][j] = new Tile(null, i, j);
                this.tiles[i][j] = new Tile(null, i, j);
            }
        }

        //placement des fourmilieres avec fourmis(50 par fourmiliere) et commandants (5 par fourmilieres)
        //fourmiliere colore
        AnthillColor color;
        for (int j = 0; j < 3; j++) {
            switch (j) {
                case 0:
                    color = AnthillColor.BLUE;
                    break;
                case 1:
                    color = AnthillColor.GREEN;
                    break;
                case 2:
                    color = AnthillColor.YELLOW;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + j);
            }

            int XPosAntHill = Constants.randomInt(0, Constants.MAP_SIZE_X - 1);
            int YPosAntHill = Constants.randomInt(0, Constants.MAP_SIZE_Y - 1);
            if (tiles[XPosAntHill][YPosAntHill].anthill == null) {
                Anthill TempoAntHill = new Anthill(color, XPosAntHill, YPosAntHill);
                tiles[XPosAntHill][YPosAntHill].anthill = TempoAntHill; // recupere dans une tempo les anthills
                anthills[j] = TempoAntHill;

                // création des commandant
                for (int i = 0; i < 5; i++) {
                    tiles[XPosAntHill][YPosAntHill].addAnt(new CommanderAnt(color, XPosAntHill, YPosAntHill));
                    System.out.println(tiles[XPosAntHill][YPosAntHill].ants);
                }
//                Thread thread = new Thread(tiles[XPosAntHill][YPosAntHill].ants.get(0)); // rend l'objet en thread
//                thread.start(); //lance le thread

                //creation des fourmies
                for (int i = 0; i < 50; i++) {
                    Random rand = new Random();
                    int x = rand.nextInt(Constants.MAP_SIZE_X);
                    int y = rand.nextInt(Constants.MAP_SIZE_Y);
                    tiles[XPosAntHill][YPosAntHill].addAnt(new WorkerAnt(color, XPosAntHill, YPosAntHill));
                    tiles[x][y].addAnt(new WorkerAnt(color, x, y));
                }
                System.out.println(tiles[XPosAntHill][YPosAntHill].ants.size());
            }
        }
    }
//creation de fonction pour thread
    /**
     * get la map
     *
     * @return
     */
    public static Map getInstance() {
        if (singleton == null) {
            singleton = new Map();
        }
        return singleton;
    }

    /**
     * get tuile
     *
     * @return
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * création de la partie graphic
     *
     * @param gc
     */
    public void draw(GraphicsContext gc) {
        for (Tile[] tiles : this.tiles) { //tableau de tableau
            for (Tile tile : tiles) { // recupere les elements du tableau le plus bas
                tile.draw(gc);
            }
        }
    }

    public Tile getTile(Anthill anthill) {
        if (anthill != null) {
            return tiles[anthill.XPos][anthill.YPos];
        }
        return null;
    }

    public Tile getTile(Ant ant) {
        return tiles[ant.getXPos()][ant.getYPos()];
    }


    public Tile getTile(int x, int y) {
        if (x >= Constants.MAP_SIZE_X - 1 || y >= Constants.MAP_SIZE_Y || x < 0 || y < 0) {
            return null;
        }

        return tiles[x][y];
    }

    /**
     * deplacement de la fourmie
     * supprime la fourmi de la case puis la mettre dans la nouvelle tuile
     *
     * @param ant
     * @param tile
     */
    public synchronized void moveTo(Ant ant, Tile tile) {
        Tile OldTile = getTile(ant.getXPos(), ant.getYPos());

        if (OldTile != null) {
            OldTile.removeAnt(ant);
        }

        tile.addAnt(ant);
    }


//    public void MoveTo(Ant ant,Tile tile,tileNext){
//
//        if (tile.getAnts().remove(ant)){
//            tileNext.getAnts().add(ant);
//        }
//    }

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

