package unc.edu.pe.appturismo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Model.Lugar;
import unc.edu.pe.appturismo.databinding.ActivityGaleriaBinding;

public class GaleriaActivity extends AppCompatActivity {

    ActivityGaleriaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding= ActivityGaleriaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle datosLugar = getIntent().getExtras();
        Lugar lugar = datosLugar.getParcelable("v_lugar");
        binding.ivImagenLugar1.setImageResource(lugar.getImageId());
        binding.ivImagenLugar2.setImageResource(lugar.getImageId2());
        binding.tvNombreLugar1.setText(lugar.getNombre());
        binding.tvNombreLugar2.setText(lugar.getNombre());
    }
}