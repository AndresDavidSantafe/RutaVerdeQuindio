package com.example.rutaverdequindio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("principal.fxml"));
        Scene scene = new Scene(loader.load(), 980, 680);
        stage.setTitle("Sistema Reservas - RutaVerde Quindio");
        stage.setScene(scene);
        stage.show();
    }
}
