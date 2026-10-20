package com.example.rutaverdequindio.model;

public class GeneradorKiosco extends GeneradorVoucher{
    @Override
    protected Voucher crearVoucher() {
        return new VoucherKiosco();
    }
}
