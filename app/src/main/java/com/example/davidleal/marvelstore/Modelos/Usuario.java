package com.example.davidleal.marvelstore.Modelos;

public class Usuario {
    public String id;
    public String correoElectronico;

    public Usuario() {
    }

    public Usuario(String id, String correoElectronico) {
        this.id = id;
        this.correoElectronico = correoElectronico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
