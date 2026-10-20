package com.example.rutaverdequindio.model;

public abstract class GeneradorVoucher {
    protected abstract Voucher crearVoucher();

    public final String emitir(Reserva reserva){
        Voucher v = crearVoucher();
        return "Encabezado\n"+v.generar(reserva)+"\nPie de pagina";

    }


}
