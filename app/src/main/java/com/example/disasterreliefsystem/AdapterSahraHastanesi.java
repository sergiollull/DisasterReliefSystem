package com.example.disasterreliefsystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterSahraHastanesi extends RecyclerView.Adapter<AdapterSahraHastanesi.MyViewHolder> {

    Context context;
    ArrayList<SahraHastanesiBilgileri> list;



    public AdapterSahraHastanesi(Context context, ArrayList<SahraHastanesiBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sahra_hastanesi_duzenle,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        SahraHastanesiBilgileri sahraHastanesiBilgileri = list.get(position);
        System.out.println(sahraHastanesiBilgileri);
        holder.il.setText(sahraHastanesiBilgileri.getIl());
        holder.ilce.setText(sahraHastanesiBilgileri.getIlce());
        holder.mahalle.setText(sahraHastanesiBilgileri.getMahalle());
        holder.sokak.setText(sahraHastanesiBilgileri.getSokak());
        holder.musaitlik.setText(sahraHastanesiBilgileri.getMusaitlik());
        holder.enlem.setText(String.valueOf(sahraHastanesiBilgileri.getEnlem()));
        holder.boylam.setText(String.valueOf(sahraHastanesiBilgileri.getBoylam()));

        holder.buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.buttonedit.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_sahra_hastanesi_edit))
                        .setExpanded(true,2000).create();
                //dialogPlus.show();
                View view1 = dialogPlus.getHolderView();
                EditText il,ilce,mahalle,sokak,musaitlik,enlem,boylam;
                il = view1.findViewById(R.id.sahrailduzenle);
                ilce = view1.findViewById(R.id.sahrailceduzenle);
                mahalle=view1.findViewById(R.id.sahramahalleduzenle);
                sokak=view1.findViewById(R.id.sahrasokakduzenle);
                musaitlik=view1.findViewById(R.id.sahramusaitlikduzenle);
                Button kaydet= view1.findViewById(R.id.sahraduzenlekaydet);
                enlem = view1.findViewById(R.id.sahraenlemduzenle);
                boylam = view1.findViewById(R.id.sahraboylamduzenle);
                il.setText(sahraHastanesiBilgileri.getIl());
                ilce.setText(sahraHastanesiBilgileri.getIlce());
                mahalle.setText(sahraHastanesiBilgileri.getMahalle());
                sokak.setText(sahraHastanesiBilgileri.getSokak());
                musaitlik.setText(sahraHastanesiBilgileri.getMusaitlik());
                enlem.setText(String.valueOf(sahraHastanesiBilgileri.getEnlem()));
                boylam.setText(String.valueOf(sahraHastanesiBilgileri.getBoylam()));


                dialogPlus.show();
                kaydet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map  = new HashMap<>();
                        map.put("il",il.getText().toString());
                        map.put("ilce",ilce.getText().toString());
                        map.put("mahalle",mahalle.getText().toString());
                        map.put("sokak",sokak.getText().toString());
                        map.put("musaitlik",(musaitlik.getText().toString()));
                        map.put("enlem",Double.valueOf(enlem.getText().toString()));
                        map.put("boylam",Double.valueOf(boylam.getText().toString()));
                        FirebaseDatabase.getInstance().getReference().child("sahrahastanesi").child(mahalle.getText().toString()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.il.getContext(), "", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });



                    }
                });
            }
        });
        holder.buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.il.getContext());
                builder.setTitle("Silmek istediğinize emin misiniz?");
                builder.setMessage("Silinen veri geri alınamaz.");
                builder.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("cadirkent").child(sahraHastanesiBilgileri.getMahalle()).removeValue();
                        Toast.makeText(holder.il.getContext(),"Başarıyla Silindi.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.il.getContext(),"İptal Edildi.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView il,ilce,mahalle,sokak,musaitlik,enlem,boylam;
        Button buttonedit,buttondelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            il = itemView.findViewById(R.id.sahrail);
            ilce = itemView.findViewById(R.id.sahrailce);
            mahalle= itemView.findViewById(R.id.sahramahalle);
            sokak=itemView.findViewById(R.id.sahrasokak);
            musaitlik=itemView.findViewById(R.id.sahramusaitlik);
            enlem=itemView.findViewById(R.id.sahraenlem);
            boylam=itemView.findViewById(R.id.sahraboylam);
            buttonedit =itemView.findViewById(R.id.sahraeditle);
            buttondelete=itemView.findViewById(R.id.sahrasil);
        }
    }
}
