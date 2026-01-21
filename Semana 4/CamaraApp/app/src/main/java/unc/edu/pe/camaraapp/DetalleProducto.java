package unc.edu.pe.camaraapp;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import unc.edu.pe.camaraapp.databinding.ActivityDetalleProductoBinding;
import unc.edu.pe.camaraapp.databinding.ActivityMainBinding;

public class DetalleProducto extends AppCompatActivity {
    ActivityDetalleProductoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleProductoBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("NombreProducto");
        double precio = extras.getDouble("PrecioProducto");
        double calificacion = extras.getDouble("Calificacion");
        Bitmap imgProducto = extras.getParcelable("imagen");

        binding.tvNombre.setText(nombre);
        binding.tvPrecio.setText(String.valueOf(precio));
        binding.rbDetCalificacion.setRating((float) calificacion);
        binding.ivDetalleProducto.setImageBitmap(imgProducto);
    }
}