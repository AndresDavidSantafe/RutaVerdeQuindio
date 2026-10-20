package com.example.rutaverdequindio.model;

import java.math.BigDecimal;
import java.util.List;

public interface Producto {

    String codigo();
    String nombre();
    double precioPersona();
    int duracion();
    List<Actividad> actividades();
}