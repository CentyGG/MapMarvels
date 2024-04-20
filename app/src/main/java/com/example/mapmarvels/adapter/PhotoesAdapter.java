package com.example.mapmarvels.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapmarvels.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoesAdapter extends RecyclerView.Adapter<PhotoesAdapter.PhotoViewHolder> {
    Context context;
    ArrayList<String> photoesArrayList;

    public PhotoesAdapter(Context context, ArrayList<String> photoesArrayList){
        this.context = context;
        this.photoesArrayList =photoesArrayList;
    }
    @NonNull
    @Override
    public PhotoesAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photoes_for_recycler,parent,false);
        return new PhotoesAdapter.PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoesAdapter.PhotoViewHolder holder, int position) {
        String url_photo =photoesArrayList.get(position);
        Picasso.get().load(url_photo).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return photoesArrayList.size();
    }
    public static  class PhotoViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.image_in_card);
        }
    }
}
