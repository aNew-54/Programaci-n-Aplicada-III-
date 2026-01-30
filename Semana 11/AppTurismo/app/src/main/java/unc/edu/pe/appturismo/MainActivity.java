package unc.edu.pe.appturismo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Model.Lugar;
import ViewModel.VMLugar;
import unc.edu.pe.appturismo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static VMLugar vmLugar=new VMLugar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //incorporando toolbar en la actividad
        binding.toolbar.setTitle(R.string.app_name);
        setSupportActionBar(binding.toolbar);
        //

        RecyclerView rvLugares=findViewById(R.id.rv_lugares);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvLugares.setLayoutManager(linearLayoutManager);

        //Para agregar una linea divisoria entre los items del RecyclerView
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(binding.rvLugares.getContext(),
                linearLayoutManager.getOrientation());
        binding.rvLugares.addItemDecoration(mDividerItemDecoration);
        //

        //vmLugar.listarLugares().add(new Lugar("Plaza de Armas", "Cajamarca",getString(R.string.desc_plaza_armas),0.0, 0.0,4.5f,R.drawable.plaza_armas,R.drawable.plaza_armas2));
        //vmLugar.listarLugares().add(new Lugar("Alameda de los Incas", "Cajamarca",getString(R.string.desc_alameda_incas),3.8,13.0,4f,R.drawable.alameda_incas,R.drawable.alameda_incas2));
        //vmLugar.listarLugares().add(new Lugar("Complejo de Baños del Inca", "Baños del Inca",getString(R.string.desc_complejo_banos),6.0,20.0,4.5f,R.drawable.complejo_banos,R.drawable.complejo_banos2));
        //vmLugar.listarLugares().add(new Lugar("Cumbemayo", "Cajamarca",getString(R.string.desc_cumbemayo),20.0,60.0,4f,R.drawable.cumbemayo,R.drawable.cumbemayo2));

        LugarAdapter lugarAdapter=new LugarAdapter(this,vmLugar);
        rvLugares.setAdapter(lugarAdapter);

        //implementando la interfaz
       lugarAdapter.setOnLugarClickListener(new LugarAdapter.OnLugarClickListener() {
           @Override
           public void onLugarClick(Lugar lugar) {
               Intent intent=new Intent(MainActivity.this, GaleriaActivity.class);
               intent.putExtra("v_lugar",lugar);
               startActivity(intent);
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        int itemId = item.getItemId();
        if (itemId == R.id.im_lugar) {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.im_agregar) {
            Intent intent=new Intent(this,AgregarActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId==R.id.im_salir){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // para actualizar el recyclerView
    @Override
    protected void onResume() {
        super.onResume();
        LugarAdapter lugarAdapter=new LugarAdapter(this,vmLugar);
        binding.rvLugares.setAdapter(lugarAdapter);
    }
}