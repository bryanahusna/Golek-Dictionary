package com.bryanhusna.golek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailHasilPencarianActivity extends AppCompatActivity {
    private TextView judul;
    private TextView tipeJudul;
    private TextView arti1Tipe;
    private TextView arti1Arti;
    private TextView arti2Tipe;
    private TextView arti2Arti;
    private TextView arti3Tipe;
    private TextView arti3Arti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hasil_pencarian);
        Toolbar toolbar = findViewById(R.id.hasil_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        judul = findViewById(R.id.judul_kata_hasil);
        tipeJudul = findViewById(R.id.tipe_kata_hasil);
        arti1Tipe = findViewById(R.id.arti1_tipe_hasil);
        arti1Arti = findViewById(R.id.arti1_arti_hasil);
        arti2Tipe = findViewById(R.id.arti2_tipe_hasil);
        arti2Arti = findViewById(R.id.arti2_arti_hasil);
        arti3Tipe = findViewById(R.id.arti3_tipe_hasil);
        arti3Arti = findViewById(R.id.arti3_arti_hasil);

        tampilkanKata();
    }

    private void tampilkanKata(){
        Intent intent = getIntent();
        int tipe = (intent.getIntExtra("tipe", 0));
        switch(tipe){
            case 1:
                judul.setText(intent.getStringExtra("indonesia"));
                tipeJudul.setText("Indonesia");
                arti1Tipe.setText("Ngoko:");
                arti1Arti.setText(intent.getStringExtra("ngoko"));
                arti2Tipe.setText("Krama:");
                arti2Arti.setText(intent.getStringExtra("krama"));
                arti3Tipe.setText("Krama Inggil:");
                arti3Arti.setText(intent.getStringExtra("krama_inggil"));
                break;

            case 2:
                judul.setText(intent.getStringExtra("ngoko"));
                tipeJudul.setText("Ngoko");
                arti1Tipe.setText("Indonesia:");
                arti1Arti.setText(intent.getStringExtra("indonesia"));
                arti2Tipe.setText("Krama:");
                arti2Arti.setText(intent.getStringExtra("krama"));
                arti3Tipe.setText("Krama Inggil:");
                arti3Arti.setText(intent.getStringExtra("krama_inggil"));
                break;

            case 3:
                judul.setText(intent.getStringExtra("krama"));
                tipeJudul.setText("Krama");
                arti1Tipe.setText("Indonesia:");
                arti1Arti.setText(intent.getStringExtra("indonesia"));
                arti2Tipe.setText("Ngoko:");
                arti2Arti.setText(intent.getStringExtra("ngoko"));
                arti3Tipe.setText("Krama Inggil:");
                arti3Arti.setText(intent.getStringExtra("krama_inggil"));
                break;

            case 4:
                judul.setText(intent.getStringExtra("krama_inggil"));
                tipeJudul.setText("Krama Inggil");
                arti1Tipe.setText("Indonesia:");
                arti1Arti.setText(intent.getStringExtra("indonesia"));
                arti2Tipe.setText("Ngoko:");
                arti2Arti.setText(intent.getStringExtra("ngoko"));
                arti3Tipe.setText("Krama:");
                arti3Arti.setText(intent.getStringExtra("krama"));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SQLiteDatabase database = new DaftarKataSQLiteHelper(getApplicationContext()).getWritableDatabase();
        Intent intent = getIntent();
        int urutan = intent.getIntExtra("urutan", 0);
        int tipe = intent.getIntExtra("tipe", 0);
        String where = DaftarKataSQLiteHelper.COLUMN_TIPE + " = ? AND " + DaftarKataSQLiteHelper.COLUMN_URUTAN + " = ?";
        String seleksiArgs[] = {Integer.toString(tipe), Integer.toString(urutan)};
        String kolom[] = {"_ID", DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT};

        Cursor mCursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME_LIST, kolom, where, seleksiArgs, null, null, null);
        mCursor.moveToFirst();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_hasil_pencarian_menu, menu);
        switch(mCursor.getInt(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT))){
            case 0:
                menu.findItem(R.id.action_favorit_tambah).setIcon(R.drawable.ic_favorite_border_secondary_color_24dp);
                break;
            case 1:
                menu.findItem(R.id.action_favorit_tambah).setIcon(R.drawable.ic_favorite_secondary_color_24dp);
                break;
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_favorit_tambah:
                Cursor mCursor = tambahKurangKeFavorit();
                mCursor.moveToFirst();
                switch(mCursor.getInt(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT))){
                    case 1:
                        item.setIcon(R.drawable.ic_favorite_border_secondary_color_24dp);
                        break;
                    case 0:
                        item.setIcon(R.drawable.ic_favorite_secondary_color_24dp);
                        break;
                }
                break;
        }
        return true;
    }

    private Cursor tambahKurangKeFavorit(){
        SQLiteDatabase database = new DaftarKataSQLiteHelper(getApplicationContext()).getWritableDatabase();
        Intent intent = getIntent();
        int urutan = intent.getIntExtra("urutan", 0);
        int tipe = intent.getIntExtra("tipe", 0);
        String where = DaftarKataSQLiteHelper.COLUMN_TIPE + " = ? AND " + DaftarKataSQLiteHelper.COLUMN_URUTAN + " = ?";
        String seleksiArgs[] = {Integer.toString(tipe), Integer.toString(urutan)};
        String kolom[] = {"_ID", DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT};

        Cursor mCursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME_LIST, kolom, where, seleksiArgs, null, null, null);
        mCursor.moveToFirst();
        ContentValues cv = new ContentValues();
        if(mCursor.getInt(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT)) == 0) {
            Toast.makeText(this, "Ditambahkan ke Favorit", Toast.LENGTH_SHORT).show();
            cv.put(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT, 1);
            database.update(DaftarKataSQLiteHelper.TABLE_NAME_LIST, cv, where, seleksiArgs);
        } else {
            Toast.makeText(this, "Dihapus dari Favorit", Toast.LENGTH_SHORT).show();
            cv.put(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT, 0);
            database.update(DaftarKataSQLiteHelper.TABLE_NAME_LIST, cv, where, seleksiArgs);
        }
        return mCursor;
    }
}
