package com.example.davidleal.marvelstore.Repositorio;

import com.example.davidleal.marvelstore.Modelos.Pedido;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Servicios.UrlService;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public class PedidoRepository {

    private PedidoRepository.pedidoApi api;

    public PedidoRepository() {

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

        api = restAdapter.create(PedidoRepository.pedidoApi.class);

    }


    public PedidoRepository.pedidoApi getApi() {

        return api;
    }

    public interface pedidoApi {

        @GET("/Pedidos/{id}")
        public Observable<Pedido>
        getPedidosId(@Path("code") String codigo);

        @GET("/Pedidos/Cliente/{code}")
        public Observable<List<Pedido>>
        getPedidoCorreo(@Path("code") String correo);

        @GET("/Pedidos/")
        public Observable<List<Pedido>>
        getPedidos();


        @POST("/Pedidos/")
        public Observable<Pedido> //este object es una respuesta
        postPedido(@Body Pedido producto);


    }
}
