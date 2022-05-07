package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import java.math.*;

/**
 * une seul map par jeu
 */
public class Map implements InterfaceMap {
    private static Map singleton; // static car une seul map
    private Tile[][] tiles = new Tile[Constants.MAP_SIZE_X][Constants.MAP_SIZE_Y];
    private Anthill[] anthills = new Anthill[3];

    private Map() {
        // création de la map vide
        for (int i = 0; i < Constants.MAP_SIZE_X; i++) {
            for (int j = 0; j < Constants.MAP_SIZE_Y; j++) {
                this.tiles[i][j] = new Tile(null, i, j);
            }
        }

        //placement des fourmiliere

        for (AnthillColor color : AnthillColor.values()) {
            int XPosAntHill = Constants.randomInt(0, Constants.MAP_SIZE_X-1);
            int YPosAntHill = Constants.randomInt(0, Constants.MAP_SIZE_Y-1);
            if (this.tiles[XPosAntHill][YPosAntHill].anthill == null) {
                this.tiles[XPosAntHill][YPosAntHill].anthill = new Anthill(color,XPosAntHill,YPosAntHill);

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

}

