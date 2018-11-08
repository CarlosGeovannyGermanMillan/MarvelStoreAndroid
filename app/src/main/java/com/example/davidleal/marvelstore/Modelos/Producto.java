package com.example.davidleal.marvelstore.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    String id;
    String nombre;
    double precio;
    double precioImpuesto;
    String imagen;
    String descripcion;
    String categoria;
    int cantidad;

    public Producto() {
    }

    public Producto(String id, String nombre, double precio, double precioImpuesto, String imagen, String descripcion, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.precioImpuesto = precioImpuesto;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioImpuesto() {
        return precioImpuesto;
    }

    public void setPrecioImpuesto(double precioImpuesto) {
        this.precioImpuesto = precioImpuesto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
