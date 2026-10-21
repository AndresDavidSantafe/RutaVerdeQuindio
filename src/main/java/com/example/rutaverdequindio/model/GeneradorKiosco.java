package com.example.rutaverdequindio.model;

public class GeneradorKiosco extends GeneradorVoucher{
    protected Voucher crearVoucher() {
        return new VoucherKiosco();
    }
    protected String separador() {return " | ";}
}
