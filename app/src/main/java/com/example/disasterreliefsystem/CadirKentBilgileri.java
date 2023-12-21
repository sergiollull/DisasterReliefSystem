package com.example.disasterreliefsystem;

import org.checkerframework.checker.units.qual.C;

public class CadirKentBilgileri {
    public String il;
    public String ilce;
    public String mahalle;
    public String sokak;
    public String bosyer;
    public Double enlem;
    public Double boylam;

    public  String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public  String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public  String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public  String getSokak() {
        return sokak;
    }

    public void setSokak(String sokak) {
        this.sokak = sokak;
    }

    public  String getBosyer() {
        return bosyer;
    }

    public void setBosyer(String bosyer) {
        this.bosyer = bosyer;
    }

    public  double getEnlem() {
        return enlem;
    }

    public void setEnlem(double enlem) {
        this.enlem = enlem;
    }

    public  double getBoylam() {
        return boylam;
    }

    public void setBoylam(double boylam) {
        this.boylam = boylam;
    }

    public CadirKentBilgileri(){

    }
    public CadirKentBilgileri(String il,String ilce,String mahalle,String sokak, String bosyer,double enlem,double boylam){
        this.il=il;
        this.ilce=ilce;
        this.mahalle=mahalle;
        this.sokak=sokak;
        this.bosyer=bosyer;
        this.enlem=enlem;
        this.boylam=boylam;
    }
}
