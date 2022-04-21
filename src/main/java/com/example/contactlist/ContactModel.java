package com.example.contactlist;

public class ContactModel {
    private String nama, nomor;

    public ContactModel(String nama, String nomor){
        this.nama = nama;
        this.nomor = nomor;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNomor(String nim) {
        this.nomor = nim;
    }

    public String getNama() {
        return nama;
    }

    public String getNomor() {
        return nomor;
    }
}
