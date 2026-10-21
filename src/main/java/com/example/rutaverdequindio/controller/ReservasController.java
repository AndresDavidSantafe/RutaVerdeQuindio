package com.example.rutaverdequindio.controller;

import com.example.rutaverdequindio.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReservasController {
    @FXML private ComboBox<Cliente> clientes;
    @FXML private ComboBox<Producto> productos;
    @FXML private ComboBox<Guia> guias;
    @FXML private ComboBox<String> canal;
    @FXML private TextField fecha, viajeros, tarjeta;
    @FXML private CheckBox transporte, seguro;
    @FXML private TextArea observaciones, resultado;
    @FXML private ListView<Reserva> lista;
    private ReservaFacade servicio = new ReservaFacade(Datos.agencia,
            new PagoAndinoAdapter(new PagoAndinoAPI()), new Correo());

    @FXML public void initialize() {
        canal.getItems().addAll("Asesor", "Kiosco", "Móvil");
        canal.setValue("Asesor");
        fecha.setText(Fechas.hoy());
        viajeros.setText("1");
        actualizar();
    }
    @FXML public void actualizar() {
        clientes.getItems().setAll(Datos.agencia.getClientes());
        productos.getItems().setAll(Datos.agencia.getProductos());
        guias.getItems().setAll(Datos.agencia.getGuias());
        lista.getItems().setAll(Datos.agencia.getReservas());
    }
    @FXML public void confirmar() {
        try {
            Reserva reserva = construir();
            GeneradorVoucher generador = new GeneradorPantalla();
            if ("Kiosco".equals(canal.getValue())) generador = new GeneradorKiosco();
            if ("Móvil".equals(canal.getValue())) generador = new GeneradorMovil();
            String mensaje = servicio.reservar(reserva, tarjeta.getText(), generador);
            resultado.setText(mensaje);
            lista.getItems().setAll(Datos.agencia.getReservas());
        } catch (Exception e) {
            resultado.setText("Reserva rechazada: " + e.getMessage());
            if (clientes.getValue() != null) {
                new Correo().enviar(clientes.getValue().getEmail(), "Reserva rechazada", e.getMessage());
            }
            Mensajes.error(e.getMessage());
        }
    }
    @FXML public void calcular() {
        try {
            Reserva reserva = construir();
            resultado.setText("Producto: " + reserva.getProducto().nombre()
                    + "\nDuración: " + reserva.getProducto().duracion() + " horas"
                    + "\nPrecio por persona: $" + reserva.getProducto().precioPersona()
                    + "\nTotal de la reserva: $" + reserva.getTotal());
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    private Reserva construir() {
        int cantidad = Integer.parseInt(viajeros.getText().trim());
        Reserva.Builder datos = Reserva.crear(clientes.getValue(), productos.getValue(),
                fecha.getText().trim(), cantidad);
        datos.guia(guias.getValue());
        datos.transporte(transporte.isSelected());
        datos.seguro(seguro.isSelected());
        datos.observaciones(observaciones.getText());
        return datos.construir();
    }
    @FXML public void verVoucher() {
        Reserva reserva = lista.getSelectionModel().getSelectedItem();
        if (reserva == null) { Mensajes.error("Seleccione una reserva confirmada."); return; }
        resultado.setText(new GeneradorPantalla().emitir(reserva, Datos.agencia));
    }
}
