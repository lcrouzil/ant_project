package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;


/**
 * une seul map par jeu
 */
public class Map implements InterfaceMap {
    private static Map singleton; // static car une seul map

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
                Anthill TempoAntHill = new Anthill(color, XPosAntHill, YPosAntHill, j);
                tiles[XPosAntHill][YPosAntHill].anthill = TempoAntHill; // recupere dans une tempo les anthills
                anthills[j] = TempoAntHill;

                // création des commandant
                CommanderAnt NewCommander;
                for (int i = 0; i < 5; i++) {
                    NewCommander = new CommanderAnt(color, XPosAntHill, YPosAntHill, j, TempoAntHill);
                    tiles[XPosAntHill][YPosAntHill].addAnt(NewCommander);
                    anthills[j].commanders.add(NewCommander);
                }

                //creation des fourmies
                WorkerAnt NewAnt;
                for (int i = 0; i < 50; i++) {
                    NewAnt = new WorkerAnt(color, XPosAntHill, YPosAntHill, j, anthills[j].commanders.get(i % anthills[j].commanders.size()));
                    tiles[XPosAntHill][YPosAntHill].addAnt(NewAnt);
                    anthills[j].workers.add(NewAnt);

                }
            }
        }
    }

    /**
     * run les threads
     */
    public void runthread() {
        Tile tiler;
        Thread[] thread;
        Thread threadQueen;
        int i = 0;
        for (int j = 0; j < 3; j++) {
            tiler = getTile(anthills[j]);
            thread = new Thread[tiler.getAnts().size()];
            threadQueen = new Thread(anthills[j]);
            threadQueen.start();
            for (Ant ant : tiler.getAnts()) {
                thread[i++] = new Thread(ant);
            }
            i = 0; //remise a zero pour la boucle de retour
            for (Thread thread2 : thread) {
                thread2.start();

            }
        }
    }

    /**
     * get la map
     *
     * @return singleton
     */
    public static Map getInstance() {
        if (singleton == null) {
            singleton = new Map();
        }
        return singleton;
    }


    /**
     * creation de la partie graphic
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

    /**
     * recupere la tuile anthill
     *
     * @param anthill
     * @return
     */
    public Tile getTile(Anthill anthill) {
        if (anthill != null) {
            return tiles[anthill.XPos][anthill.YPos];
        }
        return null;
    }

    /**
     * retourne les coord de la tuile dans la map ou null si hors map
     *
     * @param x
     * @param y
     * @return
     */
    public Tile getTile(int x, int y) {
        if (x >= Constants.MAP_SIZE_X || y >= Constants.MAP_SIZE_Y || x < 0 || y < 0) {
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
    public void moveTo(Ant ant, Tile tile) {
        Tile OldTile = getTile(ant.getXPos(), ant.getYPos());

        if (OldTile != null) {
            OldTile.removeAnt(ant);
        }

        tile.addAnt(ant);
    }
}

