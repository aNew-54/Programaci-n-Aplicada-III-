package unc.edu.pe.catgalleryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import unc.edu.pe.catgalleryapp.Models.Cat;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private Context context;
    private List<Cat> catList;

    public CatAdapter(Context context, List<Cat> catList) {
        this.context = context;
        this.catList = catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
        notifyDataSetChanged();
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cat, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        Cat cat = catList.get(position);
        holder.tvName.setText(cat.getName());
        Glide.with(context).load(cat.getImageUrl()).into(holder.ivCat);

        //Uso de Intents para abrir otra actividad con los datos del gato
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ActivityDetail.class);
            intent.putExtra("imageUrl", cat.getImageUrl());
            intent.putExtra("name", cat.getName());
            intent.putExtra("origin", cat.getOrigin());
            intent.putExtra("temperament", cat.getTemperament());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCat;
        TextView tvName;

        public CatViewHolder(View itemView) {
            super(itemView);
            ivCat = itemView.findViewById(R.id.ivCat);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
