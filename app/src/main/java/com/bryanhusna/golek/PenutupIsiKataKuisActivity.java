package com.bryanhusna.golek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PenutupIsiKataKuisActivity extends AppCompatActivity {
    private int skor;
    private TextView selamat;
    private TextView nilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penutup_isi_kata_kuis);
        selamat = findViewById(R.id.selamat_isiKata);
        nilai = findViewById(R.id.skor_isiKata);
        Intent intent = getIntent();
        skor = (intent.getIntExtra("skorIsiKata", 0)*20);
        if(skor > 60){
            selamat.setText("Selamat");
            nilai.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            nilai.setText(Integer.toString(skor));
        } else {
            selamat.setText("Belajar Lagi");
            nilai.setTextColor(ContextCompat.getColor(this, R.color.secondaryDarkColor));
            nilai.setText(Integer.toString(skor));
        }
    }

    public void selesaiIsiKataPenutup(View view){
        finish();
    }
}
