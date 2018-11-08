package com.example.davidleal.marvelstore.Vistas.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.davidleal.marvelstore.Aplicacion.preferences;
import com.example.davidleal.marvelstore.Interfaces.IPedidoView;
import com.example.davidleal.marvelstore.Modelos.Pedido;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Presentadores.PedidosPresenter;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.ItemClickSupport;
import com.example.davidleal.marvelstore.Vistas.Actividades.DetalleProducto;
import com.example.davidleal.marvelstore.Vistas.Actividades.detallePedido;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.catalogoAdapter;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.pedidoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pedidos extends Fragment implements IPedidoView{
    private OnFragmentInteractionListener mListener;
    private RecyclerView.Adapter mAdapter;
    public PedidosPresenter presenter;
    public ProgressDialog mDialog;
    public List<Pedido> list;
    public @BindView(R.id.swipeRefreshLayout_pedidos)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.pedidos_rcv_pedidos)
    RecyclerView pedidos;



    public Pedidos() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter= new PedidosPresenter(this,this,getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedidos, container, false);
        ButterKnife.bind(this,view);
        preferences.getInstance().Initialize(this.getActivity());
        this.list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        pedidos.setHasFixedSize(true);
        pedidos.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        presenter.getPedidoCorreo(preferences.getInstance().getCorreo());
        mostrarCargando("Cargando sus pedidos, espere.....", "Waiting......");
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        return view;
    }

    void refreshItems(){
        presenter.getPedidoCorreo(preferences.getInstance().getCorreo());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void getPedidoId(Pedido respuesta) {

    }

    @Override
    public void getPedidoCorreo(List<Pedido> respuesta) {
        //Todo: Lista de pedidos si existe

        if (mDialog.isShowing())
            mDialog.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);
        if(!respuesta.isEmpty()) {
            list = respuesta;

            mAdapter = new pedidoAdapter(list);
            pedidos.setAdapter(mAdapter);

            ItemClickSupport.addTo(pedidos).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Pedido pedido = list.get(position);
                    Intent intent = new Intent(getActivity(), detallePedido.class);
                    intent.putExtra("pedidoId", pedido.getPedidoId());
                    intent.putExtra("cliente", pedido.getCliente());
                    intent.putExtra("correo", pedido.getCorreoElectronico());
                    intent.putExtra("estado", pedido.getEstado());
                    intent.putExtra("fecha", pedido.getFechaPedido());
                    intent.putExtra("productos", pedido.getProductos());
                    intent.putExtra("referencia", pedido.getReferencia());
                    startActivity(intent);
                }
            });
        }else {
            mostrarMensajeError("No tiene pedidos actualmente");
        }

    }

    @Override
    public void postPedido(Pedido respuesta) {

    }

    @Override
    public void Error() {

    }

    public void mostrarCargando(String title, String message) {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
        mDialog = new ProgressDialog(getActivity(), R.style.Theme_MyDialog);
        mDialog.setTitle(title);
        mDialog.setMessage(message);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

    public void mostrarMensajeError(String mensaje) {
        Toast.makeText(getActivity(), mensaje,
                Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
