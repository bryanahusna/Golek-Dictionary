package com.bryanahusna.golek.lainnya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.bryanahusna.golek.R;
import com.bryanahusna.golek.belajar.BelajarActivity;
import com.bryanahusna.golek.cari.DaftarKataSQLiteHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SplashScreen extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void> {
    public static final int OPERATION_SEARCH_LOADER = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LoaderManager.getInstance(this).initLoader(OPERATION_SEARCH_LOADER, null, this);
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        Loader<Void> loader = loaderManager.getLoader(OPERATION_SEARCH_LOADER);
        loaderManager.initLoader(OPERATION_SEARCH_LOADER, null, this);

        jalankanDatabase();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), BelajarActivity.class));
                finish();
            }
        }, 3500);
    }


    @NonNull
    @Override
    public Loader<Void> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<Void>(this) {
            @Nullable
            @Override
            public Void loadInBackground() {
                return null;
            }

            @Override
            protected void onStartLoading(){
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Void> loader, Void data) {
        /*Toast.makeText(this, "Scan Selesai (dari SplashScreen)", Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Void> loader) {

    }
    private void jalankanDatabase(){
        SQLiteDatabase database = new DaftarKataSQLiteHelper(getApplicationContext()).getWritableDatabase();

        String[] jumlah = {DaftarKataSQLiteHelper.COLUMN_INDONESIA,
                DaftarKataSQLiteHelper.COLUMN_NGOKO,
                DaftarKataSQLiteHelper.COLUMN_NGOKO_DESK,
                DaftarKataSQLiteHelper.COLUMN_KRAMA,
                DaftarKataSQLiteHelper.COLUMN_KRAMA_DESK,
                DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL,
                DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL_DESK};

        Cursor cursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME, jumlah, null, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() < 139) {
            try {
                ContentValues contentValues = new ContentValues();
                ContentValues contentValuesAllInd = new ContentValues();
                ContentValues contentValuesAllNgk = new ContentValues();
                ContentValues contentValuesAllKr = new ContentValues();
                ContentValues contentValuesAllKrIn = new ContentValues();

                /* final Resources resourcesNgk = getApplicationContext().getResources();
                InputStream inputStreamNgk = resourcesNgk.openRawResource(R.raw.daftar_ngoko);
                BufferedReader readerNgk = new BufferedReader(new InputStreamReader(inputStreamNgk)); */

                BufferedReader readerInd = new BufferedReader(new InputStreamReader(getApplicationContext().getResources().openRawResource(R.raw.daftar_indonesia)));
                BufferedReader readerNgk = new BufferedReader(new InputStreamReader(getApplicationContext().getResources().openRawResource(R.raw.daftar_ngoko)));
                BufferedReader readerKr = new BufferedReader(new InputStreamReader(getApplicationContext().getResources().openRawResource(R.raw.daftar_krama_lugu)));
                BufferedReader readerKrIn = new BufferedReader(new InputStreamReader(getApplicationContext().getResources().openRawResource(R.raw.daftar_krama_alus)));

                String lineInd;
                String lineNgk;
                String lineKr;
                String lineKrIn;
                int urutan = 1;

                while ((lineInd = readerInd.readLine()) != null) {
                    lineNgk = readerNgk.readLine();
                    lineKr = readerKr.readLine();
                    lineKrIn = readerKrIn.readLine();

                    String[] terpisahNgk = lineNgk.split("-");
                    terpisahNgk[0] = terpisahNgk[0].trim();
                    terpisahNgk[1] = terpisahNgk[1].trim();

                    String[] terpisahKr = lineKr.split("-");
                    terpisahKr[0] = terpisahKr[0].trim();
                    terpisahKr[1] = terpisahKr[1].trim();

                    String[] terpisahKrIn = lineKrIn.split("-");
                    terpisahKrIn[0] = terpisahKrIn[0].trim();
                    terpisahKrIn[1] = terpisahKrIn[1].trim();

                    contentValues.put(DaftarKataSQLiteHelper.COLUMN_INDONESIA, lineInd);
                    contentValues.put(DaftarKataSQLiteHelper.COLUMN_NGOKO, terpisahNgk[0]);
                    contentValues.put(DaftarKataSQLiteHelper.COLUMN_NGOKO_DESK, terpisahNgk[1]);
                    contentValues.put(DaftarKataSQLiteHelper.COLUMN_KRAMA, terpisahKr[0]);
                    contentValues.put(DaftarKataSQLiteHelper.COLUMN_KRAMA_DESK, terpisahKr[1]);
                    contentValues.put(DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL, terpisahKrIn[0]);
                    contentValues.put(DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL_DESK, terpisahKrIn[1]);

                    contentValuesAllInd.put(DaftarKataSQLiteHelper.COLUMN_SEMUA, lineInd);
                    contentValuesAllInd.put(DaftarKataSQLiteHelper.COLUMN_TIPE, 1);
                    contentValuesAllInd.put(DaftarKataSQLiteHelper.COLUMN_URUTAN, urutan);
                    contentValuesAllInd.put(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT, 0);
                    contentValuesAllNgk.put(DaftarKataSQLiteHelper.COLUMN_SEMUA, terpisahNgk[0]);
                    contentValuesAllNgk.put(DaftarKataSQLiteHelper.COLUMN_TIPE, 2);
                    contentValuesAllNgk.put(DaftarKataSQLiteHelper.COLUMN_URUTAN, urutan);
                    contentValuesAllNgk.put(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT, 0);
                    contentValuesAllKr.put(DaftarKataSQLiteHelper.COLUMN_SEMUA, terpisahKr[0]);
                    contentValuesAllKr.put(DaftarKataSQLiteHelper.COLUMN_TIPE, 3);
                    contentValuesAllKr.put(DaftarKataSQLiteHelper.COLUMN_URUTAN, urutan);
                    contentValuesAllKr.put(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT, 0);
                    contentValuesAllKrIn.put(DaftarKataSQLiteHelper.COLUMN_SEMUA, terpisahKrIn[0]);
                    contentValuesAllKrIn.put(DaftarKataSQLiteHelper.COLUMN_TIPE, 4);
                    contentValuesAllKrIn.put(DaftarKataSQLiteHelper.COLUMN_URUTAN, urutan);
                    contentValuesAllKrIn.put(DaftarKataSQLiteHelper.COLUMN_STATUS_FAVORIT, 0);

                    database.insert(DaftarKataSQLiteHelper.TABLE_NAME, null, contentValues);
                    database.insert(DaftarKataSQLiteHelper.TABLE_NAME_LIST, null, contentValuesAllInd);
                    database.insert(DaftarKataSQLiteHelper.TABLE_NAME_LIST, null, contentValuesAllNgk);
                    database.insert(DaftarKataSQLiteHelper.TABLE_NAME_LIST, null, contentValuesAllKr);
                    database.insert(DaftarKataSQLiteHelper.TABLE_NAME_LIST, null, contentValuesAllKrIn);
                    urutan++;
                }
                readerInd.close();
                readerNgk.close();

            } catch (Exception e) {
                /*Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();*/
                e.printStackTrace();
            }
        }
    }
}
