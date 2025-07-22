package com.example.sansieutoc.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.R;

import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {
    public interface OnTimeSlotSelected {
        void onSelected(String timeSlot);
    }

    private final List<String> timeSlots;
    private final OnTimeSlotSelected listener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public TimeSlotAdapter(List<String> timeSlots, OnTimeSlotSelected listener) {
        this.timeSlots = timeSlots;
        this.listener = listener;
    }
    public void setSelectedPosition(int pos) {
        int oldPos = selectedPosition;
        selectedPosition = pos;
        if (oldPos != RecyclerView.NO_POSITION) notifyItemChanged(oldPos);
        notifyItemChanged(selectedPosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_time_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String slot = timeSlots.get(position);
        holder.tvTime.setText(slot);
        holder.itemView.setSelected(selectedPosition == position);
        holder.itemView.setOnClickListener(v -> {
            int oldPos = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(oldPos);
            notifyItemChanged(selectedPosition);
            listener.onSelected(slot);
        });
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTimeSlot);
        }
    }
}