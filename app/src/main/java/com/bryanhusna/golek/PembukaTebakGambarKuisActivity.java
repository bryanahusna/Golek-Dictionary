package com.bryanhusna.golek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PembukaTebakGambarKuisActivity extends AppCompatActivity {
    private TextView judulLevel;
    private TextView keteranganLevel;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembuka_tebak_gambar_kuis);
        judulLevel = findViewById(R.id.judul_level_pembuka_isiKata);
        keteranganLevel = findViewById(R.id.keterangan_pembuka_isiKata);
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        switch(level){
            case 1:
                judulLevel.setText("Level 1");
                keteranganLevel.setText("Tebak Kata Ngoko dari Gambar pada Level Ini");
                break;
            case 2:
                judulLevel.setText("Level 2");
                keteranganLevel.setText("Tebak Kata Krama dari Gambar pada Level Ini");
                break;
            case 3:
                judulLevel.setText("Level 3");
                keteranganLevel.setText("Tebak Kata Krama Inggil dari Gambar pada Level ini");
                break;
        }
    }
    public void mulaiKuisIsiKataPembuka(View view){
        Intent intent2 = new Intent(this, TebakGambarKuisActivity.class);
        intent2.putExtra("level", level);
        startActivity(intent2);
        finish();
    }
}
