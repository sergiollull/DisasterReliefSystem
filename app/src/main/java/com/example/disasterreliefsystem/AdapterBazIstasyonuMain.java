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

public class AdapterBazIstasyonuMain extends RecyclerView.Adapter<AdapterBazIstasyonuMain.MyViewHolder> {

    Context context;
    ArrayList<BazBilgileri> list;



    public AdapterBazIstasyonuMain(Context context, ArrayList<BazBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.baz_istasyonu_list,parent,false);
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
