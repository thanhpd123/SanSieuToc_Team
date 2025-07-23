package com.example.sansieutoc.Adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Entity.Booking;
import com.example.sansieutoc.R;

import java.util.List;

public class BookingNotificationAdapter extends RecyclerView.Adapter<BookingNotificationAdapter.ViewHolder> {

    private List<Booking> bookings;

    public BookingNotificationAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookings.get(position);

        boolean isCancelled = "cancelled".equalsIgnoreCase(booking.status);
        String statusText = isCancelled ? "Đã huỷ" : "Đã đặt";
        int statusColor = isCancelled ? Color.RED : Color.parseColor("#4CAF50");

        holder.txtTitle.setText("Bạn đã " + (isCancelled ? "huỷ đặt" : "đặt") + " Sân bóng KTX Đại học FPT");

        holder.txtTime.setText("Sân " + booking.fieldUnitId +
                " - Lúc " + booking.startTime + " - " + booking.endTime +
                ", Ngày " + booking.date);

        holder.btnStatus.setText(statusText);
        holder.btnStatus.setBackgroundTintList(ColorStateList.valueOf(statusColor));
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtTime;
        Button btnStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtTime = itemView.findViewById(R.id.txtTime);
            btnStatus = itemView.findViewById(R.id.btnStatus);
        }
    }
}
