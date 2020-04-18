package  com.lec.android.a008_practice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    static ProfileAdapter adapter;
    //profile을 담을 리스트
    List<Profile> items = new ArrayList<Profile>();

    public ProfileAdapter() {this.adapter = this;
    }//기본 생성자

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());

        View itemView = inf.inflate(R.layout.item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        Profile item = items.get(position);     //arraylist의 함수 get
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddr, tvAge;
        ImageButton btnDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddr = itemView.findViewById(R.id.tvAddr);
            tvAge = itemView.findViewById(R.id.tvAge);
            btnDel = itemView.findViewById(R.id.btnDel);
            // VIEW 객체 가져오기

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.removeItem(getAdapterPosition()); //==> 데이터 삭제
                    adapter.notifyDataSetChanged(); // 이거한번 이면 가능 꼭해줘야함******8

                }

            });
        }
            public void setItem(Profile item){

                tvName.setText(item.getName().toString());
                tvAddr.setText(item.getAddr().toString());
                tvAge.setText(String.format("%d",item.getAge()));
            }


    }//end ViewHolder
    public void addItem(Profile item) {  items.add(item); }
    public void addItem(int position, Profile item) {   items.add(position, item);}
    public void setItems(ArrayList<Profile> items) {   this.items = items;}
    public Profile getItem(int position) {   return items.get(position);}
    public void setItem(int position, Profile item) {   items.set(position, item); }
    public void removeItem(int position){ items.remove(position); }

}