package com.example.disasterreliefsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SahraHastanesiList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterSahraHastanesiMain adapterSahraHastanesi;
    ArrayList<SahraHastanesiBilgileri> list ;
    GetLocation getLocation= new GetLocation();


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.recview_sahrahastanesi);
        super.onCreate(savedInstanceState);
        recyclerView= findViewById(R.id.recviewsahrahastanesi);
        databaseReference= FirebaseDatabase.getInstance().getReference("sahrahastanesi");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<SahraHastanesiBilgileri>();
        adapterSahraHastanesi = new AdapterSahraHastanesiMain(this,list);
        recyclerView.setAdapter(adapterSahraHastanesi);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SahraHastanesiBilgileri sahraHastanesiBilgileri = dataSnapshot.getValue(SahraHastanesiBilgileri.class);
                    list.add(sahraHastanesiBilgileri);
                }
                SortandCalculate(list);
                adapterSahraHastanesi.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void SortandCalculate(List<SahraHastanesiBilgileri> sortsahrahastanesi){
        Collections.sort(sortsahrahastanesi, new Comparator<SahraHastanesiBilgileri>() {
            @Override
            public int compare(SahraHastanesiBilgileri t1, SahraHastanesiBilgileri t2) {
                double x1 = getLocation.getLatitude();
                double x2 = getLocation.getLongitude();
                double a1 = Math.pow(t1.getEnlem()-x1, 2);
                double a2 = Math.pow(t1.getBoylam()-x2, 2);
                double conc1 = Math.sqrt(a1+a2);

                double b1 = Math.pow(t2.getEnlem()-x1, 2);
                double b2 = Math.pow(t2.getBoylam()-x2, 2);
                double conc2 = Math.sqrt(b1+b2);
                return Double.compare(conc1, conc2);


            }
        });

    }
}