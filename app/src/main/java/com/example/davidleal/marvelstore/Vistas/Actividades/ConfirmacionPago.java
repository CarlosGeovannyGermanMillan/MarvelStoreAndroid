package com.example.davidleal.marvelstore.Vistas.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davidleal.marvelstore.Aplicacion.preferences;
import com.example.davidleal.marvelstore.DataBase.CarritoBD;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Presentadores.ProductosPresenter;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.Utilerias;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.carritoAdapter;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.catalogoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.LinearLayout.VERTICAL;

public class ConfirmacionPago extends BaseActivity {

    public List<Object> list;
    private RecyclerView.Adapter mAdapter;

    public @BindView(R.id.confirmar_lyt_pagar)
    LinearLayout pagar;
    public @BindView(R.id.confirmar_rcv_carrito)
    RecyclerView carrito;
    public @BindView(R.id.confirmar_txt_total)
    TextView total;
    public @BindView(R.id.confirmar_txt_subtotal)
    TextView subtotal;
    CarritoBD mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_pago);

        ButterKnife.bind(this);
        mydb = new CarritoBD(this);
        mostrarCargando("Cargando productos del Carrito, espere.....", "Waiting......");
        this.list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        carrito.setHasFixedSize(true);
        carrito.setLayoutManager(layoutManager);
        ConsultaCarrito();
    }



    void ConsultaCarrito(){
        List<Producto> array_list = new ArrayList();
        if (mDialog.isShowing())
            mDialog.dismiss();
        int a = mydb.numberOfRows();
        array_list = mydb.getAllProductos();
        mAdapter = new catalogoAdapter(array_list);
        double sum =0;
        double subsum =0;
        double total=0;
        double subtotal=0;

        for (Producto producto:array_list) {
            sum = producto.getCantidad() * producto.getPrecioImpuesto();
            subsum = producto.getCantidad() * producto.getPrecio();
            total+=sum;
            subtotal+=subsum;
            sum=0;
            subsum=0;
        }

        this.total.setText(Utilerias.parsearNumeroAString(total));
        this.subtotal.setText(Utilerias.parsearNumeroAString(subtotal));
        carrito.setAdapter(mAdapter);

        if (mDialog.isShowing())
            mDialog.dismiss();


    }

    @OnClick(R.id.confirmar_lyt_pagar)
    void Confirmar(){
        if(preferences.getInstance().getSesion()){
            //TODO: Sesión activa a Pagar
            Intent intent = new Intent(this, FinalizarPago.class);
            intent.putExtra("total",total.getText());
            intent.putExtra("subtotal",subtotal.getText());
            startActivity(intent);
        }else{
            //TODO: Sesion inactiva, iniciar sesion
            startActivity(new Intent(this.getApplicationContext(), IniciarSesion.class));

        }
    }
}
