package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminEkrani extends AppCompatActivity {
    private ImageView cadirekle, yardimekle, hastaneekle,bazekle,veriduzenle,kayit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ekrani);
        kayit=findViewById(R.id.yetkilikullanicikayit);
        cadirekle = findViewById(R.id.cadirkenteklebutton);
        veriduzenle=findViewById(R.id.AdminDuzenleButton);
        yardimekle=findViewById(R.id.yardimnoktasieklebutton);
        hastaneekle=findViewById(R.id.sahrahastanesieklebutton);
        bazekle=findViewById(R.id.bazistasyonueklebutton);
        yardimekle=findViewById(R.id.yardimnoktasieklebutton);
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openYetkiliKullaniciKayit();
            }
        });
        veriduzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVeriDuzenle();
            }
        });
        yardimekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openYardimEkle();
            }
        });
        bazekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBazEkle();
            }
        });
        cadirekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadirEkle();
            }
        });
        hastaneekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHastaneEkle();
            }
        });

    }
    public void openYardimEkle(){
        Intent intent3 = new Intent(this,YardimNoktasiEkle.class);
        startActivity(intent3);
    }
    public void openCadirEkle(){
        Intent intent = new Intent(this,CadirKentEkle.class);
        startActivity(intent);
    }
    public void openBazEkle(){
        Intent intent1 = new Intent(this,BazEkle.class);
        startActivity(intent1);
    }
    public void openHastaneEkle(){
        Intent intent2 = new Intent(this,SahraHastanesiEkle.class);
        startActivity(intent2);
    }
    public void openVeriDuzenle(){
        Intent intent4 = new Intent(this,AdminShowOrEdit.class);
        startActivity(intent4);
    }
    public void openYetkiliKullaniciKayit(){
        Intent intent5 = new Intent(this, AdminKullaniciKayit.class);
        startActivity(intent5);
    }
}