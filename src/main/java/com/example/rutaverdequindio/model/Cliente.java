package com.example.rutaverdequindio.model;

public class Cliente {

    private String documento;
    private String nombre;
    private String telefono;
    private String email;
    private String pais;
    private String idiomaPreferido;

    Cliente (String documento, String nombre, String telefono, String email, String pais,  String idiomaPreferido) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.pais = pais;
        this.idiomaPreferido = idiomaPreferido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIdiomaPreferido() {
        return idiomaPreferido;
    }

    public void setIdiomaPreferido(String idiomaPreferido) {
        this.idiomaPreferido = idiomaPreferido;
    }

    @Override
    public String toString() {
        return nombre + " cedula/pasaporte: " + documento;
    }
}
