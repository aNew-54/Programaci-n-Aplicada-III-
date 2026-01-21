package sistemas.unc.edu.pe.appsaludpersonas;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Modelo.Persona;
import VistaModelo.VMPersona;

public class MainActivity extends AppCompatActivity implements MyBottomSheet.OnPersonaRegistrada {

    private RecyclerView rvPersonas;
    private PersonaAdapter adaptador;
    private ArrayList<Persona> listaPersonas;
    private VMPersona oVMpersona;

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

        oVMpersona = new VMPersona();
        listaPersonas = new ArrayList<>();

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitle("Registro de Pacientes");
        }
        rvPersonas = findViewById(R.id.rv_personas);
        rvPersonas.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent().getExtras() != null && getIntent().hasExtra("listaPersonas")) {
            ArrayList<Persona> inicial = (ArrayList<Persona>) getIntent().getSerializableExtra("listaPersonas");
            if (inicial != null) listaPersonas.addAll(inicial);
        }

        adaptador = new PersonaAdapter(listaPersonas);
        rvPersonas.setAdapter(adaptador);

        FloatingActionButton btnShow = findViewById(R.id.btnAñadirPaciente);
        btnShow.setOnClickListener(v -> {
            MyBottomSheet bottomSheet = new MyBottomSheet();
            bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
        });
    }

    @Override
    public void onPersonaGuardada(Persona persona) {
        listaPersonas.add(persona);
        adaptador.notifyItemInserted(listaPersonas.size() - 1);
        rvPersonas.scrollToPosition(listaPersonas.size() - 1);
        Toast.makeText(this, persona.mensajeRegistro(), Toast.LENGTH_SHORT).show();
    }
}