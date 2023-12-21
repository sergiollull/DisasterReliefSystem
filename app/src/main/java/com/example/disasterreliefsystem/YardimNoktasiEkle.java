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

public class YardimNoktasiEkle extends AppCompatActivity {
    private EditText yardimil,yardimilce,yardimmahalle,yardimsokak,yardimenlem,yardimboylam,yardimicerik,yardimsaat;
    private Button yardimkaydet;
    private DatabaseReference mDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yardim_noktasi_ekle);
        yardimil=findViewById(R.id.yardimilekle);
        yardimilce=findViewById(R.id.yardimilceekle);
        yardimmahalle=findViewById(R.id.yardimmahalleekle);
        yardimsokak=findViewById(R.id.yardimsokakekle);
        yardimenlem=findViewById(R.id.yardimenlemekle);
        yardimboylam=findViewById(R.id.yardimboylamekle);
        yardimicerik=findViewById(R.id.yardimicerik);
        yardimsaat=findViewById(R.id.yardimsaat);
        yardimkaydet=findViewById(R.id.yardimkaydet);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("yardimnoktasi");
        yardimkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveYardimInformation();
            }
        });

    }
    private void saveYardimInformation(){
        String il = yardimil.getText().toString();
        String ilce= yardimilce.getText().toString();
        String mahalle = yardimmahalle.getText().toString();
        String sokak= yardimsokak.getText().toString();
        String icerik= yardimicerik.getText().toString();
        String saat = yardimsaat.getText().toString();
        double enlem = Double.parseDouble(yardimenlem.getText().toString().trim());
        double boylam = Double.parseDouble(yardimboylam.getText().toString().trim());
        YardimNoktasiBilgileri yardimNoktasiBilgileri = new YardimNoktasiBilgileri(il,ilce,mahalle,sokak,icerik,enlem,boylam,saat);
        mDatabase.child(mahalle).setValue(yardimNoktasiBilgileri);
        Log.d("asdf", "KAYDEDİLDİ");
        Toast.makeText(this, "Kaydedildi", Toast.LENGTH_SHORT).show();
        yardimil.getText().clear();
        yardimilce.getText().clear();
        yardimmahalle.getText().clear();
        yardimsokak.getText().clear();
        yardimenlem.getText().clear();
        yardimboylam.getText().clear();
        yardimsaat.getText().clear();
        yardimicerik.getText().clear();
    }
}