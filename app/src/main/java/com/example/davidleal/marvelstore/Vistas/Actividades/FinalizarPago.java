package com.example.davidleal.marvelstore.Vistas.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidleal.marvelstore.Aplicacion.preferences;
import com.example.davidleal.marvelstore.DataBase.CarritoBD;
import com.example.davidleal.marvelstore.Interfaces.IPedidoView;
import com.example.davidleal.marvelstore.Modelos.Pedido;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Presentadores.PedidosPresenter;
import com.example.davidleal.marvelstore.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinalizarPago extends BaseActivity implements IPedidoView {
    @BindView(R.id.finalizar_lyt_bancomer)
    LinearLayout dataBancomer;
    @BindView(R.id.finalizar_lyt_banorte)
    LinearLayout dataBanorte;
    @BindView(R.id.finalizar_lyt_oxxo)
    LinearLayout dataOxxo;
    @BindView(R.id.finalizar_lyt_seven)
    LinearLayout dataSeven;
    @BindView(R.id.finalizar_txtv_after)
    TextView fin;

    PedidosPresenter presenter;
    CarritoBD mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pago);
        ButterKnife.bind(this);
        presenter= new PedidosPresenter(this,this,this);
        preferences.getInstance().Initialize(this);
        mydb = new CarritoBD(this);

    }



    @OnClick(R.id.finalizar_imgv_bancomer)
    void showBancomer(){
        if(dataBancomer.getVisibility() == View.GONE){
            dataBancomer.setVisibility(View.VISIBLE);
        }else{
            dataBancomer.setVisibility(View.GONE);
        }
    }
    @OnClick(R.id.finalizar_imgv_banorte)
    void showBanorte(){
        if(dataBanorte.getVisibility() == View.GONE){
            dataBanorte.setVisibility(View.VISIBLE);
        }else{
            dataBanorte.setVisibility(View.GONE);
        }
    }
    @OnClick(R.id.finalizar_imgv_oxxo)
    void showOxxo(){
        if(dataOxxo.getVisibility() == View.GONE){
            dataOxxo.setVisibility(View.VISIBLE);
        }else{
            dataOxxo.setVisibility(View.GONE);
        }
    }
    @OnClick(R.id.finalizar_imgv_seven)
    void showSeven(){
        if(dataSeven.getVisibility() == View.GONE){
            dataSeven.setVisibility(View.VISIBLE);
        }else{
            dataSeven.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.finalizar_txtv_hacer)
    void showFin(){
        if(fin.getVisibility() == View.GONE){
            fin.setVisibility(View.VISIBLE);
        }else{
            fin.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.finalizar_lyt_finalizar)
    void finalizar(){
        Pedido pedido = new Pedido();
        pedido.setCliente(preferences.getInstance().getUserid());
        pedido.setCorreoElectronico(preferences.getInstance().getCorreo());
        pedido.setProductos(pedidos());
        pedido.setFechaPedido(fecha());
        pedido.setEstado("En proceso");
        presenter.postPedido(pedido);

    }

    String pedidos(){
        List<Producto> list = new ArrayList<>();
        list = mydb.getAllProductos();
        String json = new Gson().toJson(list );

        return json;
    }

    String fecha(){
        SimpleDateFormat s = new SimpleDateFormat("yyyy.MM.dd");
        String format = s.format(new Date());
        return format;
    }

    @Override
    public void getPedidoId(Pedido respuesta) {

    }

    @Override
    public void getPedidoCorreo(List<Pedido> respuesta) {

    }



    @Override
    public void postPedido(Pedido respuesta) {
        //Todo: Pedido Creado Correctamente
        mostrarMensaje("Pedido en espera");
        mydb.deleteAllProducto();
        startActivity(new Intent(this.getApplicationContext(), MainActivity.class));

    }

    @Override
    public void Error() {
        //TODO: Error al crear pedido
    }
}
