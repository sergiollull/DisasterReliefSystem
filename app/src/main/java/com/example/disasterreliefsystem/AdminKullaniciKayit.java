package com.example.disasterreliefsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminKullaniciKayit extends AppCompatActivity {
    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    private EditText adminisim, adminsoyisim, admintcno, adminemail, adminsifre;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kullanici_kayit);
        adminisim = findViewById(R.id.adminisim);
        adminsoyisim = findViewById(R.id.adminsoyisim);
        adminemail = findViewById(R.id.adminemail);
        admintcno = findViewById(R.id.admintcno);
        adminsifre = findViewById(R.id.adminsifre);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }

    public void sendData(View view) {
        writeNewUser();

    }
    public void writeNewUser(){
        String name = adminisim.getText().toString();
        String surname= adminsoyisim.getText().toString();
        String tcno= admintcno.getText().toString();
        String email= adminemail.getText().toString();
        String password = adminsifre.getText().toString();
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.setTitle("Kayıt Olunuyor.");
                        startActivity(new Intent(AdminKullaniciKayit.this,MainActivity.class));
                        progressDialog.cancel();
                        firebaseFirestore.collection("Admin User").document(FirebaseAuth.getInstance().getUid()).set(new AdminUser(name,surname,email,tcno,password));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminKullaniciKayit.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();
                    }
                });
    }
}