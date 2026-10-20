package com.example.rutaverdequindio.model;

public class Actividad {
    private String codigo;
    private String nombre;
    private String municipio;
    private double duracion;
    private double precioxpersona;
    private int cupomaximo;
    private Dificultad dificultad;
    private Estado estado;

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


}
