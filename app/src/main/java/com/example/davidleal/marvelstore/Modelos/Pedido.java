package com.example.davidleal.marvelstore.Modelos;

public class Pedido {
    String pedidoId;
    String cliente;
    String correoElectronico;
    String fechaPedido;
    String estado;
    String productos;
    String referencia;

    public Pedido() {
    }

    public Pedido(String pedidoId, String cliente, String correoElectronico, String fechaPedido, String estado, String productos, String referencia) {
        this.pedidoId = pedidoId;
        this.cliente = cliente;
        this.correoElectronico = correoElectronico;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.productos = productos;
        this.referencia = referencia;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }
}
