package com.bryanahusna.golek.kuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bryanahusna.golek.R;

public class PembukaIsiKataKuisActivity extends AppCompatActivity {
    private TextView judulLevel;
    private TextView keteranganLevel;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembuka_isi_kata_kuis);
        judulLevel = findViewById(R.id.judul_level_pembuka_isiKata);
        keteranganLevel = findViewById(R.id.keterangan_pembuka_isiKata);
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        switch(level){
            case 1:
                judulLevel.setText("Level 1");
                keteranganLevel.setText("Gunakan Ngoko Lugu pada Level ini");
                break;
            case 2:
                judulLevel.setText("Level 2");
                keteranganLevel.setText("Gunakan Ngoko Alus pada Level ini");
                break;
            case 3:
                judulLevel.setText("Level 3");
                keteranganLevel.setText("Gunakan Krama Lugu pada Level ini");
                break;
            case 4:
                judulLevel.setText("Level 4");
                keteranganLevel.setText("Gunakan Krama Alus pada level ini");
                break;
        }
    }
    public void mulaiKuisIsiKataPembuka(View view){
        Intent intent2 = new Intent(this, IsiKataKuisActivity.class);
        intent2.putExtra("level", level);
        startActivity(intent2);
        finish();
    }
}
