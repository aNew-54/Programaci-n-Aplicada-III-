package com.example.calculadoraigv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        // Asociar los view con los objetos del código
        EditText etPrecio = findViewById(R.id.etPrecio);
        TextView tvIGV = findViewById(R.id.tv_igv);
        TextView tvPrecioTotal = findViewById(R.id.tv_precioTotal);
        Button bCalcular = findViewById(R.id.b_calcular);

        bCalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                String precio = etPrecio.getText().toString();
                if(!precio.isEmpty()){
                    double precioD = Double.parseDouble(precio);
                    double igv = precioD*0.18;
                    double precioTotal = precioD + igv;
                    tvIGV.setText(String.valueOf(igv));
                    tvPrecioTotal.setText(String.valueOf(precioTotal));
                }

                else {
                    Toast.makeText(MainActivity.this, "Ingrese Precio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}