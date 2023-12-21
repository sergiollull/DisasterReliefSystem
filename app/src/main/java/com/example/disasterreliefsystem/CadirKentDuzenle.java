package com.example.disasterreliefsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CadirKentDuzenle extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterCadirKent adapterCadirKent;
    Double latitude,longitude;
    GetLocation getLocation= new GetLocation();
    ArrayList<CadirKentBilgileri> list ;
    String a,b,c,d;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.recview_cadirkent);
        super.onCreate(savedInstanceState);
        recyclerView= findViewById(R.id.recviewcadirkent);
        databaseReference= FirebaseDatabase.getInstance().getReference("cadirkent");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        CadirKentBilgileri cadirKentBilgileri = new CadirKentBilgileri();
        adapterCadirKent = new AdapterCadirKent(this,list);
        recyclerView.setAdapter(adapterCadirKent);

        Log.d("LATİTUDE", String.valueOf(getLocation.getLatitude()));
        Log.d("LATİTUDE", String.valueOf(getLocation.getLongitude()));
        Collections.sort(list, new Comparator<CadirKentBilgileri>() {
            @Override
            public int compare(CadirKentBilgileri t1, CadirKentBilgileri t2) {
                return t1.getBosyer().compareToIgnoreCase(t2.getBosyer());
            }

        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Collections.reverse(list);
                    CadirKentBilgileri cadirKentBilgileri = dataSnapshot.getValue(CadirKentBilgileri.class);
                    list.add(cadirKentBilgileri);



                }
                SortandCalculate(list);
                adapterCadirKent.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void SortandCalculate(List<CadirKentBilgileri> sortcadirkent){
        Collections.sort(sortcadirkent, new Comparator<CadirKentBilgileri>() {
            @Override
            public int compare(CadirKentBilgileri t1, CadirKentBilgileri t2) {
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
        System.out.println("----------------------asadsada---------------------");
        for (int i = 0; i<sortcadirkent.size();i++){
            System.out.println("LAT:" + sortcadirkent.get(i).getEnlem() + " LONG:" + sortcadirkent.get(i).getBoylam() + " ISIM: " + sortcadirkent.get(i).getMahalle());
            System.out.println("MESAFE " + String.valueOf(Math.sqrt(Math.pow(sortcadirkent.get(i).getEnlem()-getLocation.getLatitude(),2)+ Math.pow(sortcadirkent.get(i).getBoylam()-getLocation.getLongitude(),2))));
        }
        System.out.println("----------------------asadsada---------------------");
    }

}