package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_booking);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_booking) {
                Toast.makeText(this, "Đặt sân", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, BookingActivity.class));
                return true;
            } else if (id == R.id.nav_shop) {
                Toast.makeText(this, "Mua sắm", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_notifications) {
                Toast.makeText(this, "Thông báo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Cá Nhân", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
        LinearLayout layoutStadium1 = findViewById(R.id.layoutStadium1);

        layoutStadium1.setOnClickListener(v -> {
            Intent intent = new Intent(BookingActivity.this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân bóng đá KTX Đại học FPT");
            intent.putExtra("time", "05:00 - 22:00");
            intent.putExtra("price", "100.000đ - 300.000đ/giờ");
            intent.putExtra("imageRes", R.drawable.d3);
            startActivity(intent);
        });

        LinearLayout layoutStadium2 = findViewById(R.id.layoutStadium2);

        layoutStadium2.setOnClickListener(v -> {
            Intent intent = new Intent(BookingActivity.this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân bóng đá KTX Đại học FPT");
            intent.putExtra("time", "05:00 - 22:00");
            intent.putExtra("price", "100.000đ - 300.000đ/giờ");
            intent.putExtra("imageRes", R.drawable.d3);
            startActivity(intent);
        });

        LinearLayout layoutStadium3 = findViewById(R.id.layoutStadium3);

        layoutStadium3.setOnClickListener(v -> {
            Intent intent = new Intent(BookingActivity.this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân bóng đá KTX Đại học FPT");
            intent.putExtra("time", "05:00 - 22:00");
            intent.putExtra("price", "100.000đ - 300.000đ/giờ");
            intent.putExtra("imageRes", R.drawable.d3);
            startActivity(intent);
        });

        LinearLayout layoutStadium4 = findViewById(R.id.layoutStadium4);

        layoutStadium4.setOnClickListener(v -> {
            Intent intent = new Intent(BookingActivity.this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân bóng đá KTX Đại học FPT");
            intent.putExtra("time", "05:00 - 22:00");
            intent.putExtra("price", "100.000đ - 300.000đ/giờ");
            intent.putExtra("imageRes", R.drawable.d3);
            startActivity(intent);
        });
    }
}
