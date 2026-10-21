package com.example.rutaverdequindio.model;

public interface PasarelaPago {
    ResultadoPago procesarPago(String numeroTarjeta, double valorCentavos, String referencia);
}
