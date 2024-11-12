package com.example.myfin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TransaksiDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaksi.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TRANSAKSI = "transaksi";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_JUMLAH = "jumlah";
    public static final String COLUMN_TANGGAL = "tanggal";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_TRANSAKSI + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA + " TEXT NOT NULL, " +
                    COLUMN_JUMLAH + " REAL NOT NULL, " +
                    COLUMN_TANGGAL + " TEXT NOT NULL);";

    public TransaksiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI);
        onCreate(db);
    }
}
