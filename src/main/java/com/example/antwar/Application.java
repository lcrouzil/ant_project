package com.example.antwar;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Map.getInstance().runthread();
        Temps timercyclejeu= new  Temps();
        timercyclejeu.DepartChrono();
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("AntGame");
        Canvas canvas = new Canvas(Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y);
        stage.setScene(scene);
        root.getChildren().add(canvas);
        stage.setOnCloseRequest(e -> System.exit(0));
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                Map.getInstance().draw(gc);
                if (Constants.FinGame){
                    AnthillColor color0 = Map.getInstance().anthills[0].getAntColor();
                    AnthillColor color1 = Map.getInstance().anthills[1].getAntColor();
                    AnthillColor color2 = Map.getInstance().anthills[2].getAntColor();
                    this.stop();
                    stage.close();

                    int resultat1 = Map.getInstance().anthills[0].getScore();
                    int resultat2 = Map.getInstance().anthills[1].getScore();
                    int resultat3 = Map.getInstance().anthills[2].getScore();

                   if (resultat1 < resultat2 && resultat2 > resultat3){
                        System.out.println("La fourmiliere "+color1+" a gagné avec " + resultat2 + " ressources ");
                    } else if (resultat1 > resultat2 && resultat1 > resultat3) {

                        System.out.println("La fourmiliere "+color0+" a gagné avec " + resultat1 + " ressources");
                    }
                    else {

                        System.out.println("La fourmiliere "+color2+" a gagné avec " + resultat3 + " ressources");

                    }
                    System.out.println("resultat de " + color0 + " " + resultat1 + " ressources");
                    System.out.println("resultat de " + color1 + " " + resultat2 + " ressources");
                    System.out.println("resultat de " + color2 + " " + resultat3 + " ressources");
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }
        }.start();
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}