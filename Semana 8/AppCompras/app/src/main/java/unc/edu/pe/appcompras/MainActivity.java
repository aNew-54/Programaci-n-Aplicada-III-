package unc.edu.pe.appcompras;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import unc.edu.pe.appcompras.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<Electrodomestico> listaElec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaElec = new ArrayList<>();
        listaElec.add(new Electrodomestico("Cocina", 700, R.drawable.cocina));
        listaElec.add(new Electrodomestico("Lavadora", 2500, R.drawable.lavadora));
        listaElec.add(new Electrodomestico("Licuadora", 400, R.drawable.licuadora));
        listaElec.add(new Electrodomestico("Microondas", 1000, R.drawable.microondas));
        listaElec.add(new Electrodomestico("Rapiducha", 500, R.drawable.rapiducha));
        listaElec.add(new Electrodomestico("Refrigeradora", 3000, R.drawable.refrigeradora));

        ElectroAdapter adapter = new ElectroAdapter(listaElec);

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}