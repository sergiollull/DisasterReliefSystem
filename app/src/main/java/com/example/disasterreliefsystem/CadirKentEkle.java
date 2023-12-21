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

public class CadirKentEkle extends AppCompatActivity {
    private EditText cadirkentil,cadirkentilce,cadirkentmahalle,cadirkentsokak,cadirkentbosyer,cadirkentenlem,cadirkentboylam;
    private Button cadirkentkaydet;
    private DatabaseReference mDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadir_kent_ekle);
        cadirkentil= findViewById(R.id.cadirkentilekle);
        cadirkentilce=findViewById(R.id.cadirkentilceekle);
        cadirkentmahalle=findViewById(R.id.cadirkentmahalleekle);
        cadirkentsokak=findViewById(R.id.cadirkentsokakekle);
        cadirkentbosyer=findViewById(R.id.cadirkentboscadirsayisi);
        cadirkentenlem=findViewById(R.id.cadirkentenlemekle);
        cadirkentboylam=findViewById(R.id.cadirkentboylamekle);
        cadirkentkaydet=findViewById(R.id.cadirkentkaydet);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("cadirkent");
        cadirkentkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveCadirKentInformation();
            }
        });
    }
    private void saveCadirKentInformation(){
        String il = cadirkentil.getText().toString();
        String ilce= cadirkentilce.getText().toString();
        String mahalle = cadirkentmahalle.getText().toString();
        String sokak= cadirkentsokak.getText().toString();
        String bosyer= cadirkentbosyer.getText().toString();
        double enlem = Double.parseDouble(cadirkentenlem.getText().toString().trim());
        double boylam = Double.parseDouble(cadirkentboylam.getText().toString().trim());

        CadirKentBilgileri cadirKentBilgileri = new CadirKentBilgileri(il,ilce,mahalle,sokak,bosyer,enlem,boylam);
        mDatabase.child(mahalle).setValue(cadirKentBilgileri);
        Log.d("asdf", "KAYDEDİLDİ");
        Toast.makeText(this, "Kaydedildi", Toast.LENGTH_SHORT).show();
        cadirkentil.getText().clear();
        cadirkentilce.getText().clear();
        cadirkentmahalle.getText().clear();
        cadirkentsokak.getText().clear();
        cadirkentbosyer.getText().clear();
        cadirkentenlem.getText().clear();
        cadirkentboylam.getText().clear();
    }

}
