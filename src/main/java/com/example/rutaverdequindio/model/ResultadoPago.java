package com.example.rutaverdequindio.model;

public class ResultadoPago {
    private boolean aprobado;
    private String mensaje;
    private String autorizacion;

    ResultadoPago(boolean aprobado, String mensaje, String autorizacion) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.autorizacion = autorizacion;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

}
