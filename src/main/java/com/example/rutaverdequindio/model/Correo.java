package com.example.rutaverdequindio.model;

public class Correo {
    public void enviar(String destinatario, String asunto, String mensaje) {
        System.out.println("\n=== CORREO ===");
        System.out.println("Para: " + destinatario);
        System.out.println("Asunto: " + asunto);
        System.out.println(mensaje);
        System.out.println("=======================\n");
    }
}
