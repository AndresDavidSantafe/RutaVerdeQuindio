package com.example.rutaverdequindio.model;

public class VoucherKiosco implements Voucher{
    public String generar(Reserva reserva) {
        return "Tiquete RV-" + reserva.getNumero() + " | " + reserva.getProducto().nombre()
                + " | " + reserva.getFecha() + " | Presentar en la entrada";
    }
}
