package com.example.rutaverdequindio.model;

import java.math.BigDecimal;
import java.util.*;


// Composite
public class Paquete implements Producto {

    private String codigo;
    private String nombre;
    private BigDecimal descuento;
    private List<Producto> contenido = new  ArrayList<>();

    public Paquete(String codigo, String nombre, BigDecimal descuento) {
        if(descuento.compareTo(new BigDecimal("20")) > 0) throw new IllegalArgumentException("Descuento entre 0 y 20%");
        this.codigo = codigo;
        this.nombre = nombre;
        this.descuento = descuento;
    }

    private boolean contienePaquete(Paquete paquete) {
        if (this == paquete){
            return true;
        }
        for(Producto p : contenido){
            if(p instanceof Paquete){
                Paquete subpaquete = (Paquete) p;
                if(subpaquete.contienePaquete(paquete)){
                    return true;
                }
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
        BigDecimal descuentoAplicable = new BigDecimal("100").subtract(descuento);
        return (precio - descuentoAplicable.doubleValue()) / 100;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal descuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public List<Producto> getContenido() {
        return contenido;
    }

    public void setContenido(List<Producto> contenido) {
        this.contenido = contenido;
    }

    public int duracion(){
        int horas = 0;
        for(Producto producto : contenido){
            horas += producto.duracion();
        }
        return horas;
    }
    @Override
    public String codigo() {
        return codigo; }
    @Override
    public String nombre() { return nombre; }

    @Override
    public List<Actividad> actividades() {
        List<Actividad> lista = new ArrayList<>();
        for (Producto producto : contenido) { lista.addAll(producto.actividades()); }
        return lista;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (-" + descuento + "%)";
    }
}