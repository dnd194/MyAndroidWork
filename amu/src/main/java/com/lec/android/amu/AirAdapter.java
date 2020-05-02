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
    }//기본생성자

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
        TextView tvDust, tvFinedDust, tvRegion , tvStatue;
        ImageView ivStatue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivGu = itemView.findViewById(R.id.ivGu);
            tvDust =itemView.findViewById(R.id.tvDust);
            tvFinedDust =itemView.findViewById(R.id.tvFineDust);
            tvRegion = itemView.findViewById(R.id.tvRegion);
            ivStatue = itemView.findViewById(R.id.ivStatue);   //상태이미지
            tvStatue = itemView.findViewById(R.id.tvStatue);   //상태텍스트
        }

        public void setItem(Air item){
            ivGu.setImageResource(item.getPhoto());
            tvDust.setText(item.getDust());
            tvFinedDust.setText(item.getFineDust());
            tvRegion.setText(item.getRegion());

            String imageMatch= tvRegion.getText().toString().trim();

            switch (imageMatch){
                case "서초구":
                    ivGu.setImageResource(R.drawable.seocho);
                    break;
                case "광진구":
                    ivGu.setImageResource(R.drawable.gwangjin);
                    break;
                case "동대문구":
                    ivGu.setImageResource(R.drawable.dongdeamun);
                    break;
                case "성동구":
                    ivGu.setImageResource(R.drawable.sungdonggu);
                    break;
                case "성북구":
                    ivGu.setImageResource(R.drawable.sungbukgu);
                    break;
                case "용산구":
                    ivGu.setImageResource(R.drawable.yongsan);
                    break;
                case "강북구":
                    ivGu.setImageResource(R.drawable.kangbuk);
                    break;
                case "중구":
                    ivGu.setImageResource(R.drawable.junggu);
                    break;
                case "중랑구":
                    ivGu.setImageResource(R.drawable.jungranggu);
                    break;
                case "종로구":
                    ivGu.setImageResource(R.drawable.jongrogu);
                    break;
            }//end switch

            if(Integer.parseInt(tvDust.getText().toString().trim()) >= 90){
                ivStatue.setImageResource(R.drawable.ic_tired_regular);
                tvStatue.setText("진촤위험해욧");
            }else if(Integer.parseInt(tvDust.getText().toString().trim()) >= 70){
                ivStatue.setImageResource(R.drawable.ic_sad_tear_regular);
                tvStatue.setText("조심해욧");
            }else if(Integer.parseInt(tvDust.getText().toString().trim()) >= 50){
                ivStatue.setImageResource(R.drawable.ic_meh_regular);
                tvStatue.setText("나갈만해욧");
            }else {
                ivStatue.setImageResource(R.drawable.ic_smile_wink_regular);
                tvStatue.setText("진촤 좋당");
            }
        }//end setItem

    }//end ViewHolder
    public void addItem(Air item) {  items.add(item); }
    public void addItem(int position, Air item) {   items.add(position, item);}
    public void setItems(ArrayList<Air> items) {   this.items = items;}
    public Air getItem(int position) {   return items.get(position);}
    public void setItem(int position, Air item) {   items.set(position, item); }




}//end activity
