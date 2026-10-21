package com.example.rutaverdequindio.controller;

import com.example.rutaverdequindio.model.Actividad;
import com.example.rutaverdequindio.model.Datos;
import com.example.rutaverdequindio.model.Paquete;
import com.example.rutaverdequindio.model.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PaquetesController {
    @FXML private TextField codigo, nombre, descuento;
    @FXML private ComboBox<Paquete> paquetes;
    @FXML private ComboBox<Producto> productos;
    @FXML private TextArea detalle;

    @FXML public void initialize() { actualizar(); }
    @FXML public void actualizar() {
        Paquete elegido = paquetes.getValue();
        paquetes.getItems().setAll(Datos.agencia.getPaquetes());
        productos.getItems().setAll(Datos.agencia.getProductos());
        if (paquetes.getItems().contains(elegido)) paquetes.setValue(elegido);
        mostrar();
    }
    @FXML public void crear() {
        try {
            Paquete nuevo = new Paquete(codigo.getText(), nombre.getText(),
                    Double.parseDouble(descuento.getText().trim()));
            Datos.agencia.agregarPaquete(nuevo);
            actualizar(); paquetes.setValue(nuevo); mostrar();
            codigo.clear(); nombre.clear(); descuento.clear();
            Mensajes.aviso("Paquete creado. Ahora puede agregarle productos.");
        } catch (Exception e) { Mensajes.error("Revise los datos: " + e.getMessage()); }
    }
    @FXML public void agregar() {
        try {
            Paquete paquete = paquetes.getValue();
            Producto producto = productos.getValue();
            if (paquete == null) throw new IllegalArgumentException("Elija el paquete que desea modificar.");
            if (Datos.agencia.paqueteReservado(paquete)) {
                throw new IllegalArgumentException("No puede modificar un paquete que ya tiene reservas.");
            }
            paquete.agregarProducto(producto);
            mostrar();
            Mensajes.aviso("Producto agregado al paquete.");
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    @FXML public void eliminar() {
        try {
            Paquete paquete = paquetes.getValue();
            if (paquete == null) throw new IllegalArgumentException("Seleccione un paquete.");
            Datos.agencia.quitarPaquete(paquete);
            actualizar();
        } catch (Exception e) { Mensajes.error(e.getMessage()); }
    }
    @FXML public void mostrar() {
        Paquete paquete = paquetes.getValue();
        if (paquete == null) { detalle.setText("Seleccione un paquete para ver su contenido."); return; }
        String texto = paquete.nombre() + " - Descuento: " + paquete.getDescuento() + "%";
        texto = texto + "\nContenido:";
        for (Producto producto : paquete.getContenido()) texto = texto + "\n- " + producto.nombre();
        texto = texto + "\n\nActividades finales:";
        for (Actividad actividad : paquete.actividades()) texto = texto + "\n* " + actividad.getNombre();
        texto = texto + "\nDuración total: " + paquete.duracion() + " horas";
        texto = texto + "\nPrecio por persona: $" + paquete.precioPersona();
        detalle.setText(texto);
    }
}
