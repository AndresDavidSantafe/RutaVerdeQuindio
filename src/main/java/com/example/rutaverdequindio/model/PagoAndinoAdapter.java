package com.example.rutaverdequindio.model;

public class PagoAndinoAdapter implements PasarelaPago{
    private PagoAndinoAPI pagoAndino;

    public PagoAndinoAdapter(PagoAndinoAPI pagoAndino) {
        this.pagoAndino = pagoAndino;
    }

    @Override
    public String procesarPago(String numeroTarjeta, long valorCentavos, String referencia) {
        int Respuesta = pagoAndino.procesarCobro(numeroTarjeta, valorCentavos, referencia);

        if (Respuesta != 0) {
            return "Ocurrió un error en el pago. Código de error: " + Respuesta;
        }

        return "Cobro exitoso. Autorización: " + pagoAndino.ultimaAutorizacion();
    }
}
