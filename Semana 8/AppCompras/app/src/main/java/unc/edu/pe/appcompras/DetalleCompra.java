package unc.edu.pe.appcompras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import unc.edu.pe.appcompras.databinding.ActivityDetalleCompraBinding;

public class DetalleCompra extends AppCompatActivity {

    ActivityDetalleCompraBinding binding;
    boolean Obsequio = false;

    Obsequio obsequioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityDetalleCompraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Electrodomestico producto = getIntent().getParcelableExtra("producto");

        if(producto != null) {
            binding.imgProducto.setImageResource(producto.imagen);
            binding.tvNombreElec.setText(producto.nombre);
            binding.tvPrecioElec.setText("S/. " + String.valueOf(producto.precio));
        }

        binding.bAtras.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            v.getContext().startActivity(intent);
        });

        Obsequio[] obsequios = new Obsequio[]{
                new Obsequio("Set de tazas x 6 und.", R.drawable.tazas),
                new Obsequio("Individuales x 12 und.", R.drawable.individuales),
                new Obsequio("Bowls x 6 und.", R.drawable.bowls),
                new Obsequio("Set de vasos x 6 und.", R.drawable.vasos),
                new Obsequio("Cucharitas de té x 12 und.", R.drawable.cucharitas)
        };

        binding.bObsequio.setOnClickListener(v -> {
            if(!Obsequio) {
                Random r = new Random();
                int pos = r.nextInt(obsequios.length);

                obsequioSeleccionado = new Obsequio(obsequios[pos].nombre, obsequios[pos].imagen);

                binding.imgObsequio.setImageResource(obsequioSeleccionado.imagen);
                binding.tvObsequio.setText(obsequioSeleccionado.nombre);

                Obsequio = true;
            }
        });

        binding.bComprar.setOnClickListener(v -> {

            if(binding.etCantidad.getText().toString().isEmpty()){
                Toast.makeText(this, "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!Obsequio){
                Toast.makeText(this, "Obtén tu obsequio", Toast.LENGTH_SHORT).show();
                return;
            }

            int cantidad = Integer.parseInt(binding.etCantidad.getText().toString());

            if(cantidad <= 0) {
                Toast.makeText(this, "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
                return;
            }

            if(binding.rbDescuento.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Seleccione un descuento", Toast.LENGTH_SHORT).show();
                return;
            }

            double montoBruto = producto.precio * cantidad;

            double montoAdicionales = 0;
            ArrayList<String> listAdicionales = new ArrayList<>();
            boolean aux = false;

            if(binding.cbDomicilio.isChecked()){
                double descuento = Math.round(montoBruto * 0.05 * 100) / 100d;
                montoAdicionales += descuento;
                listAdicionales.add("Instalación a Domicilio");
                aux = true;
            }
            if(binding.cbMantenimiento.isChecked()){
                double descuento = Math.round(montoBruto * 0.10 * 100) / 100d;
                montoAdicionales += descuento;
                listAdicionales.add("Mantenimiento trimestral");
                aux = true;
            }
            if(binding.cbSeguro.isChecked()){
                double descuento = Math.round(montoBruto * 0.07 * 100) / 100d;
                montoAdicionales += descuento;
                listAdicionales.add("Seguro por fallas técnicas");
                aux = true;
            }
            if(!aux)
                listAdicionales.add("Ninguno");

            int idSeleccionado = binding.rbDescuento.getCheckedRadioButtonId();
            RadioButton rbSeleccionado = findViewById(idSeleccionado);

            String tipoDescuento = rbSeleccionado.getText().toString();
            double montoDescuento = Math.round(montoBruto * Double.parseDouble(rbSeleccionado.getTag().toString()) * 100) / 100d;

            double totalPagar = montoBruto + montoAdicionales - montoDescuento;

            Intent intent = new Intent(v.getContext(), Compra.class);
            intent.putExtra("producto", producto);
            intent.putExtra("cantidad", cantidad);
            intent.putExtra("subtotal", montoBruto);
            intent.putExtra("listaAdicionales", listAdicionales);
            intent.putExtra("montoAdicionales", montoAdicionales);
            intent.putExtra("tipoDescuento", tipoDescuento);
            intent.putExtra("montoDescuento", montoDescuento);
            intent.putExtra("totalPagar", totalPagar);
            intent.putExtra("obsequio", obsequioSeleccionado);

            startActivity(intent);
        });
    }
}
