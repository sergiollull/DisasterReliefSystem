package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ImageView buttonyetkili,buttoncadirkent,buttonbazistasyonu,buttonsahrahastanesi,buttonyardimnoktasi,buttonlistview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonbazistasyonu=findViewById(R.id.bazistasyonubutton);
        buttonsahrahastanesi=findViewById(R.id.sahrahastanesibutton);
        buttoncadirkent = findViewById(R.id.cadirkentbutton);
        buttonyetkili = findViewById(R.id.girisyapbutton);
        buttonyardimnoktasi=findViewById(R.id.yardimnoktalaributton);
        buttonlistview=findViewById(R.id.listviewbutton);

        buttonlistview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListView();
            }
        });
        buttoncadirkent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadirKent();
            }
        });
        buttonyetkili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGirisyap();
            }
        });
        buttonbazistasyonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBazIstasyonu();
            }
        });
        buttonsahrahastanesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSahraHastanesi();
            }
        });
        buttonyardimnoktasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openYardimNoktasi();
            }
        });

    }
    public void openGirisyap(){
        Intent intent = new Intent(this,YetkiliKullaniciGiris.class);
        startActivity(intent);
    }
    public void openCadirKent(){
        Intent intent1 = new Intent(this,CadirKent.class);
        startActivity(intent1);
    }
    public void openBazIstasyonu(){
        Intent intent2 = new Intent(this,BazIstasyonu.class);
        startActivity(intent2);
    }
    public void openSahraHastanesi(){
        Intent intent3 = new Intent(this,SahraHastanesi.class);
        startActivity(intent3);
    }
    public void openYardimNoktasi(){
        Intent intent4 = new Intent(this,YardimNoktasi.class);
        startActivity(intent4);
    }
    public void openListView(){
        Intent intent5 = new Intent(this,ListViewMain.class);
        startActivity(intent5);
    }

}