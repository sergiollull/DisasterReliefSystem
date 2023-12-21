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

public class BazIstasyonuList extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterBazIstasyonuMain adapterBazIstasyonu;
    GetLocation getLocation= new GetLocation();
    ArrayList<BazBilgileri> list ;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.recview_bazistasyonu);
        super.onCreate(savedInstanceState);
        recyclerView= findViewById(R.id.recviewbazistasyonu);
        databaseReference= FirebaseDatabase.getInstance().getReference("bazistasyonu");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<BazBilgileri>();
        adapterBazIstasyonu = new AdapterBazIstasyonuMain(this,list);
        recyclerView.setAdapter(adapterBazIstasyonu);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BazBilgileri bazBilgileri = dataSnapshot.getValue(BazBilgileri.class);
                    list.add(bazBilgileri);
                }
                SortandCalculate(list);
                adapterBazIstasyonu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void SortandCalculate(List<BazBilgileri> sortbazistasyonu){
        Collections.sort(sortbazistasyonu, new Comparator<BazBilgileri>() {
            @Override
            public int compare(BazBilgileri t1, BazBilgileri t2) {
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