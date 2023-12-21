package com.example.disasterreliefsystem;

public class BazBilgileri {
    String il,ilce,mahalle,sokak,operator;
    Double enlem,boylam;

    public String getIl() {
        return il;
    }
    public String getOperator() {
        return operator;
    }

    public void setIl(String il) {
        this.il = il;
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

    public BazBilgileri(){

    }
    public BazBilgileri(String il,String ilce,String mahalle,String sokak,String operator,double enlem,double boylam){
        this.il=il;
        this.ilce=ilce;
        this.mahalle=mahalle;
        this.sokak=sokak;
        this.operator=operator;
        this.enlem=enlem;
        this.boylam=boylam;
    }
}
