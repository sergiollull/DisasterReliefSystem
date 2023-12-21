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

public class AdapterYardimNoktasiMain extends RecyclerView.Adapter<AdapterYardimNoktasiMain.MyViewHolder> {

    Context context;
    ArrayList<YardimNoktasiBilgileri> list;



    public AdapterYardimNoktasiMain(Context context, ArrayList<YardimNoktasiBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.yardim_noktasi_list,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        YardimNoktasiBilgileri yardimNoktasiBilgileri = list.get(position);
        holder.il.setText(yardimNoktasiBilgileri.getIl());
        holder.ilce.setText(yardimNoktasiBilgileri.getIlce());
        holder.mahalle.setText(yardimNoktasiBilgileri.getMahalle());
        holder.sokak.setText(yardimNoktasiBilgileri.getSokak());
        holder.icerik.setText(yardimNoktasiBilgileri.getIcerik());
        holder.saat.setText(yardimNoktasiBilgileri.getSaat());
        holder.enlem.setText(String.valueOf(yardimNoktasiBilgileri.getEnlem()));
        holder.boylam.setText(String.valueOf(yardimNoktasiBilgileri.getBoylam()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView il,ilce,mahalle,sokak,icerik,saat,enlem,boylam;
        Button buttonedit,buttondelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            il = itemView.findViewById(R.id.yardimil);
            ilce = itemView.findViewById(R.id.yardimilce);
            mahalle= itemView.findViewById(R.id.yardimmahalle);
            sokak=itemView.findViewById(R.id.yardimsokak);
            saat=itemView.findViewById(R.id.yardimsaat);
            icerik=itemView.findViewById(R.id.yardimicerik);
            enlem=itemView.findViewById(R.id.yardimenlem);
            boylam=itemView.findViewById(R.id.yardimboylam);
            buttonedit =itemView.findViewById(R.id.yardimeditle);
            buttondelete=itemView.findViewById(R.id.yardimsil);
        }
    }
}
