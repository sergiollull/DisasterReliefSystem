package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminVeriGoruntule extends AppCompatActivity {
    private ImageView cadirkent1,yardimnoktasi1,bazistasyonu1,sahrahastanesi1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_veri_goruntule);
        cadirkent1=findViewById(R.id.cadirkentadmin);
        yardimnoktasi1=findViewById(R.id.yardimnoktalariadmin);
        bazistasyonu1=findViewById(R.id.bazistasyonuadmin);
        sahrahastanesi1=findViewById(R.id.sahrahastanesiadmin);
        cadirkent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadirKent1();
            }
        });
        yardimnoktasi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openYardimNoktasi1();
            }
        });
        bazistasyonu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBazIstasyonu1();
            }
        });
        sahrahastanesi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSahraHastanesi1();
            }
        });
    }
    public void openCadirKent1(){
        Intent intent = new Intent(this, CadirKent.class);
        startActivity(intent);
    }
    public void openYardimNoktasi1(){
        Intent intent1 = new Intent(this,YardimNoktasi.class);
        startActivity(intent1);
    }
    public void openBazIstasyonu1(){
        Intent intent2=new Intent(this,BazIstasyonu.class);
        startActivity(intent2);
    }
    public void openSahraHastanesi1(){
        Intent intent3 = new Intent(this,SahraHastanesi.class);
        startActivity(intent3);
    }
}