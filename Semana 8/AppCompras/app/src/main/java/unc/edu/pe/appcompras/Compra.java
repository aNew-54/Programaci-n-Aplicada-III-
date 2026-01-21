package unc.edu.pe.appcompras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import unc.edu.pe.appcompras.databinding.ActivityCompraBinding;

public class Compra extends AppCompatActivity {

    ActivityCompraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityCompraBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Electrodomestico producto = getIntent().getParcelableExtra("producto");
        int cantidad = getIntent().getIntExtra("cantidad", 0);
        double montoBruto = getIntent().getDoubleExtra("subtotal", 0);
        ArrayList<String> listaAdicionales = getIntent().getStringArrayListExtra("listaAdicionales");
        double montoAdicionales = getIntent().getDoubleExtra("montoAdicionales", 0);
        String tipoDescuento = getIntent().getStringExtra("tipoDescuento");
        double montoDescuento = getIntent().getDoubleExtra("montoDescuento", 0);
        double totalPagar = getIntent().getDoubleExtra("totalPagar", 0);
        Obsequio obsequioSeleccionado = getIntent().getParcelableExtra("obsequio");

        if(producto != null) {
            binding.nameProducto.setText(producto.nombre);
            binding.precioProducto.setText(String.valueOf(producto.precio));
            binding.imgProductoConfirmado.setImageResource(producto.imagen);
        }
        binding.cantidadProducto.setText(String.valueOf(cantidad));
        binding.productoSubTotal.setText(String.valueOf(montoBruto));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAdicionales);
        binding.lvAdicionales.setAdapter(adapter);
        binding.montoAdicionales.setText(String.valueOf(montoAdicionales));
        binding.tipoDescuento.setText(tipoDescuento);
        binding.montoDescuento.setText(String.valueOf(montoDescuento));

        if(obsequioSeleccionado != null) {
            binding.nameObsequio.setText(obsequioSeleccionado.nombre);
            binding.imgObsequioConfirmado.setImageResource(obsequioSeleccionado.imagen);
        }

        binding.totalPagar.setText(String.valueOf(totalPagar));

        binding.bVolverComprar.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            v.getContext().startActivity(intent);
        });
    }
}