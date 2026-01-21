package unc.edu.pe.camaraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import unc.edu.pe.camaraapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Bitmap imagenProducto;

    private ActivityResultLauncher<Intent> cameraLauncher; //ActivityResult API

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

        binding.bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.ptNombreProducto.getText().toString();
                double precio = Double.parseDouble(binding.ndPrecioProducto.getText().toString());
                double calificacion = binding.rbCalificacion.getRating();
                openDetalleProducto(nombre, precio, calificacion);
            }
        });
        // Registrar el launcher ANTES de usarlo - Activity Result API
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getExtras() != null) {
                            imagenProducto = (Bitmap) data.getExtras().get("data");
                            binding.ivImagen.setImageBitmap(imagenProducto);
                        }
                    } else {
                        Toast.makeText(this, "Cámara cancelada", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        binding.ivImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
    }

    private void openCamera() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //implicit intent
        cameraLauncher.launch(camaraIntent);

    }

    private void openDetalleProducto(String nombre, double precio, double calificacion) {
        Intent intent = new Intent(this, DetalleProducto.class);
        intent.putExtra("NombreProducto", nombre);
        intent.putExtra("PrecioProducto", precio);
        intent.putExtra("Calificacion", calificacion);
        intent.putExtra("imagen", imagenProducto);
        startActivity(intent);
    }
}