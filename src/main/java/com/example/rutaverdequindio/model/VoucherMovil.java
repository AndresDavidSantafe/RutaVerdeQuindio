package com.example.rutaverdequindio.model;

public class VoucherMovil implements Voucher{
    public String generar(Reserva reserva) {
        return "RutaVerde #" + reserva.getNumero() + " - " + reserva.getProducto().nombre()
                + " - " + reserva.getFecha() + " - $" + reserva.getTotal();
    }
}
