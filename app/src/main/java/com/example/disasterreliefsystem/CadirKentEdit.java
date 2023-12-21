package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadirKentEdit extends AppCompatActivity {
    EditText cadiril,cadirilce,cadirmahalle,cadirsokak,cadirbosyer,cadirenlem,cadirboylam;
    Button kaydetbutton;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadir_kent_edit);
        databaseReference = FirebaseDatabase.getInstance().getReference("cadirkent");
        cadiril = findViewById(R.id.cadirilduzenle);
        cadirilce = findViewById(R.id.cadirilceduzenle);
        cadirmahalle = findViewById(R.id.cadirmahalleduzenle);
        cadirsokak = findViewById(R.id.cadirsokakduzenle);
        cadirbosyer = findViewById(R.id.cadirbosyerduzenle);
        cadirenlem = findViewById(R.id.cadirenlemduzenle);
        cadirboylam = findViewById(R.id.cadirboylamduzenle);
        kaydetbutton = findViewById(R.id.cadirduzenlekaydet);
    }}