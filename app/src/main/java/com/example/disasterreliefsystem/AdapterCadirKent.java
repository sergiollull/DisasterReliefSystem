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

public class AdapterCadirKent extends RecyclerView.Adapter<AdapterCadirKent.MyViewHolder> {

    Context context;
    ArrayList<CadirKentBilgileri> list;



    public AdapterCadirKent(Context context, ArrayList<CadirKentBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cadir_kent_duzenle,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        CadirKentBilgileri cadirKentBilgileri = list.get(position);
        holder.il.setText(cadirKentBilgileri.getIl());
        holder.ilce.setText(cadirKentBilgileri.getIlce());
        holder.mahalle.setText(cadirKentBilgileri.getMahalle());
        holder.sokak.setText(cadirKentBilgileri.getSokak());
        holder.bosyer.setText(cadirKentBilgileri.getBosyer());
        holder.enlem.setText(String.valueOf(cadirKentBilgileri.getEnlem()));
        holder.boylam.setText(String.valueOf(cadirKentBilgileri.getBoylam()));

        holder.buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.buttonedit.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_cadir_kent_edit))
                        .setExpanded(true,2000).create();
                        //dialogPlus.show();
                        View view1 = dialogPlus.getHolderView();
                        EditText il,ilce,mahalle,sokak,bosyer,enlem,boylam;
                        il = view1.findViewById(R.id.cadirilduzenle);
                        ilce = view1.findViewById(R.id.cadirilceduzenle);
                        mahalle=view1.findViewById(R.id.cadirmahalleduzenle);
                        sokak=view1.findViewById(R.id.cadirsokakduzenle);
                        bosyer=view1.findViewById(R.id.cadirbosyerduzenle);
                        Button kaydet= view1.findViewById(R.id.cadirduzenlekaydet);
                        enlem = view1.findViewById(R.id.cadirenlemduzenle);
                        boylam = view1.findViewById(R.id.cadirboylamduzenle);
                        il.setText(cadirKentBilgileri.getIl());
                        ilce.setText(cadirKentBilgileri.getIlce());
                        mahalle.setText(cadirKentBilgileri.getMahalle());
                        sokak.setText(cadirKentBilgileri.getSokak());
                        bosyer.setText(cadirKentBilgileri.getBosyer());
                        enlem.setText(String.valueOf(cadirKentBilgileri.getEnlem()));
                        boylam.setText(String.valueOf(cadirKentBilgileri.getBoylam()));


                        dialogPlus.show();
                        kaydet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Map<String,Object> map  = new HashMap<>();
                                map.put("il",il.getText().toString());
                                map.put("ilce",ilce.getText().toString());
                                map.put("mahalle",mahalle.getText().toString());
                                map.put("sokak",sokak.getText().toString());
                                map.put("bosyer",(bosyer.getText().toString()));
                                map.put("enlem",Double.valueOf(enlem.getText().toString()));
                                map.put("boylam",Double.valueOf(boylam.getText().toString()));
                                FirebaseDatabase.getInstance().getReference().child("cadirkent").child(mahalle.getText().toString()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        FirebaseDatabase.getInstance().getReference().child("cadirkent").child(cadirKentBilgileri.getMahalle()).removeValue();
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
        TextView il,ilce,mahalle,sokak,bosyer,enlem,boylam;
        Button buttonedit,buttondelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            il = itemView.findViewById(R.id.cadiril);
            ilce = itemView.findViewById(R.id.cadirilce);
            mahalle= itemView.findViewById(R.id.cadirmahalle);
            sokak=itemView.findViewById(R.id.cadirsokak);
            bosyer=itemView.findViewById(R.id.cadirbosyer);
           enlem=itemView.findViewById(R.id.cadirenlem);
            boylam=itemView.findViewById(R.id.cadirboylam);
            buttonedit =itemView.findViewById(R.id.cadirkenteditle);
            buttondelete=itemView.findViewById(R.id.cadirkentsil);
        }
    }
}
