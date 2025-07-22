package com.example.sansieutoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Entity.Field;
import com.example.sansieutoc.R;

import java.util.List;

public class StadiumAdapter extends RecyclerView.Adapter<StadiumAdapter.StadiumViewHolder> {
    private final List<Field> stadiumList;
    private final Context context;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Field field);
    }

    public StadiumAdapter(Context context, List<Field> stadiumList, OnItemClickListener listener) {
        this.context = context;
        this.stadiumList = stadiumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StadiumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stadium, parent, false);
        return new StadiumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StadiumViewHolder holder, int position) {
        Field field = stadiumList.get(position);
        holder.tvName.setText(field.name);
        holder.tvTime.setText("Mở cửa: " + field.availableTimes);
        holder.tvPrice.setText("Giá: " + field.pricePerHour + "đ/giờ");
        holder.tvStadiumAddress.setText("Địa chỉ: " + field.address);
        int resId = context.getResources().getIdentifier(field.images, "drawable", context.getPackageName());
        holder.ivImage.setImageResource(resId);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(field));
    }

    @Override
    public int getItemCount() {
        return stadiumList.size();
    }

    static class StadiumViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvTime, tvPrice, tvStadiumAddress;
        ImageView ivImage;
        StadiumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStadiumName);
            tvTime = itemView.findViewById(R.id.tvStadiumTime);
            tvPrice = itemView.findViewById(R.id.tvStadiumPrice);
            ivImage = itemView.findViewById(R.id.ivStadiumImage);
            tvStadiumAddress = itemView.findViewById(R.id.tvStadiumAddress);
        }
    }
}
