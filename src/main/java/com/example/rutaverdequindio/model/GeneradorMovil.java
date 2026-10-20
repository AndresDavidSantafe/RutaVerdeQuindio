package com.example.rutaverdequindio.model;

public class GeneradorMovil extends GeneradorVoucher{
    @Override
    protected Voucher crearVoucher() {
        return new VoucherMovil();
    }
}
