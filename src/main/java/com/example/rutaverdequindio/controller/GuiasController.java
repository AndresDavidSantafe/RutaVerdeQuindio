package com.example.rutaverdequindio.controller;

import com.example.rutaverdequindio.model.Datos;
import com.example.rutaverdequindio.model.Guia;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GuiasController {
    @FXML private TextField identificacion, nombre, idiomas, telefono, tarifa;
    @FXML private ListView<Guia> lista;

    @FXML public void initialize() { actualizar(); }
    @FXML public void actualizar() { lista.getItems().setAll(Datos.agencia.getGuias()); }
    @FXML public void seleccionar() {
        Guia guia = lista.getSelectionModel().getSelectedItem();
        if (guia == null) return;
        identificacion.setText(guia.getIdentificacion());
        nombre.setText(guia.getNombre());
        idiomas.setText(String.join(", ", guia.getIdiomasDominados()));
        telefono.setText(guia.getTelefono());
        tarifa.setText("" + guia.getTarifaxdia());
    }
    @FXML public void guardar() {
        try {
            double precio = Double.parseDouble(tarifa.getText().trim());
            Guia anterior = null;
            for (Guia guia : Datos.agencia.getGuias()) {
                if (guia.getIdentificacion().equalsIgnoreCase(identificacion.getText().trim())) anterior = guia;
            }
            if (anterior == null) {
                Datos.agencia.agregarGuia(new Guia(identificacion.getText(), nombre.getText(),
                        idiomas.getText(), telefono.getText(), precio));
            } else {
                anterior.setIdiomasDominados(idiomas.getText());
                anterior.setNombre(nombre.getText());
                anterior.setTelefono(telefono.getText());
                anterior.setTarifaxdia(precio);
            }
            actualizar(); limpiar();
            Mensajes.aviso("Guía guardado.");
        } catch (Exception e) { Mensajes.error("Revise los datos: " + e.getMessage()); }
    }
    @FXML public void eliminar() {
        try {
            Guia guia = lista.getSelectionModel().getSelectedItem();
            if (guia == null) throw new IllegalArgumentException("Seleccione un guía.");
            Datos.agencia.quitarGuia(guia);
            actualizar(); limpiar();
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    @FXML public void limpiar() {
        identificacion.clear(); nombre.clear(); idiomas.clear(); telefono.clear(); tarifa.clear();
        lista.getSelectionModel().clearSelection();
    }
}
