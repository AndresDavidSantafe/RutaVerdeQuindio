package com.example.rutaverdequindio.model;

import java.util.ArrayList;

public class Reserva {

    private Cliente cliente;
    private Producto producto;
    private String fecha;
    private int viajeros;
    private Guia guia;
    private boolean transporte;
    private boolean seguro;
    private String observaciones;
    private int numero;
    private boolean confirmada;
    private double total;
    private String autorizacion = "";

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

    public static Builder crear(Cliente cliente, Producto producto, String fecha, int viajeros) {
        return new Builder(cliente, producto, fecha, viajeros);
    }

    public static class Builder {
        private Cliente cliente;
        private Producto producto;
        private String fecha;
        private int viajeros;
        private Guia guia;
        private boolean transporte, seguro;
        private String observaciones = "";

        public Builder(Cliente cliente, Producto producto, String fecha, int viajeros) {
            if (cliente == null || producto == null) {
                throw new IllegalArgumentException("Falta cliente o producto.");
            }
            if (!Fechas.esValida(fecha) || fecha.compareTo(Fechas.hoy()) < 0) {
                throw new IllegalArgumentException("La fecha debe ser válida y no puede ser anterior a hoy.");
            }
            if (viajeros < 1 || viajeros > 15) {
                throw new IllegalArgumentException("Se permiten de 1 a 15 viajeros.");
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
            ArrayList<Actividad> actividades = producto.actividades();
            if(actividades.isEmpty()){
                throw new IllegalArgumentException("El producto no tiene actividades.");
            }
            for (int i = 0; i < actividades.size(); i++) {
                Actividad actual = actividades.get(i);
                if (actual.getEstado() == Estado.SUSPENDIDA) {
                    throw new IllegalArgumentException("Actividad suspendida: " + actual.getNombre());
                }
                for (int j = i + 1; j < actividades.size(); j++) {
                    if (actual.getCodigo().equalsIgnoreCase(actividades.get(j).getCodigo())) {
                        throw new IllegalArgumentException("Hay una actividad repetida dentro del paquete.");
                    }
                }
            }
            return new Reserva(this);
        }
    }

    public double calcularTotal() {
        double valor = producto.precioPersona() * viajeros;
        if(transporte){ valor = valor + 35000 * viajeros;}
        if(seguro){ valor = valor + 15000 * viajeros;}
        if(guia != null) {
            int dias = producto.duracion() / 8;
            if (producto.duracion() % 8 != 0) {
                dias = dias + 1;
            }
            valor = valor + guia.getTarifaxdia() * dias;
        }
        return Math.round(valor * 100) / 100.0;
    }

    public void confirmar(int numero, String autorizacion){
        this.numero = numero;
        this.autorizacion = autorizacion;
        confirmada = true;
    }

    public void anularConfirmacion(){
        numero = 0;
        autorizacion = "";
        confirmada = false;
    }

    public Cliente getCliente() { return cliente; }
    public Producto getProducto() { return producto; }
    public String getFecha() { return fecha; }
    public int getViajeros() { return viajeros; }
    public Guia getGuia() { return guia; }
    public boolean isTransporte() { return transporte; }
    public boolean isSeguro() { return seguro; }
    public String getObservaciones() { return observaciones; }
    public int getNumero() { return numero; }
    public boolean isConfirmada() { return confirmada; }
    public double getTotal() { return total; }
    public String getAutorizacion() { return autorizacion; }
    public String toString() { return "#" + numero + " | " + cliente.getNombre() + " | " + fecha + " | $" + total; }



}
