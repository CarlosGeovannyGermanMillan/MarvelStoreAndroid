package com.example.davidleal.marvelstore.Vistas.Adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidleal.marvelstore.DataBase.CarritoBD;
import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.Utilerias;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class carritoAdapter extends RecyclerView.Adapter<carritoAdapter.MiCarritoAdapterViewHolder> {

    private List<Producto> items;

    public carritoAdapter(List carrito) {
        this.items = carrito;
    }

    @NonNull
    @Override
    public MiCarritoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);

        return new carritoAdapter.MiCarritoAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MiCarritoAdapterViewHolder holder, final int position) {
        final Producto producto = items.get(position);
        Picasso.get().load(producto.getImagen()).into(holder.imagenProducto);
        holder.precio.setText( Utilerias.parsearNumeroAString(producto.getPrecioImpuesto()*producto.getCantidad()));
        holder.producto.setText(producto.getNombre());
        holder.cantidad.setText( producto.getCantidad() + "");

        holder.imgMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(v.getContext(),
                        "The favorite list would appear on clicking this icon",
                        Toast.LENGTH_LONG).show();
                        */
                //call sqlite
                producto.setCantidad(producto.getCantidad() + 1);
                holder.mydb.updateProducto(producto);
                notifyDataSetChanged();
            }
        });
        holder.imgMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(v.getContext(),
                        "The worst list would appear on clicking this icon",
                        Toast.LENGTH_LONG).show();*/
                //call sqlite
                producto.setCantidad(producto.getCantidad() - 1);
                if (producto.getCantidad() > 0) {
                    holder.mydb.updateProducto(producto);
                    notifyDataSetChanged();
                } else {
                    holder.mydb.deleteProducto(producto.getId());
                    items.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, items.size());
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MiCarritoAdapterViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public
        @BindView(R.id.itemCarrito_imgv_producto)
        ImageView imagenProducto;
        @BindView(R.id.itemCarrito_imgv_plus)
        ImageView imgMas;
        @BindView(R.id.itemCarrito_imgv_minus)
        ImageView imgMenos;
        public
        @BindView(R.id.itemCarrito_txt_precio)
        TextView precio;
        public
        @BindView(R.id.itemCarrito_txt_producto)
        TextView producto;
        @BindView(R.id.itemCarrito_txt_cantidad)
        TextView cantidad;

        CarritoBD mydb;


        public MiCarritoAdapterViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            mydb = new CarritoBD(v.getContext());

        }

    }
}
