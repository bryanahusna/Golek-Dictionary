package com.bryanahusna.golek.cari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.bryanahusna.golek.R;

public class DaftarKataFavoritActivity extends AppCompatActivity {
    SQLiteDatabase database;
    RecyclerView favoritRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kata_favorit);
        Toolbar toolbar = findViewById(R.id.favorit_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        favoritRecyclerView = findViewById(R.id.favorit_recyclerview);
        favoritRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tampilkanFavorit();
    }

    private void tampilkanFavorit() {
        database = new DaftarKataSQLiteHelper(this).getWritableDatabase();
        String[] semua = {"_ID", DaftarKataSQLiteHelper.COLUMN_SEMUA, DaftarKataSQLiteHelper.COLUMN_TIPE, DaftarKataSQLiteHelper.COLUMN_URUTAN, DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT};
        String where = DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT + " = ?";
        String[] seleksiArgs = {"1"};
        String urut = DaftarKataSQLiteHelper.COLUMN_SEMUA + " COLLATE NOCASE";
        Cursor mCursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME_LIST, semua, where, seleksiArgs, null, null, urut);
        HasilPencarianAdapter adapter = new HasilPencarianAdapter(this, mCursor);
        favoritRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilkanFavorit();
    }
}
