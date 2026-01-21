package unc.edu.pe.catgalleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class ActivityDetail extends AppCompatActivity {

    ImageView ivCatDetail;
    TextView tvNameDetail, tvOriginDetail, tvTemperamentDetail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivCatDetail = findViewById(R.id.ivCatDetail);
        tvNameDetail = findViewById(R.id.tvNameDetail);
        tvOriginDetail = findViewById(R.id.tvOriginDetail);
        tvTemperamentDetail = findViewById(R.id.tvTemperamentDetail);

        String imageUrl = getIntent().getStringExtra("imageUrl");
        String name = getIntent().getStringExtra("name");
        String origin = getIntent().getStringExtra("origin");
        String temperament = getIntent().getStringExtra("temperament");

        Glide.with(this).load(imageUrl).into(ivCatDetail);
        tvNameDetail.setText(name.toUpperCase());
        tvOriginDetail.setText(origin);
        tvTemperamentDetail.setText(temperament);
    }
}
