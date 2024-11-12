package com.example.myfin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TransaksiDataSource dataSource;
    private EditText namaEditText, jumlahEditText, tanggalEditText;
    private Button tambahButton;
    private ListView transaksiListView;
    private TransaksiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new TransaksiDataSource(this);
        dataSource.open();

        namaEditText = findViewById(R.id.namaEditText);
        jumlahEditText = findViewById(R.id.jumlahEditText);
        tanggalEditText = findViewById(R.id.tanggalEditText);
        tambahButton = findViewById(R.id.tambahButton);
        transaksiListView = findViewById(R.id.transaksiListView);

        List<Transaksi> transaksiList = dataSource.getAllTransaksi();
        adapter = new TransaksiAdapter(this, transaksiList);
        transaksiListView.setAdapter(adapter);

        // Set up the DatePicker for tanggalEditText
        tanggalEditText.setFocusable(false);  // Disable direct input
        tanggalEditText.setClickable(true);   // Enable clicking

        tanggalEditText.setOnClickListener(v -> {
            // Get current date
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Set up the DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // Format the date and set it in the EditText
                        String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;
                        tanggalEditText.setText(date);
                    },
                    year, month, day);

            // Show the date picker dialog
            datePickerDialog.show();
        });

        tambahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaEditText.getText().toString();
                double jumlah = Double.parseDouble(jumlahEditText.getText().toString());
                String tanggal = tanggalEditText.getText().toString();

                if (nama.isEmpty() || tanggal.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nama dan tanggal harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    Transaksi transaksi = new Transaksi(0, nama, jumlah, tanggal);
                    dataSource.tambahTransaksi(transaksi);
                    updateTransaksiList();
                }
            }
        });
    }

    private void updateTransaksiList() {
        List<Transaksi> transaksiList = dataSource.getAllTransaksi();
        adapter.updateTransaksiList(transaksiList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}
