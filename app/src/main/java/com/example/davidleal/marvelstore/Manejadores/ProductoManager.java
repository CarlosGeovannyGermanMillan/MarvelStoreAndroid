package com.example.davidleal.marvelstore.Manejadores;

import android.content.Context;

import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Repositorio.ProductosRepository;

import java.util.List;

import rx.Observable;

public class ProductoManager {
    private Context mContext;
    private ProductosRepository repository;

    public ProductoManager(Context context) {
        this.mContext = context;
        repository = new ProductosRepository();
    }


    public Observable<Producto> getProducto(String id) {
        return repository.getApi().getProducto(id);
    }
    public Observable<List<Producto>> getProductoCategoria(String categoria) {
        return repository.getApi().getProductoCategoria(categoria);
    }

    public Observable<List<Producto>> getProductos() {
        return repository.getApi().getProductos();
    }


}
