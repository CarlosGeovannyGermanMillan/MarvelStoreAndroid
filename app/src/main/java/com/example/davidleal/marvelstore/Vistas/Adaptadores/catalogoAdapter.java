package com.example.davidleal.marvelstore.Vistas.Adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidleal.marvelstore.Modelos.Producto;
import com.example.davidleal.marvelstore.R;
import com.example.davidleal.marvelstore.Utilerias.ImageUtils;
import com.example.davidleal.marvelstore.Utilerias.Utilerias;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class catalogoAdapter extends RecyclerView.Adapter<catalogoAdapter.MiCatalogoAdapterViewHolder>{

    private List<Producto> items;
    public catalogoAdapter(List carrito){
        this.items = carrito;
    }

    @NonNull
    @Override
    public catalogoAdapter.MiCatalogoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalogo, parent, false);

        return new catalogoAdapter.MiCatalogoAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MiCatalogoAdapterViewHolder holder, int position) {
        Producto producto = items.get(position);
        holder.imagenProducto.setImageResource(R.drawable.icon_hat);
        //holder.imagenProducto.setImageBitmap(ImageUtils.loadBitmap(producto.getImagen()));
        Picasso.get().load(producto.getImagen()).into(holder.imagenProducto);
        holder.precio.setText(Utilerias.parsearNumeroAString(producto.getPrecioImpuesto()));
        holder.producto.setText(producto.getNombre());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MiCatalogoAdapterViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public
        @BindView(R.id.itemCatalogo_imgv_producto)
        ImageView imagenProducto;
        public
        @BindView(R.id.itemCatalogo_txt_precio)
        TextView precio;
        public
        @BindView(R.id.itemCatalogo_txt_producto)
        TextView producto;

        public MiCatalogoAdapterViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
