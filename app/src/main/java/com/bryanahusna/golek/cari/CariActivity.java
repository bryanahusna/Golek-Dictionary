package com.bryanahusna.golek.cari;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.bryanahusna.golek.kuis.KuisActivity;
import com.bryanahusna.golek.lainnya.LainnyaActivity;
import com.bryanahusna.golek.R;
import com.bryanahusna.golek.belajar.BelajarActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class CariActivity extends AppCompatActivity {
    private static final int WAKTU_KELUAR_INTERVAL = 2000;
    private long mTombolKembali;

    private BottomNavigationViewEx bottomNavigationViewEx;

    private EditText cariEditText;
    private RecyclerView hasilPencarianRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupBottomNavigationView();

        hasilPencarianRecyclerView = findViewById(R.id.hasil_pencarian_recyclerview);
        hasilPencarianRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cariEditText = findViewById(R.id.cari_edittext);
        tampilkanSemua();
        cariEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sedangCari(editable.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cari_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.activity_pilih_favorit:
                startActivity(new Intent(this, DaftarKataFavoritActivity.class));
                break;
        }
        return true;
    }

    private void tampilkanSemua(){
        String[] semua = {DaftarKataSQLiteHelper.COLUMN_URUTAN, DaftarKataSQLiteHelper.COLUMN_TIPE, DaftarKataSQLiteHelper.COLUMN_SEMUA};
        String urutan = DaftarKataSQLiteHelper.COLUMN_SEMUA + " COLLATE NOCASE";
        SQLiteDatabase database = new DaftarKataSQLiteHelper(this).getReadableDatabase();
        Cursor cursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME_LIST, semua, null, null, null, null, urutan);
        HasilPencarianAdapter adapter = new HasilPencarianAdapter(this, cursor);
        hasilPencarianRecyclerView.setAdapter(adapter);
    }

    private void sedangCari(String kunci){
        String[] semua = {DaftarKataSQLiteHelper.COLUMN_URUTAN, DaftarKataSQLiteHelper.COLUMN_TIPE, DaftarKataSQLiteHelper.COLUMN_SEMUA};
        String selection = DaftarKataSQLiteHelper.COLUMN_SEMUA + " like ?";
        String[] selectionArgs = {kunci + "%"};
        String urutan = DaftarKataSQLiteHelper.COLUMN_SEMUA + " COLLATE NOCASE";
        SQLiteDatabase database = new DaftarKataSQLiteHelper(this).getReadableDatabase();
        Cursor cursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME_LIST, semua, selection, selectionArgs, null, null, urutan);
        HasilPencarianAdapter adapter = new HasilPencarianAdapter(this, cursor);
        hasilPencarianRecyclerView.setAdapter(adapter);
    }

    private void setupBottomNavigationView(){
        bottomNavigationViewEx = findViewById(R.id.bottom_bar);
        this.bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch(item.getItemId()){
                    case R.id.navigation_belajar:
                        startActivity(new Intent(getApplicationContext(), BelajarActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0,0);
                    case R.id.navigation_cari:
                        break;
                    case R.id.navigation_kuis:
                        startActivity(new Intent(getApplicationContext(), KuisActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.navigation_lainnya:
                        startActivity(new Intent(getApplicationContext(), LainnyaActivity.class).setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                        overridePendingTransition(0,0);
                        break;
                }
                return true;
            }
        });
        bottomNavigationViewEx.setSelectedItemId(R.id.navigation_cari);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationViewEx.setSelectedItemId(R.id.navigation_cari);
    }
}

