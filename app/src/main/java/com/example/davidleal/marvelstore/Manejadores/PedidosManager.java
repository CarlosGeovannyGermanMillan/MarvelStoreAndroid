package com.example.davidleal.marvelstore.Manejadores;

import android.content.Context;

import com.example.davidleal.marvelstore.Modelos.Pedido;
import com.example.davidleal.marvelstore.Repositorio.PedidoRepository;

import java.util.List;

import rx.Observable;

public class PedidosManager {

    private Context mContext;
    private PedidoRepository repository;

    public PedidosManager(Context context) {
        this.mContext = context;
        repository = new PedidoRepository();
    }


    public Observable<Pedido> getPedidoId(String id) {
        return repository.getApi().getPedidosId(id);
    }
    public Observable<List<Pedido>> getPedidoCorreo(String correo) {
        return repository.getApi().getPedidoCorreo(correo);
    }

    public Observable<List<Pedido>> getPedidos() {
        return repository.getApi().getPedidos();
    }

    public Observable<Pedido> postPedido(Pedido usuario) {
        return repository.getApi().postPedido(usuario);
    }
}
