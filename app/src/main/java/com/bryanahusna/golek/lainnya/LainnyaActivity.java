package com.bryanahusna.golek.lainnya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bryanahusna.golek.R;
import com.bryanahusna.golek.belajar.BelajarActivity;
import com.bryanahusna.golek.cari.CariActivity;
import com.bryanahusna.golek.cari.DaftarKataFavoritActivity;
import com.bryanahusna.golek.kuis.KuisActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class LainnyaActivity extends AppCompatActivity {
    private static final int WAKTU_KELUAR_INTERVAL = 2000;
    private long mTombolKembali;

    private BottomNavigationViewEx bottomNavigationViewEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lainnya);

        setupBottomNavigationView();
    }

    private void setupBottomNavigationView(){
        bottomNavigationViewEx = findViewById(R.id.bottom_bar);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch(item.getItemId()){
                    case R.id.navigation_belajar:
                        startActivity(new Intent(getApplicationContext(), BelajarActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navigation_cari:
                        startActivity(new Intent(getApplicationContext(), CariActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navigation_kuis:
                        startActivity(new Intent(getApplicationContext(), KuisActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navigation_lainnya:
                        break;
                }
                return true;
            }
        });
        bottomNavigationViewEx.setSelectedItemId(R.id.navigation_lainnya);
    }

    @Override
    public void onBackPressed() {
        if(mTombolKembali + WAKTU_KELUAR_INTERVAL > System.currentTimeMillis()){
            ActivityCompat.finishAffinity(this);
            return;
        } else {
            Toast.makeText(getApplicationContext(), "Tekan tombol kembali dua kali untuk keluar", Toast.LENGTH_SHORT).show();
        }
        mTombolKembali = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationViewEx.setSelectedItemId(R.id.navigation_lainnya);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(0,0);
    }

    public void luncurkanAktivitasLainnya(View view) {
        switch(view.getId()){
            case R.id.lainnya_buka_favorit:
                startActivity(new Intent(getApplicationContext(), DaftarKataFavoritActivity.class));
                break;
            case R.id.lainnya_buka_tentang:
                startActivity(new Intent(this, TentangActivity.class));
                break;
        }

    }
}
