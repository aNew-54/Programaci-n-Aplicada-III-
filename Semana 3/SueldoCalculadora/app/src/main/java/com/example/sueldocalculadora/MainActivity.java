package com.example.sueldocalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

        //Llenamos los Spinner
        Spinner spinnerTrabajador = findViewById(R.id.spinnerTrabajador);
        Spinner spinnerSeguro = findViewById(R.id.spinnerSeguro);
        EditText etDias = findViewById(R.id.etDias);
        TextView tvSueldoNeto = findViewById(R.id.tvResultado);
        Button bcalcular = findViewById(R.id.btnCalcular);

        String[] tipoTrabajadores = {"Secretario", "Obrero", "Gerente"};
        ArrayAdapter<String> adaptadorTrabajador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                tipoTrabajadores
        );
        adaptadorTrabajador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerTrabajador.setAdapter(adaptadorTrabajador);

        String[] tipoSeguros = {"ONP", "AFP"};
        ArrayAdapter<String> adaptadorSeguros = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                tipoSeguros
        );
        adaptadorSeguros.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSeguro.setAdapter(adaptadorSeguros);

        bcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoChambeador = spinnerTrabajador.getSelectedItem().toString();
                String seguro = spinnerSeguro.getSelectedItem().toString();

                if(etDias.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Ingrese un numero de días valido", Toast.LENGTH_SHORT).show();
                    return;
                }

                int diasTrabajados = Integer.parseInt(etDias.getText().toString());

                double sueldoBruto = 0;
                switch(tipoChambeador) {
                    case "Secretario":
                        sueldoBruto = 80 * diasTrabajados;
                        break;
                    case "Obrero":
                        sueldoBruto = 60 * diasTrabajados;
                        break;
                    case "Gerente":
                        sueldoBruto = 200 * diasTrabajados;
                        break;
                }
                double sueldoNeto=0;
                switch (seguro){
                    case "AFP":
                        sueldoNeto = sueldoBruto * 0.9;
                        break;
                    case "ONP":
                        sueldoNeto = sueldoBruto * 0.87;
                        break;
                }
                tvSueldoNeto.setText(String.valueOf(sueldoNeto));
            }
        });
    }
}