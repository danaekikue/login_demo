package com.danaekikue.login_demo.Login.Magazines.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView date = itemView.findViewById(R.id.magazine_title);
        date.setText(data.getTitle());

        ImageView imageView = itemView.findViewById(R.id.magazine_cover);
        //Http not showing image, replace with http as you get the image url
        Picasso.get().load(data.getImg_url().replace("http", "https")).into(imageView);
    }

}
