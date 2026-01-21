package sistemas.unc.edu.pe.appsaludpersonas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import Modelo.Persona;

public class ActivityListar extends AppCompatActivity {

    ListView lvPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvPersonas=findViewById(R.id.lv_personas);

        Bundle personas=getIntent().getExtras();
        ArrayList<Persona> lista=(ArrayList<Persona>) personas.getSerializable("listaPersonas");
        lvPersonas.setAdapter(new ArrayAdapter<Persona>(this,android.R.layout.simple_list_item_1,lista));

    }
}