package com.example.rutaverdequindio.model;

import java.util.ArrayList;

public interface Producto {

    String codigo();

    String nombre();

    double precioPersona();

    int duracion();

    ArrayList<Actividad> actividades();
}