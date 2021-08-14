package com.bryanahusna.golek.cari;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DaftarKataSQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "daftar_kata_database";
    public static final String TABLE_NAME = "daftar_kata";
    public static final String TABLE_NAME_LIST = "semua_kata";

    public static final String COLUMN_INDONESIA = "indonesia";
    public static final String COLUMN_NGOKO = "ngoko";
    public static final String COLUMN_NGOKO_DESK = "deskripsi_ngoko";
    public static final String COLUMN_KRAMA = "krama";
    public static final String COLUMN_KRAMA_DESK = "deskripsi_krama";
    public static final String COLUMN_KRAMA_INGGIL = "krama_inggil";
    public static final String COLUMN_KRAMA_INGGIL_DESK = "deskripsi_krama_inggil";

    public static final String COLUMN_SEMUA = "semua";
    public static final String COLUMN_TIPE = "tipe";
    public static final String COLUMN_URUTAN = "urutan";
    public static final String COLUMN_STATUS_FAVORIT = "status_favorit";
    // 1 Indonesia, 2 Ngoko, 3 Krama, 4 Krama Inggil


    public DaftarKataSQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INDONESIA + " TEXT, " +
                COLUMN_NGOKO + " TEXT, " + COLUMN_NGOKO_DESK + " TEXT, " +
                COLUMN_KRAMA + " TEXT, " + COLUMN_KRAMA_DESK + " TEXT, " +
                COLUMN_KRAMA_INGGIL + " TEXT, " + COLUMN_KRAMA_INGGIL_DESK + " TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_LIST + " (" + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SEMUA + " TEXT, " + COLUMN_TIPE + " INTEGER, "
                + COLUMN_URUTAN + " INTEGER, " + COLUMN_STATUS_FAVORIT + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LIST);
        onCreate(sqLiteDatabase);
    }



}