package com.example.rutaverdequindio.model;

public class VoucherKiosco implements Voucher{
    @Override
    public String generar(Reserva reserva) {
        return "Codigo: "+"presentar en la entrada";
    }
}
