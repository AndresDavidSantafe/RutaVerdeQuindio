package com.example.rutaverdequindio.model;

import java.math.BigDecimal;
import java.util.*;


// Composite
public class Paquete implements Producto {

    private String codigo;
    private String nombre;
    private double descuento;
    private ArrayList<Producto> contenido = new ArrayList<>();

    public Paquete(String codigo, String nombre, double descuento) {
        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese código y nombre del paquete.");
        }
        if (descuento < 0 || descuento > 20) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 20.");
        }
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.descuento = descuento;
    }

    public boolean contienePaquete(Paquete buscado) {
        if (this == buscado) return true;
        for (Producto item : contenido) {
            if (item instanceof Paquete) {
                Paquete otro = (Paquete) item;
                if (otro.contienePaquete(buscado)) return true;
            }
        }
        return false;
    }

    public void agregarProducto(Producto producto) {
        if(producto == null){
            throw new IllegalArgumentException("Escoge un producto.");
        }
        if (producto == this) {
            throw new IllegalArgumentException("Un paquete no puede incluirse a sí mismo");
        }
        if (producto instanceof Paquete) {
            Paquete otro = (Paquete) producto;
            if (otro.contienePaquete(this)) {
                throw new IllegalArgumentException("No puedes crear paquetes circulares");
            }
        }
        for (Producto item : contenido) {
            if (item == producto) throw new IllegalArgumentException("Este producto ya fue incluido.");
        }
        for(Actividad nueva: producto.actividades()){
            for(Actividad actual: actividades()){
                if(nueva.getCodigo().equals(actual.getCodigo())){
                    throw new IllegalArgumentException("Actividad repetida " + nueva.getNombre());
                }
            }
        }
        contenido.add(producto);
    }

    public double precioPersona(){
        double precio = 0;
        for(Producto p : contenido){
            precio += p.precioPersona();
        }
        return (precio * (100 - descuento)) / 100;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo es obligatorio");
        }
        this.codigo = codigo.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        if(descuento < 0 || descuento > 20){
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 20.");
        }
        this.descuento = descuento;
    }

    public ArrayList<Producto> getContenido() {
        return new ArrayList<>(contenido);
    }

    public void setContenido(ArrayList<Producto> contenido) {
        if(contenido == null){
            throw new IllegalArgumentException("La lista no puede ser nula.");
        }
        ArrayList<Producto> anterior = this.contenido;
        this.contenido = new ArrayList<>();
        try {
            for(Producto producto : contenido) {
                agregarProducto(producto);
            }
        } catch (IllegalArgumentException e) {
            this.contenido = anterior;
            throw e;
        }
    }

    public int duracion(){
        int horas = 0;
        for(Producto producto : contenido){
            horas += producto.duracion();
        }
        return horas;
    }
    public String codigo() {
        return codigo; }
    public String nombre() {
        return nombre; }

    public ArrayList<Actividad> actividades() {
        ArrayList<Actividad> lista = new ArrayList<>();
        for (Producto producto : contenido) { lista.addAll(producto.actividades()); }
        return lista;
    }


    @Override
    public String toString() {
        return codigo + " - " + nombre + " (-" + descuento + "%)";
    }
}