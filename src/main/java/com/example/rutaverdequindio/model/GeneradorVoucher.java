package com.example.rutaverdequindio.model;

public abstract class GeneradorVoucher {
    protected abstract Voucher crearVoucher();
    protected String separador() {return "\n";}

    public String emitir(Reserva reserva, Agencia agencia) {
        Voucher voucher = crearVoucher();
        return agencia.getNombre() + " | RNT: " + agencia.getRnt()
                + separador() + voucher.generar(reserva)
                + separador() + "Cancelación sin costo hasta 48 horas antes del viaje.";
    }
}