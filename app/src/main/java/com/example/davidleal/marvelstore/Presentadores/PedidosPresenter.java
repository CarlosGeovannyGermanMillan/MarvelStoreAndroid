package com.example.davidleal.marvelstore.Presentadores;

import android.content.Context;
import android.util.Log;

import com.example.davidleal.marvelstore.Interfaces.IBaseView;
import com.example.davidleal.marvelstore.Interfaces.IPedidoView;
import com.example.davidleal.marvelstore.Manejadores.PedidosManager;
import com.example.davidleal.marvelstore.Modelos.Pedido;
import com.example.davidleal.marvelstore.R;

import java.util.List;

import retrofit.RetrofitError;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PedidosPresenter extends BasePresenter {

    IPedidoView mView;
    private Context mContext;

    public PedidosPresenter(IBaseView iBaseView, IPedidoView view, Context context) {
        this.mView = view;
        this.mContext = context;
        this.mBaseView = iBaseView;
    }

    public void getPedidoCorreo(String correo) {
        PedidosManager manager = new PedidosManager(mContext);
        observableSubscription = manager.getPedidoCorreo(correo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Pedido>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof RetrofitError) {
                            Log.e("a", "Tengo un error");
                            RetrofitError response = (RetrofitError) e;
                            if (response.getKind().name().equals("NETWORK"))
                                mBaseView.mostrarMensajeError(mContext.getString(R.string.str_sin_conexion));
                            else
                                mBaseView.mostrarMensajeError(mContext.getString(R.string.str_error_servidor));
                        }
                    }

                    @Override
                    public void onNext(List<Pedido> respuesta) {
                        if (respuesta != null) {
                            mView.getPedidoCorreo(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }

    public void getPedidoId(String id) {
        PedidosManager manager = new PedidosManager(mContext);
        observableSubscription = manager.getPedidoId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pedido>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof RetrofitError) {
                            Log.e("a", "Tengo un error");
                            RetrofitError response = (RetrofitError) e;
                            if (response.getKind().name().equals("NETWORK"))
                                mBaseView.mostrarMensajeError(mContext.getString(R.string.str_sin_conexion));
                            else
                                mBaseView.mostrarMensajeError(mContext.getString(R.string.str_error_servidor));
                        }
                    }

                    @Override
                    public void onNext(Pedido respuesta) {
                        if (respuesta != null) {
                            mView.getPedidoId(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }

    public void postPedido(Pedido pedido) {
        PedidosManager manager = new PedidosManager(mContext);
        observableSubscription = manager.postPedido(pedido)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pedido>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof RetrofitError) {
                            Log.e("a", "Tengo un error");
                            RetrofitError response = (RetrofitError) e;
                            if (response.getKind().name().equals("NETWORK"))
                                mBaseView.mostrarMensajeError(mContext.getString(R.string.str_sin_conexion));
                            else
                                mBaseView.mostrarMensajeError(mContext.getString(R.string.str_error_servidor));
                        }
                    }

                    @Override
                    public void onNext(Pedido respuesta) {
                        if (respuesta != null) {
                            mView.postPedido(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }
}
