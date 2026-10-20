package com.example.rutaverdequindio.model;

public interface PasarelaPago {
    String procesarPago(String numeroTarjeta, long valorCentavos, String referencia);
}
