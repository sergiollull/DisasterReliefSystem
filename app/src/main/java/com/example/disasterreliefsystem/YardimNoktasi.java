package com.example.disasterreliefsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class YardimNoktasi extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener {
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng userLatLong;

    Location currentLocation;
    DatabaseReference mYardimNoktasi;
    Marker marker;
    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;
    FusedLocationProviderClient fusedClient;
    private static final int REQUEST_CODE = 101;
    GoogleMap gMap;
    FrameLayout map;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yardim_noktasi);
        map = findViewById(R.id.map);
        ChildEventListener childEventListener;

        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
        mYardimNoktasi = FirebaseDatabase.getInstance().getReference("yardimnoktasi");
        mYardimNoktasi.push().setValue(marker);
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(YardimNoktasi.this);
                }
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        GetLocation getLocation = new GetLocation();
        getLocation.setLatitude(currentLocation.getLatitude());
        getLocation.setLongitude(currentLocation.getLongitude());
        Log.d("latitude", String.valueOf(currentLocation.getLatitude()));
        Log.d("longitude", String.valueOf(currentLocation.getLongitude()));

        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Konumunuz");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        googleMap.addMarker(markerOptions);
        googleMap.setOnMarkerClickListener(this);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        long startTime = System.currentTimeMillis();

        mYardimNoktasi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    YardimNoktasiBilgileri yardimNoktasiBilgileri= s.getValue(YardimNoktasiBilgileri.class);
                    LatLng location = new LatLng(yardimNoktasiBilgileri.enlem, yardimNoktasiBilgileri.boylam);
                    MarkerOptions markerOptions1 = new MarkerOptions().position(location).title("İçerik: "+yardimNoktasiBilgileri.getIcerik()+", "+ "Çalışma Saati: " + yardimNoktasiBilgileri.getSaat());
                    markerOptions1.icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.yardimnoktasiharita));
                    googleMap.addMarker(markerOptions1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        long stopTime = System.currentTimeMillis();
        long responseTime = stopTime - startTime;

        System.out.println("Firebase response time: " + responseTime + " milliseconds");
    }
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);



    }
}