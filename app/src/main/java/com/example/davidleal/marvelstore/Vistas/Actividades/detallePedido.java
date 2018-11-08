package com.example.davidleal.marvelstore.Vistas.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.ItemClickSupport;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.catalogoAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class detallePedido extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;


    @BindView(R.id.detallePedido_rcv_productos)
    RecyclerView productos;
    @BindView(R.id.detallePedido_txt_correo)
    TextView correo;
    @BindView(R.id.detallePedido_txt_estado)
    TextView estado;
    @BindView(R.id.detallePedido_txt_fechaPedido)
    TextView fechaPedido;
    @BindView(R.id.detallePedido_txt_referencia)
    TextView referencia;
    String pedidosGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);
        Bundle extras = getIntent().getExtras();
        ButterKnife.bind(this);
        if (extras != null) {
            correo.setText(extras.getString("correo"));
            estado.setText(extras.getString("estado"));
            fechaPedido.setText(extras.getString("fecha"));
            referencia.setText(extras.getString("referencia"));
            pedidosGlobal = (extras.getString("productos"));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productos.setHasFixedSize(true);
        productos.setLayoutManager(layoutManager);
        setProductos();

    }


    void setProductos() {
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(pedidosGlobal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Producto>>() {
        }.getType();
        List<Producto> list = gson.fromJson(String.valueOf(jArray), type);
        setRecycler(list);
    }

    void setRecycler(List<Producto> list) {
        mAdapter = new catalogoAdapter(list);
        productos.setAdapter(mAdapter);
    }
}
