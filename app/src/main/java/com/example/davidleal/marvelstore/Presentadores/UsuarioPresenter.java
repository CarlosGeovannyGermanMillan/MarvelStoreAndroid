package com.example.davidleal.marvelstore.Presentadores;

import android.content.Context;
import android.util.Log;

import com.example.davidleal.marvelstore.Interfaces.IBaseView;
import com.example.davidleal.marvelstore.Interfaces.IUsuarioView;
import com.example.davidleal.marvelstore.Manejadores.UsuarioManager;
import com.example.davidleal.marvelstore.Modelos.Usuario;
import com.example.davidleal.marvelstore.R;

import retrofit.RetrofitError;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsuarioPresenter extends BasePresenter {
    IUsuarioView mView;
    private Context mContext;

    public UsuarioPresenter(IBaseView iBaseView, IUsuarioView view, Context context) {
        this.mView = view;
        this.mContext = context;
        this.mBaseView = iBaseView;
    }


    public void getUsuarioId(String id) {
        UsuarioManager manager = new UsuarioManager(mContext);
        observableSubscription = manager.getUsuarioId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usuario>() {

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
                    public void onNext(Usuario respuesta) {
                        if (respuesta != null) {
                            mView.getUsuarioId(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }

    public void getUsuarioCorreo(String correo) {
        UsuarioManager manager = new UsuarioManager(mContext);
        observableSubscription = manager.getUsuarioCorreo(correo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usuario>() {

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
                    public void onNext(Usuario respuesta) {
                        if (respuesta != null) {
                            mView.getUsuarioCorreo(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }

    public void postUsuario(Usuario user) {
        UsuarioManager manager = new UsuarioManager(mContext);
        observableSubscription = manager.postUsuario(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usuario>() {

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
                    public void onNext(Usuario respuesta) {
                        if (respuesta != null) {
                            mView.postUsuario(respuesta);

                        } else
                            mView.Error();
                    }
                });
    }
}
