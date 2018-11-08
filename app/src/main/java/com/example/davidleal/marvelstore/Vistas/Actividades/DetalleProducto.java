package com.example.davidleal.marvelstore.Vistas.Actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidleal.marvelstore.DataBase.CarritoBD;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalleProducto extends BaseActivity {

    private CoordinatorLayout coordinatorLayout;

    @BindView(R.id.detalleproducto_imgv_carrito)
    ImageView imgCarrito;
    @BindView(R.id.detalleproducto_imgv_producto)
    ImageView imgProducto;
    @BindView(R.id.detalleproducto_txt_detallep)
    TextView txtDetalle;
    @BindView(R.id.detalleproducto_txt_nombrep)
    TextView txtNombre;
    @BindView(R.id.detalleproducto_txt_preciop)
    TextView txtPrecio;
    private String productoId;
    // BD Carrito
    CarritoBD mydb;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Bundle extras = getIntent().getExtras();
        ButterKnife.bind(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        if (extras != null) {
            txtDetalle.setText(extras.getString("descripcion"));
            txtNombre.setText(extras.getString("nombre"));
            txtPrecio.setText("$" + extras.getString("precio"));
            Picasso.get().load(extras.getString("imagen")).into(imgProducto);
            productoId = extras.getString("productoId");
        }


        mydb = new CarritoBD(this);
        producto = new Producto();
        producto.setDescripcion(txtDetalle.getText().toString());
        producto.setId(productoId);
        producto.setImagen(extras.getString("imagen"));
        producto.setNombre(txtNombre.getText().toString());
        producto.setPrecio(Double.parseDouble(extras.getString("precio")));
        producto.setPrecioImpuesto(Double.parseDouble(extras.getString("precioimpuesto")));
        producto.setCantidad(0);
        producto.setCategoria(extras.getString("categoria"));


    }

    void SnackBar() {

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Agregado al carrito", Snackbar.LENGTH_LONG)
                .setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Se ha quitado del carrito", Snackbar.LENGTH_LONG);
                        snackbar1.show();


                        CarritoBD admin = new CarritoBD(getApplicationContext());

                        SQLiteDatabase bd = admin.getWritableDatabase();
                        //Haz algo
                        Cursor fila = mydb.getData(productoId);
                        if (fila.moveToFirst()) {
                            int a = fila.getInt(7);
                            producto.setCantidad(--a);

                            if (producto.getCantidad() > 0) {
                                mydb.updateProducto(producto);

                            } else {
                                mydb.deleteProducto(producto.getId());
                            }

                            bd.close();

                        }
                    }
                });
        snackbar.show();
    }


    @OnClick(R.id.detalleproducto_imgv_carrito)
    void OnClickCarrito() {

        CarritoBD admin = new CarritoBD(this);

        SQLiteDatabase bd = admin.getWritableDatabase();
        //Haz algo
        SnackBar();

        Cursor fila = mydb.getData(productoId);
        if (fila.moveToFirst()) {
            int a = fila.getInt(7);
            producto.setCantidad(++a);
            mydb.updateProducto(producto);
        } else {
            producto.setCantidad(1);
            mydb.insertProducto(producto);
        }

        bd.close();

        //this.finish();
    }


}
