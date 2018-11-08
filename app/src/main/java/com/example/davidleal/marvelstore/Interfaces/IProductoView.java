package com.example.davidleal.marvelstore.Interfaces;

import com.example.davidleal.marvelstore.Modelos.Producto;

import java.util.List;

public interface IProductoView extends IBaseView {
    void getMiProducto(Producto respuesta);
    void getMisProductos(List<Producto>  respuesta);
    void getMisProductosCategoria(List<Producto>  respuesta);
    void postProducto(Object respuesta);
    void deleteProducto(Object respuesta);
    void putProducto(Object respuesta);
    void Error();

}
