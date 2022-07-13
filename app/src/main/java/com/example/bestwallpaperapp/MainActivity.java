package com.example.bestwallpaperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bestwallpaperapp.Adapters.WallpaperAdapters;
import com.example.bestwallpaperapp.Models.WallpaperModels;
import com.example.bestwallpaperapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<WallpaperModels>list;
    private WallpaperAdapters adapters;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("images");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait While Loading...");
        progressDialog.show();




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();

                list = new ArrayList<>();

                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    WallpaperModels models = snapshot1.getValue(WallpaperModels.class);
                    list.add(models);
                }

                adapters = new WallpaperAdapters(list,getApplicationContext());
                binding.wallpaperRecycler.setAdapter(adapters);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                binding.wallpaperRecycler.setLayoutManager(gridLayoutManager);

                adapters.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}