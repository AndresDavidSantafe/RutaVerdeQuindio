package com.example.rutaverdequindio.model;

import javafx.util.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reserva {

    private Cliente cliente;
    private Producto producto;
    private LocalDate fecha;
    private int viajeros;
    private Guia guia;
    private boolean transporte;
    private boolean seguro;
    private String observaciones;
    private int numero;
    private boolean confirmada;
    private BigDecimal total;

    private Reserva(Builder datos) {
        cliente = datos.cliente;
        producto = datos.producto;
        fecha = datos.fecha;
        viajeros = datos.viajeros;
        guia = datos.guia;
        transporte = datos.transporte;
        seguro = datos.seguro;
        observaciones = datos.observaciones;
        total = calcularTotal();
    }

    public static Builder crear(Cliente cliente, Producto producto, LocalDate fecha, int viajeros) {
        return new Builder(cliente, producto, fecha, viajeros);
    }

    public static class Builder {
        private Cliente cliente;
        private Producto producto;
        private LocalDate fecha;
        private int viajeros;
        private Guia guia;
        private boolean transporte, seguro;
        private String observaciones = "";

        public Builder(Cliente cliente, Producto producto, LocalDate fecha, int viajeros) {
            if (cliente == null || producto == null || fecha == null || fecha.isBefore(LocalDate.now())
                    || viajeros < 1 || viajeros > 15) {
                throw new IllegalArgumentException("Escoge cliente, producto, fecha válida y entre 1 y 15 viajeros");
            }
            this.cliente = cliente;
            this.producto = producto;
            this.fecha = fecha;
            this.viajeros = viajeros;
        }

        public Builder guia(Guia nuevo) {
            guia = nuevo;
            return this;
        }

        public Builder transporte(boolean incluir) {
            transporte = incluir;
            return this;
        }

        public Builder seguro(boolean incluir) {
            seguro = incluir;
            return this;
        }

        public Builder observaciones(String texto) {
            observaciones = texto == null ? "" : texto;
            return this;
        }

        public Reserva construir() {
            if (guia != null && !guia.habla(cliente.getIdiomaPreferido())) {
                throw new IllegalArgumentException("El guía no habla el idioma del cliente");
            }
            if (producto.actividades().isEmpty()) {
                throw new IllegalArgumentException("Producto sin actividades");
            }
            for (Actividad actividad : producto.actividades()) {
                if (actividad.getEstado() == Estado.SUSPENDIDA) {
                    throw new IllegalArgumentException("Hay una actividad suspendida");
                }
            }
            return new Reserva(this);
        }
    }

    public double calcularTotal() {
        // ...
    }





}
