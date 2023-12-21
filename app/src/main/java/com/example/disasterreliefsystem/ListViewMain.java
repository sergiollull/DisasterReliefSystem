package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ListViewMain extends AppCompatActivity {
    ImageView cadirkent,bazistasyonu,yardimnoktasi,sahrahastanesi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);
        cadirkent = findViewById(R.id.cadirkentlistview);
        bazistasyonu = findViewById(R.id.bazistasyonulistview);
        yardimnoktasi= findViewById(R.id.yardimnoktasilistview);
        sahrahastanesi=findViewById(R.id.sahrahastanesilistview);
        cadirkent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadirList();
            }
        });
        bazistasyonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBazList();
            }
        });
        yardimnoktasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openYardimList();
            }
        });
        sahrahastanesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSahraList();
            }
        });
    }
    public void openCadirList(){
        Intent intent = new Intent(this,CadirKentList.class);
        startActivity(intent);
    }
    public void openBazList(){
        Intent intent1 = new Intent(this, BazIstasyonuList.class);
        startActivity(intent1);
    }
    public void openYardimList(){
        Intent intent2 = new Intent(this, YardimNoktasiList.class);
        startActivity(intent2);
    }
    public void openSahraList(){
        Intent intent3 = new Intent(this, SahraHastanesiList.class);
        startActivity(intent3);
    }
}