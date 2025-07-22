package com.example.sansieutoc.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Entity.FieldType;
import com.example.sansieutoc.R;

import java.util.List;

public class FieldTypeAdapter extends RecyclerView.Adapter<FieldTypeAdapter.ViewHolder> {
    public interface OnFieldTypeSelected {
        void onSelected(FieldType fieldType);
    }

    private final List<FieldType> fieldTypes;
    private final OnFieldTypeSelected listener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public FieldTypeAdapter(List<FieldType> fieldTypes, OnFieldTypeSelected listener) {
        this.fieldTypes = fieldTypes;
        this.listener = listener;
    }

    public void setSelectedPosition(int pos) {
        int oldPos = selectedPosition;
        selectedPosition = pos;
        if (oldPos != RecyclerView.NO_POSITION) {
            notifyItemChanged(oldPos);
        }
        notifyItemChanged(selectedPosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_field_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FieldType fieldType = fieldTypes.get(position);
        holder.tvName.setText(fieldType.name);
        holder.itemView.setSelected(selectedPosition == position);
        holder.itemView.setOnClickListener(v -> {
            int oldPos = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(oldPos);
            notifyItemChanged(selectedPosition);
            listener.onSelected(fieldType);
        });
    }

    @Override
    public int getItemCount() {
        return fieldTypes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFieldTypeName);
        }
    }
}