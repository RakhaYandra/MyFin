package com.example.myfin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDataSource {
    private SQLiteDatabase database;
    private TransaksiDatabaseHelper dbHelper;

    public TransaksiDataSource(Context context) {
        dbHelper = new TransaksiDatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void tambahTransaksi(Transaksi transaksi) {
        ContentValues values = new ContentValues();
        values.put(TransaksiDatabaseHelper.COLUMN_NAMA, transaksi.getNama());
        values.put(TransaksiDatabaseHelper.COLUMN_JUMLAH, transaksi.getJumlah());
        values.put(TransaksiDatabaseHelper.COLUMN_TANGGAL, transaksi.getTanggal());

        database.insert(TransaksiDatabaseHelper.TABLE_TRANSAKSI, null, values);
    }

    public List<Transaksi> getAllTransaksi() {
        List<Transaksi> transaksiList = new ArrayList<>();

        Cursor cursor = database.query(TransaksiDatabaseHelper.TABLE_TRANSAKSI,
                new String[]{TransaksiDatabaseHelper.COLUMN_ID, TransaksiDatabaseHelper.COLUMN_NAMA,
                        TransaksiDatabaseHelper.COLUMN_JUMLAH, TransaksiDatabaseHelper.COLUMN_TANGGAL},
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Transaksi transaksi = cursorToTransaksi(cursor);
            transaksiList.add(transaksi);
            cursor.moveToNext();
        }
        cursor.close();
        return transaksiList;
    }

    private Transaksi cursorToTransaksi(Cursor cursor) {
        int id = cursor.getInt(0);
        String nama = cursor.getString(1);
        double jumlah = cursor.getDouble(2);
        String tanggal = cursor.getString(3);
        return new Transaksi(id, nama, jumlah, tanggal);
    }
}
