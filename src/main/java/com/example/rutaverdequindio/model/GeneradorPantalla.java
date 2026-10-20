package com.example.rutaverdequindio.model;

public class GeneradorPantalla extends GeneradorVoucher{
    @Override
    protected Voucher crearVoucher() {
        return new VoucherPantalla();
    }
}
