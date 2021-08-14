package com.bryanahusna.golek.kuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bryanahusna.golek.R;

public class PilihLevelTebakGambarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_level_tebak_gambar);
    }

    public void mulaiKuisIsiKata(View view) {
        Intent intent = new Intent(this, PembukaTebakGambarKuisActivity.class);
        switch(view.getId()){
            case R.id.level1_pilih_isiKata:
                intent.putExtra("level", 1);
                startActivity(intent);
                break;
            case R.id.level2_pilih_isiKata:
                intent.putExtra("level", 2);
                startActivity(intent);
                break;
            case R.id.level3_pilih_isiKata:
                intent.putExtra("level", 3);
                startActivity(intent);
                break;
        }
    }
}
