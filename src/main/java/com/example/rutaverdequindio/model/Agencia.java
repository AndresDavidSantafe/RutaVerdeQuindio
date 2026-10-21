package com.example.rutaverdequindio.model;

import java.util.List;

public class Agencia {
    private List<Cliente> listClientes;
    private List<Producto> listProductos;
    private List<Guia> listGuias;


    public boolean agregarCliente(String documento){
        if(documento!=null) {
            for (Cliente cliente : listClientes) {
                if (!cliente.getDocumento().equals(documento)) {
                    listClientes.add(cliente);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean buscarProducto(String codigo){
        if(codigo!=null){
            for(Producto producto:listProductos){
                if(producto.codigo().equals(codigo)){
                    return true;
                }
            }
        }
        return false;
    }


}
