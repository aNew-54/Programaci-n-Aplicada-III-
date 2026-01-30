package unc.edu.pe.appturismo;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import Model.Lugar;
import ViewModel.VMLugar;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {
    private Context context;
    private VMLugar vmLugar;
    //private static LayoutInflater inflate=null;

    //interface para escuchar los eventos en el rv (click sobre algún item)
    private OnLugarClickListener onLugarClickListener;
    public interface OnLugarClickListener{
        void onLugarClick(Lugar lugar);
    }

    public void setOnLugarClickListener(OnLugarClickListener onLugarClickListener) {
        this.onLugarClickListener = onLugarClickListener;
    }
    //

    public LugarAdapter(Context context, VMLugar vmLugar) {
        this.context = context;
        this.vmLugar = vmLugar;
        //inflate=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public LugarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_lugar,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarAdapter.ViewHolder holder, int position) {
        Lugar lugar=vmLugar.getLugar(position);
        holder.tvNombre.setText(lugar.getNombre());
        holder.tvCiudad.setText(lugar.getCiudad());
        holder.tvTiempo.setText(lugar.getTiempo()+" min");
        holder.tvValor.setText(String.valueOf(lugar.getValoracion()));
        holder.ivLugar.setImageURI(lugar.getImageId());
        holder.ivLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLugarClickListener.onLugarClick(lugar);
            }
        });
//        // Registrar menú contextual para este item
//        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                menu.setHeaderTitle("Opciones");
//                menu.add(0, 1, 0, "Editar");
//                menu.add(0, 2, 1, "Eliminar");
//            }
//        });

        // Configurar menu emergente (PopupMenu) en click largo
        holder.itemView.setOnLongClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, v);
            popup.inflate(R.menu.menu_emergente);
            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.im_editar) {
                    // Acción editar
                    return true;
                } else if (itemId == R.id.im_eliminar) {
                    // Acción eliminar
                    return true;
                }
                return false;
            });
            popup.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return vmLugar.listarLugares().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre, tvCiudad,tvTiempo,tvValor;
        ImageView ivLugar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre=itemView.findViewById(R.id.tv_nombre);
            tvCiudad=itemView.findViewById(R.id.tv_ciudad);
            tvTiempo=itemView.findViewById(R.id.tv_tiempo);
            tvValor=itemView.findViewById(R.id.tv_valor);
            ivLugar=itemView.findViewById(R.id.iv_lugar);
        }
    }
}
