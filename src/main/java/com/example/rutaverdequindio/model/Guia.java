package com.example.rutaverdequindio.model;

import java.util.ArrayList;
import java.util.List;

public class Guia {

    private String identificacion;
    private String nombre;
    private List<String> idiomasDominados;
    private String telefono;
    private double tarifaxdia;

    Guia(String identificacion, String nombre, String idiomasDominados, String telefono, double tarifaxdia) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.idiomasDominados = new ArrayList<>();
        this.telefono = telefono;
        this.tarifaxdia = tarifaxdia;
    }

    public boolean habla(String idioma){
        for(String idiomaGuia: idiomasDominados){
            if(idiomaGuia.equalsIgnoreCase(idioma)){
                return true;
            }
        }
        return false;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdiomasDominados() {
        return idiomasDominados;
    }

    public void setIdiomasDominados(String idiomasDominados) {
        this.idiomasDominados = idiomasDominados;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getTarifaxdia() {
        return tarifaxdia;
    }

    public void setTarifaxdia(double tarifaxdia) {
        this.tarifaxdia = tarifaxdia;
    }


}
