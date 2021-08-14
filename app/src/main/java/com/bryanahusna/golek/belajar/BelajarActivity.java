package com.bryanahusna.golek.belajar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.bryanahusna.golek.cari.CariActivity;
import com.bryanahusna.golek.cari.DaftarKataSQLiteHelper;
import com.bryanahusna.golek.kuis.KuisActivity;
import com.bryanahusna.golek.lainnya.LainnyaActivity;
import com.bryanahusna.golek.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.viewpagerindicator.CirclePageIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class BelajarActivity extends AppCompatActivity {
    private static final int WAKTU_KELUAR_INTERVAL = 2000;
    private long mTombolKembali;
    private String[] daftarKartuHarian = {"1", "2", "3", "4", "5"};
    private String[] daftarKartuHarianIndonesia = {"1", "2", "3", "4", "5"};
    private String[] daftarKartuHarianNgoko = {"1", "2", "3", "4", "5"};
    private String[] daftarKartuHarianKrama = {"1", "2", "3", "4", "5"};

    private BottomNavigationViewEx bottomNavigationViewEx;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajar);
        Calendar calendar = Calendar.getInstance();
        int tanggal = calendar.get(Calendar.DATE);

        SQLiteDatabase database = new DaftarKataSQLiteHelper(getApplicationContext()).getReadableDatabase();
        String daftarKolom[] = {"_ID", DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL, DaftarKataSQLiteHelper.COLUMN_INDONESIA, DaftarKataSQLiteHelper.COLUMN_NGOKO, DaftarKataSQLiteHelper.COLUMN_KRAMA};
        Cursor mCursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME, daftarKolom, null, null, null, null, null, null);
        Random rand = new Random();

        SharedPreferences sharedPrefs = getSharedPreferences("tanggal_kartu", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        if(!sharedPrefs.contains("initialized")){
            ed = sharedPrefs.edit();
            ed.putBoolean("initialized", true);
            ed.putInt("tanggal", 100);
            ed.commit();
        }

        if(tanggal != sharedPrefs.getInt("tanggal", 0)){
            ed = sharedPrefs.edit();
            ed.putInt("tanggal", tanggal);
            for(int i = 0; i<5; i++){
                mCursor.moveToPosition(rand.nextInt(132));
                daftarKartuHarian[i] = mCursor.getString(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL));
                daftarKartuHarianIndonesia[i] = mCursor.getString(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_INDONESIA));
                daftarKartuHarianNgoko[i] = mCursor.getString(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_NGOKO));
                daftarKartuHarianKrama[i] = mCursor.getString(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_KRAMA));
            }
            StringBuilder sb = new StringBuilder();
            StringBuilder sbInd = new StringBuilder();
            StringBuilder sbNgk = new StringBuilder();
            StringBuilder sbKrm = new StringBuilder();
            for(int i =0; i<daftarKartuHarian.length; i++){
                sb.append(daftarKartuHarian[i]).append(",");
                sbInd.append(daftarKartuHarianIndonesia[i]).append(",");
                sbNgk.append(daftarKartuHarianNgoko[i]).append(",");
                sbKrm.append(daftarKartuHarianKrama[i]).append(",");
            }
            ed.putString("daftar_kartu_harian", sb.toString());
            ed.putString("daftar_kartu_harian_indonesia", sbInd.toString());
            ed.putString("daftar_kartu_harian_ngoko", sbNgk.toString());
            ed.putString("daftar_kartu_harian_krama", sbKrm.toString());
            ed.commit();
        } else {
            String ambilKata = sharedPrefs.getString("daftar_kartu_harian", null);
            String ambilInd = sharedPrefs.getString("daftar_kartu_harian_indonesia", null);
            String ambilNgk = sharedPrefs.getString("daftar_kartu_harian_ngoko", null);
            String ambilKrm = sharedPrefs.getString("daftar_kartu_harian_krama", null);
            daftarKartuHarian = ambilKata.split(",");
            daftarKartuHarianIndonesia = ambilInd.split(",");
            daftarKartuHarianNgoko = ambilNgk.split(",");
            daftarKartuHarianKrama = ambilKrm.split(",");
        }

        viewPager = findViewById(R.id.kartu_harian_ViewPager);
        KartuHarianViewPagerAdapter viewPagerAdapter = new KartuHarianViewPagerAdapter(this, daftarKartuHarian, daftarKartuHarianIndonesia, daftarKartuHarianNgoko, daftarKartuHarianKrama);
        viewPager.setAdapter(viewPagerAdapter);

        setupBottomNavigationView();
        setupSliderIndicator();
    }

    private void setupBottomNavigationView() {
        bottomNavigationViewEx = findViewById(R.id.bottom_bar);
        this.bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_belajar:
                        break;
                    case R.id.navigation_cari:
                        mulaiScan();
                        startActivity(new Intent(getApplicationContext(), CariActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navigation_kuis:
                        startActivity(new Intent(getApplicationContext(), KuisActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_lainnya:
                        startActivity(new Intent(getApplicationContext(), LainnyaActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });
        bottomNavigationViewEx.setSelectedItemId(R.id.navigation_belajar);
    }

    @Override
    public void onBackPressed() {
        if (mTombolKembali + WAKTU_KELUAR_INTERVAL > System.currentTimeMillis()) {
            ActivityCompat.finishAffinity(this);
            return;
        } else {
            Toast.makeText(getApplicationContext(), "Tekan tombol kembali dua kali untuk keluar", Toast.LENGTH_SHORT).show();
        }
        mTombolKembali = System.currentTimeMillis();
    }

    private void setupSliderIndicator() {
        CirclePageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }

    public void mulaiBelajar(View view) {
        switch (view.getId()) {
            case R.id.ngoko_lugu_kartu:
                startActivity(new Intent(this, NgokoLuguActivity.class));
                break;
            case R.id.ngoko_alus_kartu:
                startActivity(new Intent(this, NgokoAlusActivity.class));
                break;
            case R.id.krama_lugu_kartu:
                startActivity(new Intent(this, KramaLuguActivity.class));
                break;
            case R.id.krama_alus_kartu:
                startActivity(new Intent(this, KramaAlusActivity.class));
                break;
        }
    }

    private void mulaiScan() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationViewEx.setSelectedItemId(R.id.navigation_belajar);
    }
}

