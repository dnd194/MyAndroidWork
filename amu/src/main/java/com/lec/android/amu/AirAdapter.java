package com.lec.android.amu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AirAdapter extends RecyclerView.Adapter<AirAdapter.ViewHolder> {
    static AirAdapter adapter;

    List<Air> items = new ArrayList<>();

    public AirAdapter() {this.adapter = this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View itemView = inf.inflate(R.layout.item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AirAdapter.ViewHolder holder, int position) {
        Air item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }//end getItemCount

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivGu;
        TextView tvDust, tvFinedDust;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivGu = itemView.findViewById(R.id.ivGu);
            tvDust =itemView.findViewById(R.id.tvDust);
            tvFinedDust =itemView.findViewById(R.id.tvFineDust);
        }

        public void setItem(Air item){
            ivGu.setImageResource(item.getPhoto());
            tvDust.setText(item.getDust());
            tvFinedDust.setText(item.getFineDust());
        }

    }//end ViewHolder
    public void addItem(Air item) {  items.add(item); }
    public void addItem(int position, Air item) {   items.add(position, item);}
    public void setItems(ArrayList<Air> items) {   this.items = items;}
    public Air getItem(int position) {   return items.get(position);}
    public void setItem(int position, Air item) {   items.set(position, item); }
    public void removeItem(int position){ items.remove(position); }


}
