package com.example.myfin;

public class Transaksi {
    private int id;
    private String nama;
    private double jumlah;
    private String tanggal;

    public Transaksi(int id, String nama, double jumlah, String tanggal) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
