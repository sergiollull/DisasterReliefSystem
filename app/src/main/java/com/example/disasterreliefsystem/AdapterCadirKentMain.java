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

public class AdapterCadirKentMain extends RecyclerView.Adapter<AdapterCadirKentMain.MyViewHolder> {

    Context context;
    ArrayList<CadirKentBilgileri> list;



    public AdapterCadirKentMain(Context context, ArrayList<CadirKentBilgileri> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cadir_kent_list,parent,false);
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
