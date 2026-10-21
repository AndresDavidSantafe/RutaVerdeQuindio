package com.example.rutaverdequindio.model;

import java.util.ArrayList;

public class Actividad implements Producto {
    private String codigo;
    private String nombre;
    private String municipio;
    private int duracion; // en horas
    private double precioxpersona;
    private int cupomaximo;
    private Dificultad dificultad;
    private Estado estado;

    public Actividad(String codigo, String nombre, String municipio, int duracion, double precioxpersona, int cupomaximo, Dificultad dificultad, Estado estado) {
        if (precioxpersona < 0) {
            throw new IllegalArgumentException("El precio por persona no puede ser negativo.");
        }
        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()
                || municipio == null || municipio.trim().isEmpty()) {
            throw new IllegalArgumentException("Complete el código, nombre y municipio.");
        }
        if (duracion < 1 || precioxpersona < 0 || cupomaximo < 1 || dificultad == null || estado == null) {
            throw new IllegalArgumentException("Revise duración, precio, cupo, dificultad y estado.");
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
        return duracion;
    }

    public ArrayList<Actividad> actividades() {
        ArrayList<Actividad> lista = new ArrayList<>();
        lista.add(this);
        return lista;
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

    public void setDuracion(int duracion) {
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
