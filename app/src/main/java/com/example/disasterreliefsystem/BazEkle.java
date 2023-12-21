package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kotlin.random.URandomKt;

public class BazEkle extends AppCompatActivity {
    private EditText bazil,bazilce,bazmahalle,bazsokak,bazenlem,bazboylam,bazoperator;
    private Button bazkaydet;
    private DatabaseReference mDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baz_ekle);
        bazil= findViewById(R.id.bazilekle);
        bazilce=findViewById(R.id.bazilceekle);
        bazmahalle=findViewById(R.id.bazmahalleekle);
        bazsokak=findViewById(R.id.bazsokakekle);
        bazoperator= findViewById(R.id.bazoperatorekle);
        bazenlem=findViewById(R.id.bazenlemekle);
        bazboylam=findViewById(R.id.bazboylamekle);
        bazkaydet=findViewById(R.id.bazkaydet);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("bazistasyonu");
        bazkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveBazInformation();
            }
        });
    }
    private void saveBazInformation(){
        String il = bazil.getText().toString();
        String ilce= bazilce.getText().toString();
        String mahalle = bazmahalle.getText().toString();
        String sokak= bazsokak.getText().toString();
        String operator= bazoperator.getText().toString();
        double enlem = Double.parseDouble(bazenlem.getText().toString().trim());
        double boylam = Double.parseDouble(bazboylam.getText().toString().trim());

       BazBilgileri bazBilgileri = new BazBilgileri(il,ilce,mahalle,sokak,operator,enlem,boylam);
        mDatabase.child(mahalle).setValue(bazBilgileri);
        Log.d("asdf", "KAYDEDİLDİ");
        Toast.makeText(this, "Kaydedildi", Toast.LENGTH_SHORT).show();
        bazil.getText().clear();
        bazilce.getText().clear();
        bazmahalle.getText().clear();
        bazsokak.getText().clear();
        bazenlem.getText().clear();
        bazboylam.getText().clear();
        bazoperator.getText().clear();
    }

    }