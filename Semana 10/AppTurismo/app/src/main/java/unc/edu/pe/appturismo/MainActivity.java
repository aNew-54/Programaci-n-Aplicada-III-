package unc.edu.pe.appturismo;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.Lugar;
import ViewModel.VMLugar;

public class MainActivity extends AppCompatActivity {

    public static VMLugar vmLugar = new VMLugar();
    public LugarAdapter lugarAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvLugares = findViewById(R.id.rvLugares);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));

        vmLugar.listarLugares().add(new Lugar("Plaza de Armas", "Cajamarca",getString(R.string.desc_plaza_armas),0.0, 0.0,4.5f,R.drawable.plaza_armas));
        vmLugar.listarLugares().add(new Lugar("Alameda de los Incas", "Cajamarca",getString(R.string.desc_alameda_incas),3.8,13.0,4f,R.drawable.alameda_incas));
        vmLugar.listarLugares().add(new Lugar("Complejo de Baños del Inca", "Baños del Inca",getString(R.string.desc_complejo_banos),6.0,20.0,4.5f,R.drawable.complejo_banos));
        vmLugar.listarLugares().add(new Lugar("Cumbemayo", "Cajamarca",getString(R.string.desc_cumbemayo),20.0,60.0,4f,R.drawable.cumbemayo));

        lugarAdapter = new LugarAdapter( this, vmLugar);
        rvLugares.setAdapter(lugarAdapter);

    }


}