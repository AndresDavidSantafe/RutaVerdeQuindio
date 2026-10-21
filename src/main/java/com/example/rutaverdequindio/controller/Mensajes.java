package com.example.rutaverdequindio.controller;

import javafx.scene.control.Alert;

public class Mensajes {
    public static void error(String texto) {
        new Alert(Alert.AlertType.ERROR, texto).showAndWait();
    }

    public static void aviso(String texto) {
        new Alert(Alert.AlertType.INFORMATION, texto).showAndWait();
    }
}
