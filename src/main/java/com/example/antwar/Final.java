package com.example.antwar;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Final {

    public static void Display() {

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Resultat");
        stage.setOnCloseRequest(e -> System.exit(0));

        if (Constants.FinGame) { //fin du jeu

            AnthillColor color0 = Map.getInstance().anthills[0].getAntColor();
            AnthillColor color1 = Map.getInstance().anthills[1].getAntColor();
            AnthillColor color2 = Map.getInstance().anthills[2].getAntColor();

            int resultat1 = Map.getInstance().anthills[0].getScore();
            int resultat2 = Map.getInstance().anthills[1].getScore();
            int resultat3 = Map.getInstance().anthills[2].getScore();
            Label resultat;
            Label resColor0;
            Label resColor1;
            Label resColor2;
            if (resultat1 < resultat2 && resultat2 > resultat3) {
                resultat = new Label("La fourmiliere " + color1 + " a gagné avec " + resultat2 + " ressources ");
            } else if (resultat1 > resultat2 && resultat1 > resultat3) {
                resultat = new Label("La fourmiliere " + color0 + " a gagné avec " + resultat1 + " ressources");
            } else {
                resultat = new Label("La fourmiliere " + color2 + " a gagné avec " + resultat3 + " ressources");

            }
            VBox vbox = new VBox(1);
            resColor0 = new Label("resultat de " + color0 + " " + resultat1 + " ressources");
            resColor1 = new Label("resultat de " + color1 + " " + resultat2 + " ressources");
            resColor2 = new Label("resultat de " + color2 + " " + resultat3 + " ressources");
            vbox.getChildren().addAll(resultat, resColor0, resColor1, resColor2); //addall pour ajout tout label attention nom !=
            Scene scene = new Scene(vbox, 380, 120);
            stage.setScene(scene);
            stage.show();
        }


    }


}


