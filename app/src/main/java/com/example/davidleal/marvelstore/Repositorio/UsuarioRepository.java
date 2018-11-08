package com.example.davidleal.marvelstore.Repositorio;

import com.example.davidleal.marvelstore.Modelos.Usuario;
import com.example.davidleal.marvelstore.Servicios.UrlService;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public class UsuarioRepository {
    private UsuarioRepository.usuarioApi api;

    public UsuarioRepository() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(UrlService.urlService)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(UsuarioRepository.usuarioApi.class);

    }


    public UsuarioRepository.usuarioApi getApi() {

        return api;
    }

    public interface usuarioApi {

        @GET("/Usuarios/{id}")
        public Observable<Usuario>
        getUsuarioId(@Path("code") String codigo);

        @GET("/Usuarios/Correo/{code}")
        public Observable<Usuario>
        getUsuarioCorreo(@Path("code") String categoria);

        @POST("/Usuarios/")
        public Observable<Usuario> //este object es una respuesta
        postUsuario(@Body Usuario usuario);


    }
}
