package com.example.davidleal.marvelstore.Vistas.Actividades;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidleal.marvelstore.Aplicacion.preferences;
import com.example.davidleal.marvelstore.DataBase.CarritoBD;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Vistas.Fragmentos.Carrito;
import com.example.davidleal.marvelstore.Vistas.Fragmentos.Catalogo;
import com.example.davidleal.marvelstore.Vistas.Fragmentos.Pedidos;
import com.example.davidleal.marvelstore.Vistas.Fragmentos.Usuario;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, Catalogo.OnFragmentInteractionListener {

    String userNombre,userId,userCorreo;
    int drawlogo;
    CarritoBD mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences.getInstance().Initialize(this);

        mydb = new CarritoBD(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GetPreferencias();
        View headerView = navigationView.getHeaderView(0);
        TextView navUsuarioName = (TextView) headerView.findViewById(R.id.navheader_txt_nombreusuario);
        ImageView navUsuario = headerView.findViewById(R.id.navheader_imgv_usuario);

        navUsuarioName.setText(userNombre);
        navUsuario.setImageResource(drawlogo);
        Catalogo catalogofragment = new Catalogo();
        iniciarFragmento(catalogofragment, "Titulo");


    }


    void GetPreferencias(){
        if(preferences.getInstance().getSesion()){
            userNombre = preferences.getInstance().getUser();
            userCorreo = preferences.getInstance().getCorreo();
            userId = preferences.getInstance().getUserid();

        }else{
            drawlogo = R.drawable.ic_user;
            userNombre = "Invitado";
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(preferences.getInstance().getSesion()) {
            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings_vercarrito) {
                //Cerrar Sesion
                preferences.getInstance().setSesion(false);
                preferences.getInstance().setCorreo("");
                preferences.getInstance().setUSerId("");
                preferences.getInstance().setUser("");
                //Eliminar todo de base de datos
                mydb.deleteAllProducto();
                //fragmento catalogo
                Fragment fragment = new Catalogo();
                String title = "Catalogo";
                cambiarFragmento(fragment, title);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        if (id == R.id.nav_perfil) {
            // Handle the camera action
            fragment = new Usuario();
            title = "usuario";
        } else if (id == R.id.nav_catalogo) {
            fragment = new Catalogo();
            title = "Catalogo";
        } else if (id == R.id.nav_carrito) {
            fragment = new Carrito();
            title = "Carrito";
        } else if (id == R.id.nav_pedidos) {
            fragment = new Pedidos();
            title = "Pedidos";
        }

        if(checksesion()){
            cambiarFragmento(fragment,title);
        }else if (id == R.id.nav_catalogo){
            cambiarFragmento(fragment,title);
        } else if (id == R.id.nav_carrito){
            cambiarFragmento(fragment,title);
        } else{
            //Intent iniciar Sesion
            startActivity(new Intent(this.getApplicationContext(), IniciarSesion.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void cambiarFragmento(Fragment fragmento, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragmento,tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void iniciarFragmento(Fragment fragmento, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container,fragmento,tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public boolean checksesion(){
        return preferences.getInstance().getSesion();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {

        this.finish();
    }

}
