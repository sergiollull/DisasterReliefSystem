package com.example.disasterreliefsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class YetkiliKullaniciGiris extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    private EditText yetkiliMail, yetkiliSifre;
    private Button yetkiligiris, yetkilisifreyenile;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yetkili_kullanici_giris);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        yetkiliMail = findViewById(R.id.yetkiligirisemail);
        yetkiliSifre = findViewById(R.id.yetkiligirissifre);
        yetkiligiris = findViewById(R.id.yetkiligirisbutton);
        yetkilisifreyenile = findViewById(R.id.yetkilisifremiunuttum);
        yetkiligiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = yetkiliMail.getText().toString().trim();
                String password = yetkiliSifre.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(YetkiliKullaniciGiris.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(YetkiliKullaniciGiris.this,AdminEkrani.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(YetkiliKullaniciGiris.this,"Başarısız, Bilgilerinizi kontrol edin",Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });
        yetkilisifreyenile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sifreyenile();
            }
        });
    }
    public void sifreyenile(){
        Intent intent= new Intent(this, SifremiUnuttum.class);
        startActivity(intent);
    }
}