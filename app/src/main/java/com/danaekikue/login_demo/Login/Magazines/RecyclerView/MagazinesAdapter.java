package com.danaekikue.login_demo.Login.Magazines.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danaekikue.login_demo.Login.Magazines.MagazineModel;
import com.danaekikue.login_demo.R;

import java.util.ArrayList;
import java.util.List;

public class MagazinesAdapter extends RecyclerView.Adapter <MagazinesViewHolder> {

    private List<MagazineModel> magazines;

    public MagazinesAdapter(List<MagazineModel> magazines){ this.magazines = magazines; }

    @NonNull
    @Override
    public MagazinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_magazine_item, parent, false);
        return new MagazinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MagazinesViewHolder holder, int position) {

        MagazineModel data = magazines.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return magazines.size();
    }





}
