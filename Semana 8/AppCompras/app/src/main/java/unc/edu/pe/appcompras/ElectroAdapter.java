package unc.edu.pe.appcompras;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import unc.edu.pe.appcompras.databinding.ItemElectroBinding;

public class ElectroAdapter extends RecyclerView.Adapter<ElectroAdapter.ViewHolder> {

    List<Electrodomestico> lista;

    public ElectroAdapter(List<Electrodomestico> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemElectroBinding binding;

        public ViewHolder(ItemElectroBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemElectroBinding binding = ItemElectroBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Electrodomestico e = lista.get(position);

        holder.binding.tvNombre.setText(e.nombre);
        holder.binding.tvPrecio.setText("S/. " + e.precio);
        holder.binding.imgElectro.setImageResource(e.imagen);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalleCompra.class);
            intent.putExtra("producto", e);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
