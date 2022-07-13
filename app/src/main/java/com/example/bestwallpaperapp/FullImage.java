package com.example.bestwallpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class FullImage extends AppCompatActivity {
ImageView image;
Button apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        image = findViewById(R.id.image);
        apply = findViewById(R.id.apply);

        String images = getIntent().getStringExtra("image");
        Picasso.get().load(images).into(image);

        apply.setOnClickListener(v->{
           Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
           WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
            try {
                wallpaperManager.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Wallpaper set", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}