package com.example.davidleal.marvelstore.Vistas.Adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidleal.marvelstore.Modelos.Pedido;
import com.example.davidleal.marvelstore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class pedidoAdapter extends RecyclerView.Adapter<pedidoAdapter.MiPedidoAdapterViewHolder>{

    private List<Pedido> items;
    public pedidoAdapter(List pedidos){
        this.items = pedidos;
    }

    @NonNull
    @Override
    public MiPedidoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);

        return new pedidoAdapter.MiPedidoAdapterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MiPedidoAdapterViewHolder holder, int position) {
        Pedido pedido = items.get(position);
        holder.correo.setText(pedido.getCorreoElectronico());
        holder.estado.setText(pedido.getEstado());
        holder.fechaPedido.setText(pedido.getFechaPedido());
        holder.referencia.setText(pedido.getReferencia());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MiPedidoAdapterViewHolder extends RecyclerView.ViewHolder {
        public
        @BindView(R.id.itemPedido_txt_correo)
        TextView correo;
        public
        @BindView(R.id.itemPedido_txt_estado)
        TextView estado;
        public
        @BindView(R.id.itemPedido_txt_fechaPedido)
        TextView fechaPedido;
        public
        @BindView(R.id.itemPedido_txt_referencia)
        TextView referencia;


        public MiPedidoAdapterViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
