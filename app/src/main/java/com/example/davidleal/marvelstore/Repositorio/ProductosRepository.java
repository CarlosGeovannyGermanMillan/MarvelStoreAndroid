package com.example.davidleal.marvelstore.Repositorio;

import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Servicios.UrlService;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import rx.Observable;

public class ProductosRepository {

    private productoApi api;

    public ProductosRepository() {

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

        api = restAdapter.create(productoApi.class);

    }


    public productoApi getApi() {

        return api;
    }

    public interface productoApi {

        @GET("/Productos/{id}")
        public Observable<Producto>
        getProducto(@Path("code") String codigo);

        @GET("/Productos/porCategoria/{code}")
        public Observable<List<Producto>>
        getProductoCategoria(@Path("code") String categoria);

        @GET("/Productos/")
        public Observable<List<Producto>>
        getProductos();

        /*
        @PUT("/Producto/{code}")
        public Observable<Object>
        putProducto(@Path("code") String codigo);

        @DELETE("/Producto/{code}")
        public Observable<Object>
        deleteProducto(@Path("code") String codigo);

        @POST("/Producto/")
        public Observable<Producto> //este object es una respuesta
        postProducto(@Body Producto producto);
        */

    }
}
