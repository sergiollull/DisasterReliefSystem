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

public class AdapterBazIstasyonu extends RecyclerView.Adapter<AdapterBazIstasyonu.MyViewHolder> {

    Context context;
    ArrayList<BazBilgileri> list;



    public AdapterBazIstasyonu(Context context, ArrayList<BazBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_baz_istasyonu_duzenle,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        BazBilgileri bazBilgileri = list.get(position);
        holder.il.setText(bazBilgileri.getIl());
        holder.ilce.setText(bazBilgileri.getIlce());
        holder.mahalle.setText(bazBilgileri.getMahalle());
        holder.sokak.setText(bazBilgileri.getSokak());
        holder.operator.setText(bazBilgileri.getOperator());
        holder.enlem.setText(String.valueOf(bazBilgileri.getEnlem()));
        holder.boylam.setText(String.valueOf(bazBilgileri.getBoylam()));

        holder.buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.buttonedit.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_baz_istasyonu_edit))
                        .setExpanded(true,2000).create();
                //dialogPlus.show();
                View view1 = dialogPlus.getHolderView();
                EditText il,ilce,mahalle,sokak,operator,enlem,boylam;
                il = view1.findViewById(R.id.bazilduzenle);
                ilce = view1.findViewById(R.id.bazilceduzenle);
                mahalle=view1.findViewById(R.id.bazmahalleduzenle);
                sokak=view1.findViewById(R.id.bazsokakduzenle);
                operator=view1.findViewById(R.id.bazoperatorduzenle);
                Button kaydet= view1.findViewById(R.id.bazduzenlekaydet);
                enlem = view1.findViewById(R.id.bazenlemduzenle);
                boylam = view1.findViewById(R.id.bazboylamduzenle);
                il.setText(bazBilgileri.getIl());
                ilce.setText(bazBilgileri.getIlce());
                mahalle.setText(bazBilgileri.getMahalle());
                sokak.setText(bazBilgileri.getSokak());
                operator.setText(bazBilgileri.getOperator());
                enlem.setText(String.valueOf(bazBilgileri.getEnlem()));
                boylam.setText(String.valueOf(bazBilgileri.getBoylam()));


                dialogPlus.show();
                kaydet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map  = new HashMap<>();
                        map.put("il",il.getText().toString());
                        map.put("ilce",ilce.getText().toString());
                        map.put("mahalle",mahalle.getText().toString());
                        map.put("sokak",sokak.getText().toString());
                        map.put("operator",(operator.getText().toString()));
                        map.put("enlem",Double.valueOf(enlem.getText().toString()));
                        map.put("boylam",Double.valueOf(boylam.getText().toString()));
                        FirebaseDatabase.getInstance().getReference().child("bazistasyonu").child(mahalle.getText().toString()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        FirebaseDatabase.getInstance().getReference().child("cadirkent").child(bazBilgileri.getMahalle()).removeValue();
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
        TextView il,ilce,mahalle,sokak,operator,enlem,boylam;
        Button buttonedit,buttondelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            il = itemView.findViewById(R.id.bazil);
            ilce = itemView.findViewById(R.id.bazilce);
            mahalle= itemView.findViewById(R.id.bazmahalle);
            sokak=itemView.findViewById(R.id.bazsokak);
            operator=itemView.findViewById(R.id.bazoperator);
            enlem=itemView.findViewById(R.id.bazenlem);
            boylam=itemView.findViewById(R.id.bazboylam);
            buttonedit =itemView.findViewById(R.id.bazeditle);
            buttondelete=itemView.findViewById(R.id.bazsil);
        }
    }
}
