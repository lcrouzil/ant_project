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
        Map.getInstance();
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("AntGame");
        Canvas canvas = new Canvas( Constants.WINDOW_SIZE_X , Constants.WINDOW_SIZE_Y  );
        stage.setScene(scene);
        root.getChildren().add( canvas );
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                Map.getInstance().draw(gc);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) { }
            }
        }.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}