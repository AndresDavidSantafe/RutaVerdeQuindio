package com.example.rutaverdequindio.model;

public class PagoAndinoAdapter implements PasarelaPago{
    private PagoAndinoAPI pagoAndino;

    public PagoAndinoAdapter(PagoAndinoAPI pagoAndino) {
        this.pagoAndino = pagoAndino;
    }

    public ResultadoPago procesarPago(String numeroTarjeta, double valorPesos, String referencia){
        long centavos = Math.round(valorPesos * 100);
        int respuesta = pagoAndino.procesarCobro(numeroTarjeta, centavos, referencia);
        if(respuesta == 0){
            return new ResultadoPago(true, "Pago aprobado", pagoAndino.ultimaAutorizacion());
        }
        if(respuesta == 14){
            return new ResultadoPago(false, "Tarjeta invalida", "");
        }
        if(respuesta == 51){
            return new ResultadoPago(false, "Fondos insuficientes", "");
        }
        if(respuesta == 91){
            return new ResultadoPago(false, "Pasarela no disponible", "");
        }
            return new ResultadoPago(false, "Error en el pago: " + respuesta, "");
    }

}
