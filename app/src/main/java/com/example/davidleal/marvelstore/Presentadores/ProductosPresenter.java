package com.example.davidleal.marvelstore.Presentadores;

import android.content.Context;
import android.util.Log;

import com.example.davidleal.marvelstore.Interfaces.IBaseView;
import com.example.davidleal.marvelstore.Interfaces.IProductoView;
import com.example.davidleal.marvelstore.Manejadores.ProductoManager;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.R;

import java.util.List;

import retrofit.RetrofitError;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProductosPresenter extends BasePresenter {
    IProductoView mView;
    private Context mContext;

    public ProductosPresenter(IBaseView iBaseView, IProductoView view, Context context) {
        this.mView = view;
        this.mContext = context;
        this.mBaseView = iBaseView;
    }


    public void getProducto(String id) {
        ProductoManager manager = new ProductoManager(mContext);
        observableSubscription = manager.getProducto(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Producto>() {

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
                    public void onNext(Producto respuesta) {
                        if (respuesta != null) {
                            mView.getMiProducto(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }

    public void getProductosCategoria(String categoria) {
        ProductoManager manager = new ProductoManager(mContext);
        observableSubscription = manager.getProductoCategoria(categoria)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Producto>>() {

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
                    public void onNext(List<Producto> respuesta) {
                        if (respuesta != null) {
                            mView.getMisProductosCategoria(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }

    public void getProductos() {
        ProductoManager manager = new ProductoManager(mContext);
        observableSubscription = manager.getProductos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Producto>>() {

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
                    public void onNext(List<Producto> respuesta) {
                        if (respuesta != null) {
                            mView.getMisProductos(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }
}
