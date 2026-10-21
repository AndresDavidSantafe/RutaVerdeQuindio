package com.example.rutaverdequindio.model;

public class ReservaFacade {
    private Agencia agencia;
    private PasarelaPago pago;
    private Correo correo;

    public ReservaFacade(Agencia agencia, PasarelaPago pago, Correo correo) {
        this.agencia = agencia;
        this.pago = pago;
        this.correo = correo;
    }

    public String reservar(Reserva reserva, String tarjeta, GeneradorVoucher generador) {
        String error = "";
        if (reserva == null) {return "No se creo la reserva.";}
        if (reserva.isConfirmada()) {return "La reserva ya estaba confirmada.";}
        if (generador == null) return "Debe seleccionar el tipo de voucher";

        if (!agencia.hayCupo(reserva)){
            error = "No hay cupos suficientes o alguna actividad esta suspendida";
        } else if (!ValidadorLuhn.verificarTarjeta(tarjeta)){
            error = "Numero de tarjeta invalido";
        }
        if(!error.isEmpty()){
            correo.enviar(reserva.getCliente().getEmail(), "Reserva rechazada", error);
            return error;
        }

        ResultadoPago resultado = pago.procesarPago(tarjeta, reserva.getTotal(),
                "RV-" + agencia.getSiguienteNumero());
        if(!resultado.isAprobado()){
            correo.enviar(reserva.getCliente().getEmail(), "Reserva rechazada", resultado.getMensaje());
            return resultado.getMensaje();
        }
        try {
            reserva.confirmar(agencia.getSiguienteNumero(), resultado.getAutorizacion());
            agencia.guardarReserva(reserva);
            String voucher = generador.emitir(reserva, agencia);
            correo.enviar(reserva.getCliente().getEmail(), "Reserva confirmada", voucher);
            return voucher;
        } catch (Exception e) {
            agencia.deshacerRegistro(reserva);
            reserva.anularConfirmacion();
            String motivo = "No se pudo terminar la reserva: " + e.getMessage();
            correo.enviar(reserva.getCliente().getEmail(), "Reserva rechazada", motivo);
            return motivo;
        }
    }

}
