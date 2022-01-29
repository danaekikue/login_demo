package com.danaekikue.login_demo.Login.Magazines.RecyclerView;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danaekikue.login_demo.Login.Magazines.MagazineModel;
import com.danaekikue.login_demo.R;

import com.squareup.picasso.Picasso;

public class MagazinesViewHolder extends RecyclerView.ViewHolder{

    public MagazinesViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(MagazineModel data) {
        ImageView imageView = itemView.findViewById(R.id.magazine_cover);
        Picasso.get().load(data.getImg_url()).into(imageView);
    }

}
