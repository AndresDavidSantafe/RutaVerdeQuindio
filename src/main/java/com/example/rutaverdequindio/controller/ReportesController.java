package com.example.rutaverdequindio.controller;

import com.example.rutaverdequindio.model.Datos;
import com.example.rutaverdequindio.model.Fechas;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReportesController {
    @FXML private TextField inicio, fin;
    @FXML private TextArea salida;

    @FXML public void initialize() { inicio.setText(Fechas.hoy()); fin.setText(Fechas.hoy()); }
    @FXML public void consultar() {
        try {
            double total = Datos.agencia.ingresos(inicio.getText().trim(), fin.getText().trim());
            salida.setText("Ingresos de viajes entre " + inicio.getText() + " y " + fin.getText()
                    + " (fechas incluidas): $" + total
                    + "\n\nProducto con más viajeros: " + Datos.agencia.productoMasViajeros()
                    + "\n\nReservas confirmadas: " + Datos.agencia.getReservas().size());
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
}
