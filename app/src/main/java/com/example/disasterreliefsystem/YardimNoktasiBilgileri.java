package com.example.disasterreliefsystem;

public class YardimNoktasiBilgileri {
    String il,ilce,mahalle,sokak,icerik,saat;
    Double enlem,boylam;

    public String getIl() {
        return il;
    }
    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public String getSokak() {
        return sokak;
    }

    public void setSokak(String sokak) {
        this.sokak = sokak;
    }


    public double getEnlem() {
        return enlem;
    }

    public void setEnlem(double enlem) {
        this.enlem = enlem;
    }

    public double getBoylam() {
        return boylam;
    }

    public void setBoylam(double boylam) {
        this.boylam = boylam;
    }

    public YardimNoktasiBilgileri(){

    }
    public YardimNoktasiBilgileri(String il,String ilce,String mahalle,String sokak,String icerik,double enlem,double boylam,String saat){
        this.il=il;
        this.ilce=ilce;
        this.mahalle=mahalle;
        this.sokak=sokak;
        this.icerik=icerik;
        this.saat=saat;
        this.enlem=enlem;
        this.boylam=boylam;
    }
}
