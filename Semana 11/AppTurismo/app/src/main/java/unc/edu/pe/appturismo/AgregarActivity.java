package unc.edu.pe.appturismo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Model.Lugar;
import unc.edu.pe.appturismo.databinding.ActivityAgregarBinding;
import unc.edu.pe.appturismo.databinding.ActivityMainBinding;

public class AgregarActivity extends AppCompatActivity {

    ActivityAgregarBinding binding;
    Uri imagen;
    String[] ciudad={"Cajamarca","Baños del Inca","Namora"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_agregar);
        binding= ActivityAgregarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //incorporando el toolbar a la activity y botón atrás
        binding.toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        binding.spCiudad.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ciudad));
        binding.ivLugarAgregar.setOnClickListener(v -> cargarImagen());
        binding.fabRegistrar.setOnClickListener(v -> agregarLugar());
    }

    //cargando la imagen desde el dispositivo.
    private void cargarImagen() {
        Intent oIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        oIntent.setType("image/*");
        startActivityIfNeeded(Intent.createChooser(oIntent,"Seleccione la aplicación"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK) {
            imagen = data.getData(); //obtenemos la imagen en formato Uri
            binding.ivLugarAgregar.setImageURI(imagen);
        }
    }
//
    private void agregarLugar(){
        String nombre=binding.etNombre.getText().toString();
        String ciudad=binding.spCiudad.getSelectedItem().toString();
        String descripcion=binding.etDescripcion.getText().toString();
        double distancia=Double.parseDouble(binding.etDistancia.getText().toString());
        double tiempo=Double.parseDouble(binding.etTiempo.getText().toString());
        float valor=binding.rbValor.getRating();
        Lugar lugar=new Lugar(nombre, ciudad, descripcion,distancia,tiempo,valor,imagen,null);
        MainActivity.vmLugar.AgregarLugar(lugar);
        Toast.makeText(this, "Se registró lugar", Toast.LENGTH_SHORT).show();
        mostrarCuadroDialogo();
    }

    private void mostrarCuadroDialogo() {
        AlertDialog.Builder oDialogo=new AlertDialog.Builder(this);
        oDialogo.setTitle("Registro");
        oDialogo.setMessage("¿Desea seguir registrando?");
        oDialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                AgregarActivity.this.finish();
            }
        });
        oDialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                limpiar();
            }
        });
        oDialogo.create();
        oDialogo.show();
    }
    private void limpiar() {
        binding.etNombre.setText("");
        binding.spCiudad.setSelection(0);
        binding.etDescripcion.setText("");
        binding.etDistancia.setText("");
        binding.etTiempo.setText("");
        binding.rbValor.setProgress(0);
        binding.ivLugarAgregar.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.cargar_imagen));
        binding.etNombre.requestFocus();
    }
}