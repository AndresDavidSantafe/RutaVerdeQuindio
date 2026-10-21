package com.example.rutaverdequindio.controller;

import com.example.rutaverdequindio.model.Actividad;
import com.example.rutaverdequindio.model.Datos;
import com.example.rutaverdequindio.model.Dificultad;
import com.example.rutaverdequindio.model.Estado;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ActividadesController {
    @FXML private TextField codigo, nombre, municipio, horas, precio, cupo;
    @FXML private ComboBox<Dificultad> dificultad;
    @FXML private ComboBox<Estado> estado;
    @FXML private ListView<Actividad> lista;

    @FXML public void initialize() {
        dificultad.getItems().addAll(Dificultad.values());
        estado.getItems().addAll(Estado.values());
        dificultad.setValue(Dificultad.BAJA);
        estado.setValue(Estado.DISPONIBLE);
        actualizar();
    }
    @FXML public void actualizar() { lista.getItems().setAll(Datos.agencia.getActividades()); }
    @FXML public void seleccionar() {
        Actividad actividad = lista.getSelectionModel().getSelectedItem();
        if (actividad == null) return;
        codigo.setText(actividad.getCodigo());
        nombre.setText(actividad.getNombre());
        municipio.setText(actividad.getMunicipio());
        horas.setText("" + actividad.getDuracion());
        precio.setText("" + actividad.getPrecioxpersona());
        cupo.setText("" + actividad.getCupomaximo());
        dificultad.setValue(actividad.getDificultad());
        estado.setValue(actividad.getEstado());
    }
    @FXML public void guardar() {
        try {
            Actividad nueva = new Actividad(codigo.getText(), nombre.getText(), municipio.getText(),
                    Integer.parseInt(horas.getText().trim()), Double.parseDouble(precio.getText().trim()),
                    Integer.parseInt(cupo.getText().trim()), dificultad.getValue(), estado.getValue());
            Datos.agencia.agregarActividad(nueva);
            actualizar(); limpiar();
            Mensajes.aviso("Actividad registrada.");
        } catch (Exception e) { Mensajes.error("Revise los datos: " + e.getMessage()); }
    }
    @FXML public void cambiarEstado() {
        try {
            Actividad actividad = lista.getSelectionModel().getSelectedItem();
            if (actividad == null || estado.getValue() == null) throw new IllegalArgumentException("Seleccione actividad y estado.");
            actividad.setEstado(estado.getValue());
            actualizar();
            Mensajes.aviso("Estado actualizado.");
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    @FXML public void eliminar() {
        try {
            Actividad actividad = lista.getSelectionModel().getSelectedItem();
            if (actividad == null) throw new IllegalArgumentException("Seleccione una actividad.");
            Datos.agencia.quitarActividad(actividad);
            actualizar(); limpiar();
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    @FXML public void limpiar() {
        codigo.clear(); nombre.clear(); municipio.clear(); horas.clear(); precio.clear(); cupo.clear();
        dificultad.setValue(Dificultad.BAJA); estado.setValue(Estado.DISPONIBLE);
        lista.getSelectionModel().clearSelection();
    }
}
