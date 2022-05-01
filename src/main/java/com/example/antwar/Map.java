package com.example.antwar;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import java.math.*;

/**
 * créationd de la class map
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
            int XPosAntHill = Constants.randomInt(0, 500);
            int YPosAntHill = Constants.randomInt(0, 500);
            if (this.tiles[XPosAntHill][YPosAntHill] == null) {
                this.tiles[XPosAntHill][YPosAntHill].anthill = new Anthill(color);

            }

        }



    }

    /**
     * random de int de 0 a 500 (exclue)
     */


    // Etape 3 - Pour pouvoir utiliser un singleton, il faut connaître son instance
    // C'est le but de cette méthode habituellement nommée getInstance.
    //
    public static Map getInstance() {
        if (singleton == null){
            singleton = new Map();
        }
        return singleton;
    }

    public void draw(GraphicsContext gc){
        for(Tile[] tiles : this.tiles){ //tableau de tableau
            for(Tile tile: tiles){ // recupere les elements du tableau le plus bas
                tile.draw(gc);
            }
        }
    }

}

