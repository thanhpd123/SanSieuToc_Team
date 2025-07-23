package com.example.sansieutoc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.Booking;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private LinearLayout layoutNotificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        layoutNotificationList = findViewById(R.id.layoutNotificationList);

        loadBookingNotifications();

        // BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_notifications);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_booking) {
                startActivity(new Intent(this, BookingActivity.class));
                return true;
            } else if (id == R.id.nav_shop) {
                startActivity(new Intent(this, ShopActivity.class));
                return true;
            } else if (id == R.id.nav_notifications) {
                return true; // Đang ở đây
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
    }

    private void loadBookingNotifications() {
        new Thread(() -> {
            List<Booking> bookingList = AppDatabase.getInstance(this).bookingDao().getAll();

            runOnUiThread(() -> {
                LayoutInflater inflater = LayoutInflater.from(this);
                layoutNotificationList.removeAllViews();

                for (Booking booking : bookingList) {
                    View view = inflater.inflate(R.layout.item_booking_notification, layoutNotificationList, false);

                    TextView txtTitle = view.findViewById(R.id.txtTitle);
                    TextView txtTime = view.findViewById(R.id.txtTime);
                    Button btnStatus = view.findViewById(R.id.btnStatus);
                    TextView txtCreatedAt = view.findViewById(R.id.txtCreatedAt);

                    boolean isCancelled = booking.status != null && booking.status.equalsIgnoreCase("cancelled");

                    txtTitle.setText("Bạn đã " + (isCancelled ? "huỷ đặt" : "đặt") + " sân thành công");
                    txtTime.setText("Sân " + booking.fieldUnitId + " - Lúc " +
                            booking.startTime + " - " + booking.endTime + ", Ngày " + booking.date);
                    btnStatus.setText(isCancelled ? "Đã huỷ" : "Đã đặt");
                    btnStatus.setBackgroundTintList(getColorStateList(isCancelled ? android.R.color.holo_red_dark : android.R.color.holo_green_dark));
                    txtCreatedAt.setText(booking.createdAt);

                    layoutNotificationList.addView(view);
                }
            });
        }).start();
    }
}
