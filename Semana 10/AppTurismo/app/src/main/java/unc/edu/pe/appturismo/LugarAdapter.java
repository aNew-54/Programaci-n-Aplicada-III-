package unc.edu.pe.appturismo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import Model.Lugar;
import ViewModel.VMLugar;
public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.LugarAdapterViewHolder> {
    private Context context;
    private VMLugar vmLugar;

    //Interface para escuchar los eventos en el recyclerView (Click sobre algún item)
    private OnLugarClickListener onLugarClickListener;
    public interface OnLugarClickListener{
        void onLugarClick(Lugar lugar);
    }

    public LugarAdapter(Context context,  VMLugar vmLugar){
        this.context = context;
        this.vmLugar = vmLugar;
    }

    //Constructor del Listener
    public void setOnLugarClickListener(OnLugarClickListener onLugarClickListener) {
        this.onLugarClickListener = onLugarClickListener;
    }

    @NonNull
    @Override
    public LugarAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context). inflate(R.layout.item_lugar,parent, false);

        return new LugarAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarAdapter.LugarAdapterViewHolder holder, int position) {
        Lugar lugar = vmLugar.getLugar(position);
        holder.tvNombre.setText(lugar.getNombre());
        holder.tvCiudad.setText(lugar.getCiudad());
        holder.tvTiempo.setText(lugar.getTiempo() + "mins");
        holder.tvValor.setText(String.valueOf(lugar.getValoracion()));
        holder.ivLugar.setImageURI(lugar.getImageId());
        holder.ivLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLugarClickListener.onLugarClick(lugar);

            }
        });
    }

    @Override
    public int getItemCount() {
        return vmLugar.listarLugares().size();
    }

    public class LugarAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre, tvCiudad, tvTiempo, tvValor;
        ImageView ivLugar;
        public LugarAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_nombre);
            tvCiudad = itemView.findViewById(R.id.tv_ciudad);
            tvTiempo = itemView.findViewById(R.id.tv_Tiempo);
            tvValor = itemView.findViewById(R.id.tv_valoración);
            ivLugar = itemView.findViewById(R.id.imageView);

        }
    }


}