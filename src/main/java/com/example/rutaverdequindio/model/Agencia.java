package com.example.rutaverdequindio.model;

import java.util.ArrayList;

public class Agencia {
    private String nombre = "RutaVerde Quindío";
    private String nit;
    private String rnt;
    private String direccion = "Armenia, Quindío";
    private String telefono;
    private String correo;
    private String web ;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Guia> guias = new ArrayList<>();
    private ArrayList<Actividad> actividades = new ArrayList<>();
    private ArrayList<Paquete> paquetes = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private int siguienteNumero = 1;

    public String getNombre() { return nombre; }
    public String getRnt() { return rnt; }
    public String getNit() { return nit; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getWeb() { return web; }
    public void actualizarDatos(String nombre, String nit, String rnt, String direccion,
                                String telefono, String correo, String web) {
        this.nombre = nombre;
        this.nit = nit;
        this.rnt = rnt;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.web = web;
    }
    public ArrayList<Cliente> getClientes() { return new ArrayList<>(clientes); }
    public ArrayList<Guia> getGuias() { return new ArrayList<>(guias); }
    public ArrayList<Actividad> getActividades() { return new ArrayList<>(actividades); }
    public ArrayList<Paquete> getPaquetes() { return new ArrayList<>(paquetes); }
    public ArrayList<Reserva> getReservas() { return new ArrayList<>(reservas); }
    public int getSiguienteNumero() { return siguienteNumero; }

    public void agregarCliente(Cliente cliente) {
        for (Cliente actual : clientes) {
            if (actual.getDocumento().equalsIgnoreCase(cliente.getDocumento())) {
                throw new IllegalArgumentException("Ya existe ese documento.");
            }
        }
        clientes.add(cliente);
    }
    public void agregarGuia(Guia guia) {
        for (Guia actual : guias) {
            if (actual.getIdentificacion().equalsIgnoreCase(guia.getIdentificacion())) {
                throw new IllegalArgumentException("Ya existe ese guía.");
            }
        }
        guias.add(guia);
    }
    public void agregarActividad(Actividad actividad) {
        if (existeCodigo(actividad.getCodigo())) throw new IllegalArgumentException("Código de producto repetido.");
        actividades.add(actividad);
    }
    public void agregarPaquete(Paquete paquete) {
        if (existeCodigo(paquete.codigo())) throw new IllegalArgumentException("Código de producto repetido.");
        paquetes.add(paquete);
    }
    private boolean existeCodigo(String codigo) {
        for (Actividad actividad : actividades) if (actividad.codigo().equalsIgnoreCase(codigo)) return true;
        for (Paquete paquete : paquetes) if (paquete.codigo().equalsIgnoreCase(codigo)) return true;
        return false;
    }
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        lista.addAll(actividades);
        lista.addAll(paquetes);
        return lista;
    }
    public void quitarCliente(Cliente cliente) {
        for (Reserva reserva : reservas) if (reserva.getCliente() == cliente) {
            throw new IllegalArgumentException("El cliente tiene reservas registradas.");
        }
        clientes.remove(cliente);
    }
    public void quitarGuia(Guia guia) {
        for (Reserva reserva : reservas) if (reserva.getGuia() == guia) {
            throw new IllegalArgumentException("Este guía está asignado a una reserva.");
        }
        guias.remove(guia);
    }
    public void quitarActividad(Actividad actividad) {
        for (Paquete paquete : paquetes) {
            for (Actividad contenida : paquete.actividades()) if (contenida == actividad) {
                throw new IllegalArgumentException("La actividad pertenece a un paquete.");
            }
        }
        for (Reserva reserva : reservas) for (Actividad usada : reserva.getProducto().actividades()) {
            if (usada == actividad) throw new IllegalArgumentException("La actividad tiene reservas.");
        }
        actividades.remove(actividad);
    }
    public void quitarPaquete(Paquete paquete) {
        for (Paquete otro : paquetes) for (Producto item : otro.getContenido()) {
            if (item == paquete) throw new IllegalArgumentException("El paquete está dentro de otro.");
        }
        for (Reserva reserva : reservas) if (reserva.getProducto() == paquete) {
            throw new IllegalArgumentException("El paquete tiene reservas.");
        }
        paquetes.remove(paquete);
    }
    public boolean paqueteReservado(Paquete paquete) {
        for (Reserva reserva : reservas) {
            if (reserva.isConfirmada() && reserva.getProducto() instanceof Paquete) {
                Paquete reservado = (Paquete) reserva.getProducto();
                if (reservado.contienePaquete(paquete)) return true;
            }
        }
        return false;
    }
    public boolean hayCupo(Reserva nueva) {
        for (Actividad actividad : nueva.getProducto().actividades()) {
            if (actividad.getEstado() == Estado.SUSPENDIDA) return false;
            int ocupados = 0;
            for (Reserva reserva : reservas) {
                if (reserva.isConfirmada() && reserva.getFecha().equals(nueva.getFecha())) {
                    for (Actividad usada : reserva.getProducto().actividades()) {
                        if (actividad.getCodigo().equalsIgnoreCase(usada.getCodigo())) {
                            ocupados = ocupados + reserva.getViajeros();
                        }
                    }
                }
            }
            if (ocupados + nueva.getViajeros() > actividad.getCupomaximo()) return false;
        }
        return true;
    }
    public void guardarReserva(Reserva reserva) {
        if (!reserva.isConfirmada()) throw new IllegalArgumentException("Reserva no confirmada.");
        reservas.add(reserva);
        siguienteNumero++;
    }
    public void deshacerRegistro(Reserva reserva) {
        if (reservas.remove(reserva)) siguienteNumero--;
    }

    public double ingresos(String inicio, String fin) {
        if (!Fechas.esValida(inicio) || !Fechas.esValida(fin) || inicio.compareTo(fin) > 0) {
            throw new IllegalArgumentException("Periodo incorrecto. Use aaaa-MM-dd.");
        }
        double suma = 0;
        for (Reserva reserva : reservas) {
            if (reserva.isConfirmada() && reserva.getFecha().compareTo(inicio) >= 0
                    && reserva.getFecha().compareTo(fin) <= 0) suma = suma + reserva.getTotal();
        }
        return suma;
    }
    public String productoMasViajeros() {
        ArrayList<String> codigos = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Integer> cantidades = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.isConfirmada()) {
                String codigo = reserva.getProducto().codigo();
                int posicion = codigos.indexOf(codigo);
                if (posicion < 0) {
                    codigos.add(codigo);
                    nombres.add(reserva.getProducto().nombre());
                    cantidades.add(reserva.getViajeros());
                } else {
                    cantidades.set(posicion, cantidades.get(posicion) + reserva.getViajeros());
                }
            }
        }
        if (codigos.isEmpty()) return "Todavía no hay reservas confirmadas.";
        int ganador = 0;
        for (int i = 1; i < cantidades.size(); i++) {
            if (cantidades.get(i) > cantidades.get(ganador)) ganador = i;
        }
        return nombres.get(ganador) + " (" + cantidades.get(ganador) + " viajeros)";
    }
}
