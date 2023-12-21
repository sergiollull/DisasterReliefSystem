package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VeriDuzenle extends AppCompatActivity {
    ImageView cadirkentduzenle,bazistasyonuduzenle,sahrahastanesiduzenle,yardimnoktasiduzenle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veri_duzenle);
        bazistasyonuduzenle=findViewById(R.id.bazistasyonuduzenle);
        cadirkentduzenle= findViewById(R.id.cadirkentduzenle);
        sahrahastanesiduzenle = findViewById(R.id.sahrahastanesiduzenle);
        yardimnoktasiduzenle=findViewById(R.id.yardimnoktasiduzenle);
        yardimnoktasiduzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openYardimNoktasiDuzenle();
            }
        });
        sahrahastanesiduzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSahraHastanesiDuzenle();
            }
        });
        bazistasyonuduzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBazIstasyonuDuzenle();
            }
        });
        cadirkentduzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadirKentDuzenle();
            }
        });
    }
    public void openCadirKentDuzenle(){
        Intent intent = new Intent(this,CadirKentDuzenle.class);
        startActivity(intent);
    }
    public void openBazIstasyonuDuzenle(){
        Intent intent1 = new Intent(this,BazIstasyonuDuzenle.class);
        startActivity(intent1);
    }
    public void openSahraHastanesiDuzenle(){
        Intent intent2 = new Intent(this,SahraHastanesiDuzenle.class);
        startActivity(intent2);
    }
    public void openYardimNoktasiDuzenle(){
        Intent intent3 = new Intent(this, YardimNoktasiDuzenle.class);
        startActivity(intent3);
    }
}