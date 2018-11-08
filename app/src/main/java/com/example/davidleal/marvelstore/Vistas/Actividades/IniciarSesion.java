package com.example.davidleal.marvelstore.Vistas.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidleal.marvelstore.Aplicacion.preferences;
import com.example.davidleal.marvelstore.Interfaces.IUsuarioView;
import com.example.davidleal.marvelstore.Modelos.Usuario;
import com.example.davidleal.marvelstore.Presentadores.UsuarioPresenter;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.Validacion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IniciarSesion extends BaseActivity implements IUsuarioView {

    @BindView(R.id.login_input_password)
    EditText passwordTxt;
    @BindView(R.id.login_input_usuario)
    EditText userTxt;


    UsuarioPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        ButterKnife.bind(this);
        preferences.getInstance().Initialize(this);
        presenter = new UsuarioPresenter(this, this, this);


    }

    @OnClick(R.id.login_btn_login)
    void login() {
        if (hasValidation()) {
            //Enviar al servicio
            presenter.getUsuarioCorreo(userTxt.getText().toString());

        } else {
            mostrarMensajeError("Intente de nuevo");
        }

    }

    @OnClick(R.id.login_link_registrarse)
    void Registrarse() {
        Intent intent = new Intent(this, Registrarse.class);
        startActivity(intent);
    }

    boolean hasValidation() {
        if (!Validacion.hasText(userTxt, "No puedes usar usuarios vacios"))
            return false;
        if (!Validacion.hasText(passwordTxt, "No puedes tener contraseñas vacias"))
            return false;
        if (!Validacion.isEmailAddress(userTxt, true, "Debe ser Correo", "Requerido"))
            return false;


        return true;

    }


    @Override
    public void getUsuarioId(Usuario respuesta) {
        if (respuesta != null) {
            preferences.getInstance().setSesion(true);
            preferences.getInstance().setUser(userTxt.getText().toString());
            preferences.getInstance().setUSerId(userTxt.getText().toString());
            preferences.getInstance().setCorreo(userTxt.getText().toString());
            mostrarMensaje("Sesión Correcta");
            finish();
        } else {
            mostrarMensajeError("Usuario Incorrecto. Intente de nuevo o Registrese");

        }
    }

    @Override
    public void getUsuarioCorreo(Usuario respuesta) {
        if (respuesta != null) {
            preferences.getInstance().setSesion(true);
            preferences.getInstance().setUser(respuesta.getCorreoElectronico());
            preferences.getInstance().setUSerId(respuesta.getId());
            preferences.getInstance().setCorreo(respuesta.getCorreoElectronico());
            mostrarMensaje("Sesión Correcta");
            finish();
        } else {
            mostrarMensajeError("Usuario Incorrecto. Intente de nuevo o Registrese");

        }
    }

    @Override
    public void postUsuario(Usuario respuesta) {

    }

    @Override
    public void Error() {
        mostrarMensaje("No existe, intenta de nuevo");
    }
}
