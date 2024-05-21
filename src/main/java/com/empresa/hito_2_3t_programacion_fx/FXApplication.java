package com.empresa.hito_2_3t_programacion_fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class FXApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXApplication.class.getResource("views/Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Obtener las dimensiones de la pantalla
        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Ajustar la escena al tamaño de la pantalla
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());

        stage.setTitle("Hito 2, 3T Programación FX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}