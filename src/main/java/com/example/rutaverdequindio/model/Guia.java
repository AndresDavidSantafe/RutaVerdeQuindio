package com.example.rutaverdequindio.model;

import java.util.ArrayList;
import java.util.List;

public class Guia {

    private String identificacion;
    private String nombre;
    private ArrayList<String> idiomasDominados;
    private String telefono;
    private double tarifaxdia;

    public Guia(String identificacion, String nombre, String idiomas, String telefono, double tarifaxdia) {
        if(identificacion == null || identificacion.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()
                || idiomas == null || idiomas.trim().isEmpty() || telefono == null || telefono.trim().isEmpty() ){
            throw new IllegalArgumentException("Datos del guia incorrectos");
        }
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tarifaxdia = tarifaxdia;
        setIdiomasDominados(idiomas);
    }

    public boolean habla(String idioma){
        if(idioma == null || idioma.trim().isEmpty()){ return false; }
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

    public ArrayList<String> getIdiomasDominados() {
        return new ArrayList<>(idiomasDominados);
    }

    public void setIdiomasDominados(String idiomas) {
        idiomasDominados = new ArrayList<>();
        if(idiomas != null && !idiomas.trim().isEmpty()){
            String[] separados = idiomas.split(",");
            for(String idioma: separados){
                if(!idioma.trim().isEmpty()){
                    idiomasDominados.add(idioma.trim());
                }
            }
        }
        if(idiomasDominados != null && !idiomasDominados.isEmpty()){
            throw new IllegalArgumentException("Ingresa los idiomas del guia");
        }
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
        if(tarifaxdia >= 0){
            this.tarifaxdia = tarifaxdia;
        }
    }


}
