package com.example.sansieutoc.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Entity.Field;
import com.example.sansieutoc.R;

public class FieldViewHolder extends RecyclerView.ViewHolder {

    TextView txtName, txtAddress, txtPrice;
    ImageButton btnUpdate, btnDelete;
    ImageView ìgView;

    public FieldViewHolder(View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.text_view_name);
        txtAddress = itemView.findViewById(R.id.text_view_address);
        txtPrice = itemView.findViewById(R.id.text_view_price);
        btnUpdate = itemView.findViewById(R.id.btnUpdate);
        btnDelete = itemView.findViewById(R.id.btnDelete);
        ìgView = itemView.findViewById(R.id.image_view_field);
    }

    public void bind(Field field) {
        txtName.setText(field.name);
        txtAddress.setText(field.address);
        txtPrice.setText("Giá: " + field.pricePerHour + "đ/giờ");

        int resId = itemView.getContext().getResources()
                .getIdentifier(field.images, "drawable", itemView.getContext().getPackageName());
        ìgView.setImageResource(resId);
    }
}
