package com.example.rutaverdequindio.controller;

import com.example.rutaverdequindio.model.Cliente;
import com.example.rutaverdequindio.model.Datos;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ClientesController {
    @FXML private TextField documento, nombre, telefono, email, pais, idioma;
    @FXML private ListView<Cliente> lista;

    @FXML public void initialize() { actualizar(); }
    @FXML public void actualizar() { lista.getItems().setAll(Datos.agencia.getClientes()); }
    @FXML public void seleccionar() {
        Cliente cliente = lista.getSelectionModel().getSelectedItem();
        if (cliente == null) return;
        documento.setText(cliente.getDocumento());
        nombre.setText(cliente.getNombre());
        telefono.setText(cliente.getTelefono());
        email.setText(cliente.getEmail());
        pais.setText(cliente.getPais());
        idioma.setText(cliente.getIdiomaPreferido());
    }
    @FXML public void guardar() {
        try {
            Cliente anterior = null;
            for (Cliente cliente : Datos.agencia.getClientes()) {
                if (cliente.getDocumento().equalsIgnoreCase(documento.getText().trim())) anterior = cliente;
            }
            if (anterior == null) {
                Datos.agencia.agregarCliente(new Cliente(documento.getText(), nombre.getText(),
                        telefono.getText(), email.getText(), pais.getText(), idioma.getText()));
            } else {
                if (nombre.getText().trim().isEmpty() || idioma.getText().trim().isEmpty())
                    throw new IllegalArgumentException("Complete los datos del cliente.");
                anterior.setNombre(nombre.getText());
                anterior.setTelefono(telefono.getText());
                anterior.setEmail(email.getText());
                anterior.setPais(pais.getText());
                anterior.setIdiomaPreferido(idioma.getText());
            }
            actualizar();
            limpiar();
            Mensajes.aviso("Cliente guardado.");
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    @FXML public void eliminar() {
        try {
            Cliente seleccionado = lista.getSelectionModel().getSelectedItem();
            if (seleccionado == null) throw new IllegalArgumentException("Seleccione un cliente de la lista.");
            Datos.agencia.quitarCliente(seleccionado);
            actualizar();
            limpiar();
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    @FXML public void limpiar() {
        documento.clear(); nombre.clear(); telefono.clear(); email.clear(); pais.clear(); idioma.clear();
        lista.getSelectionModel().clearSelection();
    }
}
