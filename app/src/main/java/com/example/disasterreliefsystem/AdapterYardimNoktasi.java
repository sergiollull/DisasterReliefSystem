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

public class AdapterYardimNoktasi extends RecyclerView.Adapter<AdapterYardimNoktasi.MyViewHolder> {

    Context context;
    ArrayList<YardimNoktasiBilgileri> list;



    public AdapterYardimNoktasi(Context context, ArrayList<YardimNoktasiBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_yardim_noktasi_duzenle,parent,false);
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

        holder.buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.buttonedit.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_yardim_noktasi_edit))
                        .setExpanded(true,2000).create();
                //dialogPlus.show();
                View view1 = dialogPlus.getHolderView();
                EditText il,ilce,mahalle,sokak,icerik,saat,enlem,boylam;
                il = view1.findViewById(R.id.yardimilduzenle);
                ilce = view1.findViewById(R.id.yardimilceduzenle);
                mahalle=view1.findViewById(R.id.yardimmahalleduzenle);
                sokak=view1.findViewById(R.id.yardimsokakduzenle);
                saat=view1.findViewById(R.id.yardimsaatduzenle);
                icerik=view1.findViewById(R.id.yardimicerikduzenle);
                Button kaydet= view1.findViewById(R.id.yardimduzenlekaydet);
                enlem = view1.findViewById(R.id.yardimenlemduzenle);
                boylam = view1.findViewById(R.id.yardimboylamduzenle);
                il.setText(yardimNoktasiBilgileri.getIl());
                ilce.setText(yardimNoktasiBilgileri.getIlce());
                mahalle.setText(yardimNoktasiBilgileri.getMahalle());
                sokak.setText(yardimNoktasiBilgileri.getSokak());
                icerik.setText(yardimNoktasiBilgileri.getIcerik());
                saat.setText(yardimNoktasiBilgileri.getSaat());
                enlem.setText(String.valueOf(yardimNoktasiBilgileri.getEnlem()));
                boylam.setText(String.valueOf(yardimNoktasiBilgileri.getBoylam()));


                dialogPlus.show();
                kaydet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map  = new HashMap<>();
                        map.put("il",il.getText().toString());
                        map.put("ilce",ilce.getText().toString());
                        map.put("mahalle",mahalle.getText().toString());
                        map.put("sokak",sokak.getText().toString());
                        map.put("saat",saat.getText().toString());
                        map.put("icerik",(icerik.getText().toString()));
                        map.put("enlem",Double.valueOf(enlem.getText().toString()));
                        map.put("boylam",Double.valueOf(boylam.getText().toString()));
                        FirebaseDatabase.getInstance().getReference().child("yardimnoktasi").child(mahalle.getText().toString()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        FirebaseDatabase.getInstance().getReference().child("yardimnoktasi").child(yardimNoktasiBilgileri.getMahalle()).removeValue();
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
