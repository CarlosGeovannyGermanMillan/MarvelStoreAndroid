package com.example.davidleal.marvelstore.Aplicacion;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;

public class preferences {
    //Configuracion
    private final String SHARED_PREFS_FILE = "Preferencias";
    private static preferences mInstance;
    private static SharedPreferences mMyPreferences;
    private Context mContext;


    public static String User = "Suscriptor";
    public static String Correo = "correo_user@company.com";
    public static boolean Sesion = false;
    public static String Userid;
    public static String Img;

    public preferences() {

    }

    public static preferences getInstance() {
        if (mInstance == null) mInstance = new preferences();
        return mInstance;
    }

    public void Initialize(Context ctxt) {
        mContext = ctxt;
        mMyPreferences = mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
    }

    public void clear() {
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.clear();
    }


    public void setUser(String user) {
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.putString(User, user);
        editor.apply();
    }

    public String getUser() {
        return mMyPreferences.getString(User, " ");
    }

    public void setCorreo(String correo) {
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.putString(Correo, correo);
        editor.apply();
    }

    public String getCorreo() {
        return mMyPreferences.getString(Correo, "");
    }

    public  void setUSerId(String userid) {
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.putString(Userid, userid);
        editor.apply();
    }

    public String getUserid() {
        return mMyPreferences.getString(Userid, "");
    }

    public  void setImg(String img) {
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.putString(Img, img);
        editor.apply();
    }

    public String getImg() {
        return mMyPreferences.getString(Img, "");
    }

    public void setSesion(Boolean sesion) {
        preferences.Sesion = sesion;
    }

    public Boolean getSesion() {
        return mMyPreferences.getBoolean("sesion", Sesion);
    }

}
