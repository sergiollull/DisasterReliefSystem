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

public class AdapterSahraHastanesiMain extends RecyclerView.Adapter<AdapterSahraHastanesiMain.MyViewHolder> {

    Context context;
    ArrayList<SahraHastanesiBilgileri> list;



    public AdapterSahraHastanesiMain(Context context, ArrayList<SahraHastanesiBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sahra_hastanesi_list,parent,false);
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
