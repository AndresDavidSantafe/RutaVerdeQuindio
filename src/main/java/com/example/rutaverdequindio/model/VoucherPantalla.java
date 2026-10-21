package com.example.rutaverdequindio.model;

public class VoucherPantalla implements Voucher{
   public String generar(Reserva reserva){
       String texto = "Reserva #" + reserva.getNumero() + " - Autorización: " + reserva.getAutorizacion();
       texto = texto + "\nCliente: " + reserva.getCliente().getNombre();
       texto = texto + "\nViaje: " + reserva.getFecha() + " - Viajeros: " + reserva.getViajeros();
       texto = texto + "\nProducto: " + reserva.getProducto().nombre();
       for (Actividad actividad : reserva.getProducto().actividades()) {
           texto = texto + "\n  * " + actividad.getNombre() + " (" + actividad.getMunicipio() + ")";
       }
       if (reserva.getGuia() != null) texto = texto + "\nGuía: " + reserva.getGuia().getNombre();
       if (reserva.isTransporte()) texto = texto + "\nTransporte desde el hotel";
       if (reserva.isSeguro()) texto = texto + "\nSeguro de viaje";
       if (!reserva.getObservaciones().isEmpty()) texto = texto + "\nObservaciones: " + reserva.getObservaciones();
       return texto + "\nTOTAL: $" + reserva.getTotal();
   }
}
