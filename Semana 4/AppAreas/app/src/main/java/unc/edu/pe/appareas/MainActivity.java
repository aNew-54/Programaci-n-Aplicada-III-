package unc.edu.pe.appareas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import unc.edu.pe.appareas.Modelo.Circulo;
import unc.edu.pe.appareas.Modelo.Cuadrado;
import unc.edu.pe.appareas.Modelo.FiguraG;
import unc.edu.pe.appareas.Modelo.Rectangulo;
import unc.edu.pe.appareas.Modelo.Triangulo;

public class MainActivity extends AppCompatActivity {

    Switch sw3DMode;
    Spinner spFiguras;
    EditText etRadio, etAncho, etLargo, etProfundidad, etSide;
    LinearLayout llTwoParams;
    Button bCalcular;
    TextView tvTitulo, tvResultado;
    ImageView ivImagenFigura;

    @SuppressLint({"SetTextI18n"})
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

        sw3DMode = findViewById(R.id.sw_3d_mode);
        spFiguras = findViewById(R.id.sp_figures);
        etRadio = findViewById(R.id.et_radius);
        etSide = findViewById(R.id.et_side);
        etAncho = findViewById(R.id.et_param1);
        etLargo = findViewById(R.id.et_param2);
        etProfundidad = findViewById(R.id.et_depth);
        llTwoParams = findViewById(R.id.ll_two_params);
        bCalcular = findViewById(R.id.b_calculate);
        tvTitulo = findViewById(R.id.tv_result_label);
        tvResultado = findViewById(R.id.tv_result_value);
        ivImagenFigura = findViewById(R.id.iv_figure);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.geometric_shapes,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFiguras.setAdapter(adapter);

        spFiguras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String figuraSeleccionada = parent.getItemAtPosition(position).toString();

                //Ocultamos los elementos que no son necesarios, dependiendo de la fiura seleccionada
                etSide.setVisibility(View.GONE);
                llTwoParams.setVisibility(View.GONE);
                etProfundidad.setVisibility(View.GONE);
                etRadio.setVisibility(View.GONE);

                bCalcular.setEnabled(false);

                ActualizarImagenes();

