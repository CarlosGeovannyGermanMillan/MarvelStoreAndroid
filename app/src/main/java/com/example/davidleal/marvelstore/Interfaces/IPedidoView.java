package com.example.davidleal.marvelstore.Interfaces;

import com.example.davidleal.marvelstore.Modelos.Pedido;

import java.util.List;

public interface IPedidoView extends IBaseView {
    void getPedidoId(Pedido respuesta);
    void getPedidoCorreo(List<Pedido> respuesta);
    void postPedido(Pedido respuesta);
    void Error();
}
