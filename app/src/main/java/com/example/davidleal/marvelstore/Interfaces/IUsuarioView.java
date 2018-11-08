package com.example.davidleal.marvelstore.Interfaces;


import com.example.davidleal.marvelstore.Modelos.Usuario;

import java.util.List;

public interface IUsuarioView extends IBaseView {

    void getUsuarioId(Usuario respuesta);
    void getUsuarioCorreo(Usuario respuesta);
    void postUsuario(Usuario respuesta);
    void Error();
}
