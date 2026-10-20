package com.example.rutaverdequindio.model;

public class ReservaFacade {
    public Producto producto;
    public Reserva reserva;
    public PasarelaPago pago;

    public ReservaFacade(Producto producto, Reserva reserva, PasarelaPago pago) {
        this.producto = producto;
        this.reserva = reserva;
        this.pago = pago;
    }

    public void reservar(Producto producto, Reserva reserva, PasarelaPago pasarela){
        //if(!Reserva.getViajeros())
    }

}
