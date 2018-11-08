package com.example.davidleal.marvelstore.Vistas.Adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidleal.marvelstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class productoAdapter extends RecyclerView.Adapter<productoAdapter.MisProductosAdapterViewHolder>{

    private List items;
    public productoAdapter(List carrito){
        this.items = carrito;
    }
    @NonNull
    @Override
    public MisProductosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito, parent, false);

        return new productoAdapter.MisProductosAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MisProductosAdapterViewHolder holder, int position) {
        Object object = items.get(position);
        //holder.imagenProducto = object;
        //holder.precio = object;
        //holder.producto = object;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MisProductosAdapterViewHolder extends RecyclerView.ViewHolder {
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

        public MisProductosAdapterViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
