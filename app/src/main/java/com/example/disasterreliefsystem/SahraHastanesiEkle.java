package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SahraHastanesiEkle extends AppCompatActivity {
    private EditText hastaneil,hastaneilce,hastanemahalle,hastanesokak,hastaneenlem,hastaneboylam,hastanemusaitlik;
    private Button hastanekaydet;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahra_hastanesi_ekle);
        hastaneil = findViewById(R.id.hastaneilekle);
        hastaneilce= findViewById(R.id.hastaneilceekle);
        hastanemahalle= findViewById(R.id.hastanemahalleekle);
        hastanesokak=findViewById(R.id.hastanesokakekle);
        hastaneenlem=findViewById(R.id.hastaneenlemekle);
        hastaneboylam=findViewById(R.id.hastaneboylamekle);
        hastanemusaitlik=findViewById(R.id.hastanemusaitlik);
        hastanekaydet=findViewById(R.id.hastanekaydet);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("sahrahastanesi");
        hastanekaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHastaneInformation();
            }
        });



    }
    private void saveHastaneInformation(){
        String il = hastaneil.getText().toString();
        String ilce= hastaneilce.getText().toString();
        String mahalle = hastanemahalle.getText().toString();
        String sokak= hastanesokak.getText().toString();
        String musaitlik= hastanemusaitlik.getText().toString();
        double enlem = Double.parseDouble(hastaneenlem.getText().toString().trim());
        double boylam = Double.parseDouble(hastaneboylam.getText().toString().trim());

        SahraHastanesiBilgileri sahraHastanesiBilgileri = new SahraHastanesiBilgileri(il,ilce,mahalle,sokak,musaitlik,enlem,boylam);
        mDatabase.child(mahalle).setValue(sahraHastanesiBilgileri);
        Log.d("asdf", "KAYDEDİLDİ");
        Toast.makeText(this, "Kaydedildi", Toast.LENGTH_SHORT).show();
        hastaneil.getText().clear();
        hastaneilce.getText().clear();
        hastanemahalle.getText().clear();
        hastanesokak.getText().clear();
        hastaneenlem.getText().clear();
        hastaneboylam.getText().clear();
        hastanemusaitlik.getText().clear();
    }
}