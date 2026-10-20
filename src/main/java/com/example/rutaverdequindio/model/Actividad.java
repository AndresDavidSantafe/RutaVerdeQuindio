package com.example.rutaverdequindio.model;

import java.util.Collections;
import java.util.List;

public class Actividad implements Producto {
    private String codigo;
    private String nombre;
    private String municipio;
    private double duracion; // en horas
    private double precioxpersona;
    private int cupomaximo;
    private Dificultad dificultad;
    private Estado estado;

    public Actividad(String codigo, String nombre, String municipio, double duracion, double precioxpersona, int cupomaximo, Dificultad dificultad, Estado estado) {
        if (precioxpersona < 0) {
            throw new IllegalArgumentException("El precio por persona no puede ser negativo.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.municipio = municipio;
        this.duracion = duracion;
        this.precioxpersona = precioxpersona;
        this.cupomaximo = cupomaximo;
        this.dificultad = dificultad;
        this.estado = estado;
    }


    @Override
    public String codigo() {
        return codigo;
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public double precioPersona() {
        return precioxpersona;
    }

    @Override
    public int duracion() {

        return (int) Math.round(duracion);
    }

    @Override
    public List<Actividad> actividades() {

        return Collections.singletonList(this);
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getPrecioxpersona() {
        return precioxpersona;
    }

    public void setPrecioxpersona(double precioxpersona) {
        if (precioxpersona < 0) {
            throw new IllegalArgumentException("El precio por persona no puede ser negativo.");
        }
        this.precioxpersona = precioxpersona;
    }

    public int getCupomaximo() {
        return cupomaximo;
    }

    public void setCupomaximo(int cupomaximo) {
        this.cupomaximo = cupomaximo;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " ($" + precioxpersona + ")";
    }
}
