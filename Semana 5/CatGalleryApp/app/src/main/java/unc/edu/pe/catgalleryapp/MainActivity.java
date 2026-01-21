package unc.edu.pe.catgalleryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unc.edu.pe.catgalleryapp.Models.Cat;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerCats;
    private CatAdapter catAdapter;
    private List<Cat> catList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Encuentra el RecyclerView
        recyclerCats = findViewById(R.id.recyclerCats);
        //Define la forma del layout
        recyclerCats.setLayoutManager(new GridLayoutManager(this, 3));
        // Crea el adaptador, con una lista vacia
        catAdapter = new CatAdapter(this, catList);
        // Conecta el RecyclerView con el Adaptador
        recyclerCats.setAdapter(catAdapter);
        // Carga los datos
        loadCats();
    }

    private void loadCats() {

        //Se crea una instancia de OkHttpClient
        OkHttpClient client = new OkHttpClient();

        //Se construye la petición
        String url = "https://api.thecatapi.com/v1/images/search?limit=100&has_breeds=1";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-api-key", "live_bKCtaAh6L2TK3JFID2xscZyLfI2nlPZcCn4uiDGCflmD2JIiPimpfGHkRUl3MCLr")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) { // Verificación de si el pedido fue exitoso
                    String jsonData = response.body().string(); // Obtener el texto JSON

                    try {
                        //Convertir el texto a una lista de objetos JSON
                        JSONArray jsonArray = new JSONArray(jsonData);
                        //Lista temporal de gatos
                        List<Cat> tempList = new ArrayList<>();

                        //Recorrer cada objeto de la lista
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i); // Un gato
                            // Extrae los datos de los gatos
                            String imageUrl = obj.getString("url");
                            JSONArray breedsArray = obj.optJSONArray("breeds");
                            String name = "Desconocido";
                            String origin = "N/A";
                            String temperament = "N/A";

                            if (breedsArray != null && breedsArray.length() > 0) {
                                JSONObject breed = breedsArray.getJSONObject(0);
                                name = breed.optString("name", "Desconocido");
                                origin = breed.optString("origin", "N/A");
                                temperament = breed.optString("temperament", "N/A");
                            }

                            //Añade un gato creado con los datos obtenidos
                            tempList.add(new Cat(imageUrl, name, origin, temperament));
                        }

                        // Envia la lista final al hilo principal para actualizar la vista
                        runOnUiThread(() -> catAdapter.setCatList(tempList));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("API_ERROR", "Error code: " + response.code());
                }
            }
        });
    }
}
