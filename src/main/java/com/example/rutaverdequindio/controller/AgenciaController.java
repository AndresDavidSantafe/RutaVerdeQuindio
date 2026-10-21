package com.example.rutaverdequindio.controller;

import com.example.rutaverdequindio.model.Agencia;
import com.example.rutaverdequindio.model.Datos;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AgenciaController {
    @FXML private TextField nombre, nit, rnt, direccion, telefono, email, web;
    @FXML public void initialize() {
        Agencia agencia = Datos.agencia;
        nombre.setText(agencia.getNombre()); nit.setText(agencia.getNit());
        rnt.setText(agencia.getRnt()); direccion.setText(agencia.getDireccion());
        telefono.setText(agencia.getTelefono()); email.setText(agencia.getCorreo()); web.setText(agencia.getWeb());
    }
    @FXML public void guardar() {
        Datos.agencia.actualizarDatos(nombre.getText(), nit.getText(), rnt.getText(),
                direccion.getText(), telefono.getText(), email.getText(), web.getText());
        Mensajes.aviso("Datos de agencia actualizados.");
    }
}
