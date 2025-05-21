package com.example.sansieutoc;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_booking) {
                Toast.makeText(this, "Đặt sân", Toast.LENGTH_SHORT).show();
                return true;
            } /*else if (id == R.id.nav_coach) {
                Toast.makeText(this, "HLV", Toast.LENGTH_SHORT).show();
                return true;
            } */else if (id == R.id.nav_shop) {
                Toast.makeText(this, "Mua sắm", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_notifications) {
                Toast.makeText(this, "Thông báo", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Cá nhân", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

    }
}
