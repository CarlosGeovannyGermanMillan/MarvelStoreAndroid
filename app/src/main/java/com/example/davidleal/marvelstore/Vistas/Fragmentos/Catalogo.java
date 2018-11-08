package com.example.davidleal.marvelstore.Vistas.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidleal.marvelstore.Interfaces.IProductoView;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.Presentadores.ProductosPresenter;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.ItemClickSupport;
import com.example.davidleal.marvelstore.Vistas.Actividades.DetalleProducto;
import com.example.davidleal.marvelstore.Vistas.Actividades.detalleCarrito;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.carritoAdapter;
import com.example.davidleal.marvelstore.Vistas.Adaptadores.catalogoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Catalogo extends Fragment implements IProductoView{

    private OnFragmentInteractionListener mListener;
    public List<Producto> list;
    private RecyclerView.Adapter mAdapter;
    public ProductosPresenter presenter;
    public ProgressDialog mDialog;

    public @BindView(R.id.catalogo_rcv_catalogo)
    RecyclerView catalogo;
    public @BindView(R.id.catalogo_txt_categoria)
    TextView txtcategoria;
    @BindView(R.id.catalogo_imgv_comic)
    ImageView btnComic;
    @BindView(R.id.catalogo_imgv_recuerdo)
    ImageView btnRecuerdo;
    @BindView(R.id.catalogo_imgv_playera)
    ImageView btnPlayera;
    @BindView(R.id.catalogo_imgv_gorra)
    ImageView btnGorra;
    public @BindView(R.id.swipeRefreshLayout_catalago)
    SwipeRefreshLayout mSwipeRefreshLayout;


    public Catalogo() {
        // Required empty public constructor
    }


    public static Catalogo newInstance() {
        Catalogo fragment = new Catalogo();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new ProductosPresenter(this, this, getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogo, container, false);
        ButterKnife.bind(this, view);
        this.list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        catalogo.setHasFixedSize(true);
        catalogo.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        presenter.getProductosCategoria("Comics");
        mostrarCargando("Cargando productos del Catalogo, espere.....", "Waiting......");
        txtcategoria.setText("Comics");
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        return  view;
    }



    @OnClick(R.id.catalogo_imgv_comic)
    void OnClickComic(){
        presenter.getProductosCategoria("Comics");
        mostrarCargando("Cargando productos del Catalogo, espere.....", "Waiting......");
        txtcategoria.setText("Comics");
        quitBtnBackground();
        // btnComic.setBackgroundResource(R.drawable.tabbackground);
    }

    @OnClick(R.id.catalogo_imgv_gorra)
    void OnClickGorra(){
        presenter.getProductosCategoria("Gorras");
        mostrarCargando("Cargando productos del Catalogo, espere.....", "Waiting......");
        txtcategoria.setText("Gorras");
        quitBtnBackground();
        // btnGorra.setBackgroundResource(R.drawable.tabbackground);
    }

    @OnClick(R.id.catalogo_imgv_playera)
    void OnClickPlayera(){
        presenter.getProductosCategoria("Playeras");
        mostrarCargando("Cargando productos del Catalogo, espere.....", "Waiting......");
        txtcategoria.setText("Playeras");
        quitBtnBackground();
        // btnPlayera.setBackgroundResource(R.drawable.tabbackground);
    }

    @OnClick(R.id.catalogo_imgv_recuerdo)
    void OnClickRecuerdo(){
        presenter.getProductosCategoria("Recuerdos");
        mostrarCargando("Cargando productos del Catalogo, espere.....", "Waiting......");
        txtcategoria.setText("Recuerdos");
        quitBtnBackground();
        // btnRecuerdo.setBackgroundResource(R.drawable.tabbackground);

    }

    void refreshItems(){
        presenter.getProductosCategoria(txtcategoria.getText().toString());

    }

    void quitBtnBackground(){
        btnRecuerdo.setBackgroundResource(0);
        btnPlayera.setBackgroundResource(0);
        btnGorra.setBackgroundResource(0);
        btnComic.setBackgroundResource(0);
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

    @Override
    public void mostrarMensajeError(String mensaje) {

    }

    @Override
    public void getMiProducto(Producto respuesta) {

    }

    @Override
    public void getMisProductos(List<Producto> respuesta) {

    }

    @Override
    public void getMisProductosCategoria(List<Producto> respuesta) {
        list = respuesta;
        mSwipeRefreshLayout.setRefreshing(false);

        mAdapter = new catalogoAdapter(list);
        catalogo.setAdapter(mAdapter);

        ItemClickSupport.addTo(catalogo).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Producto catalogo = list.get(position);
                Intent intent = new Intent(getActivity(), DetalleProducto.class);
                intent.putExtra("productoId",catalogo.getId());
                intent.putExtra("descripcion",catalogo.getDescripcion());
                intent.putExtra("nombre",catalogo.getNombre());
                intent.putExtra("imagen",catalogo.getImagen());
                intent.putExtra("precio",catalogo.getPrecio()+"");
                intent.putExtra("precioimpuesto",catalogo.getPrecioImpuesto()+"");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
        if (mDialog.isShowing())
            mDialog.dismiss();
    }


    @Override
    public void postProducto(Object respuesta) {

    }

    @Override
    public void deleteProducto(Object respuesta) {

    }

    @Override
    public void putProducto(Object respuesta) {

    }

    @Override
    public void Error() {
        if (mDialog.isShowing())
            mDialog.dismiss();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
