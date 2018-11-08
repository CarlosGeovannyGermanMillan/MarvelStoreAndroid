package com.example.davidleal.marvelstore.Vistas.Actividades;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.davidleal.marvelstore.Interfaces.IUsuarioView;
import com.example.davidleal.marvelstore.Modelos.Usuario;
import com.example.davidleal.marvelstore.Presentadores.UsuarioPresenter;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.Validacion;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Registrarse extends BaseActivity implements IUsuarioView{

    @BindView(R.id.registrarse_cbx_Terminos)
    CheckBox cbxTerminos;
    @BindView(R.id.registrarse_input_nombre)
    EditText txtNombre;
    @BindView(R.id.registrarse_input_email)
    EditText txtEmail;
    @BindView(R.id.registrarse_input_password)
    EditText txtPass;

    UsuarioPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        ButterKnife.bind(this);
        presenter = new UsuarioPresenter(this, this, this);

    }


    @OnClick(R.id.registrarse_btn_signup)
    void Registrarse(){
        if(hasValidation()) {
            Random rand = new Random();
            String id = "US";
            int ab= rand.nextInt((99998 - 1) + 1) + 1;
            id += ab;
            Usuario usuario = new Usuario(id, txtEmail.getText().toString());
            presenter.postUsuario(usuario);
        }else{
            mostrarMensajeError("Intente de nuevo");
        }
    }


    boolean hasValidation() {
        if (!Validacion.hasText(txtNombre, "No puedes usar usuarios vacios"))
            return false;
        if (!Validacion.hasText(txtPass, "No puedes tener contraseñas vacias"))
            return false;
        if (!Validacion.isEmailAddress(txtEmail, true, "Debe ser Correo", "Requerido"))
            return false;
        if(!cbxTerminos.isChecked())
            return false;
        return true;

    }


    @OnClick(R.id.registrarse_cbx_Terminos)
    public void cbxTerminos() {
        mostrarAvisoDePrivacidad();
    }
    @OnClick(R.id.registrarse_txt_Terminos)
    public void textoTerminos(){
        mostrarAvisoDePrivacidad();
    }

    public void mostrarAvisoDePrivacidad() {
        Intent iTerminosCondiciones = new Intent(Registrarse.this, AlertaSesion.class);
        iTerminosCondiciones.putExtra("aceptarTerminos", true);
        startActivityForResult(iTerminosCondiciones, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    cbxTerminos.setChecked(true);
                } else
                    cbxTerminos.setChecked(false);
                break;
        }
    }

    @Override
    public void getUsuarioId(Usuario respuesta) {

    }

    @Override
    public void getUsuarioCorreo(Usuario respuesta) {

    }

    @Override
    public void postUsuario(Usuario respuesta) {
        mostrarMensaje("Usuario dado de alta");
        finish();

    }

    @Override
    public void Error() {
        mostrarMensajeError("Error de datos, intenta de nuevo");
    }
}
