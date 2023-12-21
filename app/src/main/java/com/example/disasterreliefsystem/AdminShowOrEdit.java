package com.example.disasterreliefsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminShowOrEdit extends AppCompatActivity {
    ImageView edit,show;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_or_edit);
        edit = findViewById(R.id.adminedit);
        show = findViewById(R.id.adminview);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminEdit();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminShow();
            }
        });
    }
    public void openAdminEdit(){
        Intent intent = new Intent(this, VeriDuzenle.class);
        startActivity(intent);
    }
    public void openAdminShow(){
        Intent intent1= new Intent(this,AdminVeriGoruntule.class);
        startActivity(intent1);
    }
}