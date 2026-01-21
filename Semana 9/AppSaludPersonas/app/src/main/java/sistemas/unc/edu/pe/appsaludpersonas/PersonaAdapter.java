package sistemas.unc.edu.pe.appsaludpersonas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Modelo.Persona;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> {
    private ArrayList<Persona> lista;

    public PersonaAdapter(ArrayList<Persona> lista) { this.lista = lista; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Persona p = lista.get(position);
        holder.txtNombre.setText(p.nombres + " " + p.apellidos);
        holder.txtDniEdad.setText("DNI: " + p.dni + " | Edad: " + p.edad);

        int imcResult = p.calcularIMC();
        if (imcResult == -1) {
            holder.chipImc.setText("Bajo Peso");
            holder.chipImc.setChipIconResource(R.drawable.ic_warning);
        } else if (imcResult == 0) {
            holder.chipImc.setText("Peso Ideal");
            holder.chipImc.setChipIconResource(R.drawable.ic_check);
            holder.chipImc.setChipBackgroundColorResource(R.color.primary_container_light);
            holder.chipImc.setTextColor(holder.itemView.getContext().getColor(R.color.on_primary_container_light));
        } else {
            holder.chipImc.setText("Sobrepeso");
            holder.chipImc.setChipIconResource(R.drawable.ic_alert);
            holder.chipImc.setChipBackgroundColorResource(R.color.tertiary_container_light);
        }
    }

    @Override
    public int getItemCount() { return lista.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtDniEdad;
        com.google.android.material.chip.Chip chipImc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txt_nombre_completo);
            txtDniEdad = itemView.findViewById(R.id.txt_dni_edad);
            chipImc = itemView.findViewById(R.id.chip_imc);
        }
    }
}