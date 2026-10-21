package com.example.rutaverdequindio.model;

import java.text.SimpleDateFormat;

public class Fechas {
    public static boolean esValida(String fecha) {
        if (fecha == null || !fecha.matches("\\d{4}-\\d{2}-\\d{2}")) return false;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            formato.setLenient(false);
            return fecha.equals(formato.format(formato.parse(fecha)));
        } catch (Exception e) {
            return false;
        }
    }
    public static String hoy() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    }
}