                switch (figuraSeleccionada) {
                    case "Cuadrado":
                        etSide.setHint("Lado");
                        etSide.setVisibility(View.VISIBLE);
                        bCalcular.setEnabled(true);
                        break;

                    case "Rectángulo":
                        etAncho.setHint("Ancho");
                        etLargo.setHint("Largo");
                        llTwoParams.setVisibility(View.VISIBLE);
                        if (sw3DMode.isChecked()) {
                            etProfundidad.setHint("Profundidad");
                            etProfundidad.setVisibility(View.VISIBLE);
                        }
                        bCalcular.setEnabled(true);
                        break;

                    case "Triángulo":
                        etAncho.setHint("Base");
                        etLargo.setHint("Altura(base)");
                        llTwoParams.setVisibility(View.VISIBLE);
                        if (sw3DMode.isChecked()) {
                            etProfundidad.setHint("Altura");
                            etProfundidad.setVisibility(View.VISIBLE);
                        }
                        bCalcular.setEnabled(true);
                        break;

                    case "Círculo":
                        etRadio.setHint("Radio");
                        etRadio.setVisibility(View.VISIBLE);
                        if (sw3DMode.isChecked()) {
                            etProfundidad.setHint("Altura");
                            etProfundidad.setVisibility(View.VISIBLE);
                        }
                        bCalcular.setEnabled(true);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bCalcular.setEnabled(false);
                etSide.setVisibility(View.GONE);
                llTwoParams.setVisibility(View.GONE);
                etProfundidad.setVisibility(View.GONE);
                etRadio.setVisibility(View.GONE);
            }
        });

        sw3DMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    tvTitulo.setText("Volumen:");
                else
                    tvTitulo.setText("Área:");

                tvResultado.setText("");

                String figuraSeleccionada = spFiguras.getSelectedItem().toString();
                ActualizarImagenes();
                etProfundidad.setVisibility(View.GONE);

                if (isChecked) {
                    if (figuraSeleccionada.equals("Rectángulo") || figuraSeleccionada.equals("Triángulo")) {
                        etProfundidad.setHint("Profundidad");
                        etProfundidad.setVisibility(View.VISIBLE);
                    } else if (figuraSeleccionada.equals("Círculo")) {
                        etProfundidad.setHint("Altura");
                        etProfundidad.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        bCalcular.setOnClickListener(v -> {
            FiguraG oFigura = null;

            String figuraSeleccionada = spFiguras.getSelectedItem().toString();
            boolean is3D = sw3DMode.isChecked();

            String ancho = "";
            String alto = etLargo.getText().toString();
            String profundidad = etProfundidad.getText().toString();

            double anchoDouble = 0, altoDouble = 0, profundidadDouble = 0;

            if (figuraSeleccionada.equals("Cuadrado")) {
                ancho = etSide.getText().toString();
                if (ancho.isEmpty()) {
                    Toast.makeText(this, getString(R.string.datos_faltantes), Toast.LENGTH_SHORT).show();
                    return;
                }
                anchoDouble = Double.parseDouble(ancho);

            } else if (figuraSeleccionada.equals("Círculo")) {
                ancho = etRadio.getText().toString();
                if (is3D) {
                    if (ancho.isEmpty() || profundidad.isEmpty()) {
                        Toast.makeText(this, getString(R.string.datos_faltantes), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    anchoDouble = Double.parseDouble(ancho);
                    profundidadDouble = Double.parseDouble(profundidad);
                } else {
                    if (ancho.isEmpty()) {
                        Toast.makeText(this, getString(R.string.datos_faltantes), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    anchoDouble = Double.parseDouble(ancho);
                }

            } else if (figuraSeleccionada.equals("Triángulo") || figuraSeleccionada.equals("Rectángulo")) {
                ancho = etAncho.getText().toString();

                if (is3D) {
                    if (ancho.isEmpty() || alto.isEmpty() || profundidad.isEmpty()) {
                        Toast.makeText(this, getString(R.string.datos_faltantes), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    anchoDouble = Double.parseDouble(ancho);
                    altoDouble = Double.parseDouble(alto);
                    profundidadDouble = Double.parseDouble(profundidad);
                } else {
                    if (ancho.isEmpty() || alto.isEmpty()) {
                        Toast.makeText(this, getString(R.string.datos_faltantes), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    anchoDouble = Double.parseDouble(ancho);
                    altoDouble = Double.parseDouble(alto);
                }
            }

            double resultado = 0;

            switch (figuraSeleccionada) {
                case "Cuadrado":
                    oFigura = new Cuadrado(anchoDouble);
                    if (is3D)
                        resultado = ((Cuadrado) oFigura).CalcularVolumen();
                    else
                        resultado = ((Cuadrado) oFigura).CalcularArea();
                    break;
                case "Rectángulo":
                    if (is3D) {
                        oFigura = new Rectangulo(anchoDouble, altoDouble, profundidadDouble);
                        resultado = ((Rectangulo) oFigura).CalcularVolumen();
                    } else {
                        oFigura = new Rectangulo(anchoDouble, altoDouble);
                        resultado = ((Rectangulo) oFigura).CalcularArea();
                    }
                    break;
                case "Triángulo":
                    if (is3D) {
                        oFigura = new Triangulo(anchoDouble, altoDouble, profundidadDouble);
                        resultado = ((Triangulo) oFigura).CalcularVolumen();
                    } else {
                        oFigura = new Triangulo(anchoDouble, altoDouble);
                        resultado = ((Triangulo) oFigura).CalcularArea();
                    }
                    break;
                case "Círculo":
                    if (is3D) {
                        oFigura = new Circulo(anchoDouble, profundidadDouble);
                        resultado = ((Circulo) oFigura).CalcularVolumen();
                    } else {
                        oFigura = new Circulo(anchoDouble);
                        resultado = ((Circulo) oFigura).CalcularArea();
                    }
                    break;
                default:
                    break;
            }

            tvResultado.setText(String.format("%.2f", resultado));
        });

    }

    private void ActualizarImagenes() {
        String figuraSeleccionada = spFiguras.getSelectedItem().toString();
        boolean is3D = sw3DMode.isChecked();

        switch (figuraSeleccionada) {
            case "Cuadrado":
                if (is3D)
                    ivImagenFigura.setImageResource(R.drawable.cubo);
                else
                    ivImagenFigura.setImageResource(R.drawable.cuadrado);
                break;
            case "Triángulo":
                if (is3D)
                    ivImagenFigura.setImageResource(R.drawable.prisma_triangular);
                else
                    ivImagenFigura.setImageResource(R.drawable.triangulo);
                break;
            case "Rectángulo":
                if (is3D)
                    ivImagenFigura.setImageResource(R.drawable.prisma_rectangular);
                else
                    ivImagenFigura.setImageResource(R.drawable.rectangulo);
                break;
            case "Círculo":
                if (is3D)
                    ivImagenFigura.setImageResource(R.drawable.cilindro);
                else
                    ivImagenFigura.setImageResource(R.drawable.circulo);
                break;
        }
    }
}