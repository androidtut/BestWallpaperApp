package com.example.bestwallpaperapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestwallpaperapp.FullImage;
import com.example.bestwallpaperapp.Models.WallpaperModels;
import com.example.bestwallpaperapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WallpaperAdapters extends RecyclerView.Adapter<WallpaperAdapters.ViewHolder> {
    ArrayList<WallpaperModels>list;
    Context context;

    public WallpaperAdapters(ArrayList<WallpaperModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_wallpaper,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      final WallpaperModels models = list.get(position);
//      holder.image.setImageResource(models.getImage());

        Picasso.get()
                .load(models.getImage())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), FullImage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image",models.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
