package com.example.davidleal.marvelstore.Manejadores;

import android.content.Context;

import com.example.davidleal.marvelstore.Modelos.Usuario;
import com.example.davidleal.marvelstore.Repositorio.UsuarioRepository;


import rx.Observable;

public class UsuarioManager {

    private Context mContext;
    private UsuarioRepository repository;

    public UsuarioManager(Context context) {
        this.mContext = context;
        repository = new UsuarioRepository();
    }


    public Observable<Usuario> getUsuarioId(String id) {
        return repository.getApi().getUsuarioId(id);
    }
    public Observable<Usuario> getUsuarioCorreo(String correo) {
        return repository.getApi().getUsuarioCorreo(correo);
    }

    public Observable<Usuario> postUsuario(Usuario usuario) {
        return repository.getApi().postUsuario(usuario);
    }

}
