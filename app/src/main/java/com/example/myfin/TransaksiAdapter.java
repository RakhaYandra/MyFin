package com.example.myfin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TransaksiAdapter extends ArrayAdapter<Transaksi> {

    private Context context;
    private List<Transaksi> transaksiList;

    public TransaksiAdapter(Context context, List<Transaksi> transaksiList) {
        super(context, 0, transaksiList);
        this.context = context;
        this.transaksiList = transaksiList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_transaksi, parent, false);
        }

        Transaksi transaksi = transaksiList.get(position);

        TextView namaTextView = convertView.findViewById(R.id.namaTextView);
        TextView jumlahTextView = convertView.findViewById(R.id.jumlahTextView);
        TextView tanggalTextView = convertView.findViewById(R.id.tanggalTextView);

        namaTextView.setText(transaksi.getNama());
        jumlahTextView.setText("Rp " + transaksi.getJumlah());
        tanggalTextView.setText(transaksi.getTanggal());

        return convertView;
    }

    public void updateTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
        notifyDataSetChanged();
    }
}
