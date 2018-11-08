package com.example.davidleal.marvelstore.Vistas.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davidleal.marvelstore.DataBase.CarritoBD;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Presentadores.ProductosPresenter;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.Utilerias;
import com.example.davidleal.marvelstore.Vistas.Actividades.ConfirmacionPago;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.carritoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Carrito extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    public List<Object> list;
    private RecyclerView.Adapter mAdapter;
    public ProgressDialog mDialog;

    public @BindView(R.id.carrito_lyt_pagar)
    LinearLayout pagar;
    public @BindView(R.id.carrito_rcv_carrito)
    RecyclerView carrito;
    public @BindView(R.id.carrito_txt_total)
    TextView total;
    public @BindView(R.id.swipeRefreshLayout_carrito)
    SwipeRefreshLayout mSwipeRefreshLayout;
    CarritoBD mydb;


    public Carrito() {
        // Required empty public constructor
    }

    public static Carrito newInstance() {
        Carrito fragment = new Carrito();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.presenter = new ProductosPresenter(this, this, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);
        ButterKnife.bind(this, view);
        mydb = new CarritoBD(getActivity());
        mostrarCargando("Cargando productos del Carrito, espere.....", "Waiting......");
        this.list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        carrito.setHasFixedSize(true);
        carrito.setLayoutManager(layoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        ConsultaCarrito();
        return view;
    }

    @OnClick(R.id.carrito_lyt_pagar)
    void click_pagar() {
        if (mydb.numberOfRows() > 0) {

            Intent intent = new Intent(getActivity(), ConfirmacionPago.class);
            startActivity(intent);
        }
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
    public void onClick(View view) {

    }


    void ConsultaCarrito() {
        List<Producto> array_list = new ArrayList();
        if (mDialog.isShowing())
            mDialog.dismiss();
        int a = mydb.numberOfRows();
        array_list = mydb.getAllProductos();
        mAdapter = new carritoAdapter(array_list);
        double sum = 0;
        double total = 0;
        for (Producto producto : array_list) {
            sum = producto.getCantidad() * producto.getPrecioImpuesto();
            total += sum;
            sum = 0;
        }

        this.total.setText(Utilerias.parsearNumeroAString(total));
        carrito.setAdapter(mAdapter);
        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        carrito.setItemAnimator(animator);

    }

    void refreshItems() {
        List<Producto> array_list = new ArrayList();
        if (mDialog.isShowing())
            mDialog.dismiss();
        int a = mydb.numberOfRows();
        array_list = mydb.getAllProductos();
        mAdapter = new carritoAdapter(array_list);
        double sum = 0;
        double total = 0;
        for (Producto producto : array_list) {
            sum = producto.getCantidad() * producto.getPrecioImpuesto();
            total += sum;
            sum = 0;
        }

        this.total.setText(Utilerias.parsearNumeroAString(total));
        carrito.setAdapter(mAdapter);
        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        carrito.setItemAnimator(animator);
        mSwipeRefreshLayout.setRefreshing(false);

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and nam
        void onFragmentInteraction(Uri uri);
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


}
