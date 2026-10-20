package com.example.rutaverdequindio.model;

public class VoucherPantalla implements Voucher{
    @Override
    public String generar(Reserva reserva) {
        return "Actividades :"+ "guia: "+ "Serviocios incluidos: ";
    }
}
