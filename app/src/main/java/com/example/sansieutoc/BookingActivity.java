package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Adapter.StadiumAdapter;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.DataHelper.DataSample;
import com.example.sansieutoc.Entity.Field;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class BookingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo database (singleton)
        AppDatabase db = AppDatabase.getInstance(this);

        // Insert dữ liệu mẫu (chỉ nên gọi khi lần đầu chạy app hoặc debug, gọi nhiều lần sẽ có dữ liệu trùng)
        DataSample.insertSampleData(db);

        setContentView(R.layout.activity_booking);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStadiums);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        new Thread(() -> {
            List<Field> stadiums = db.fieldDao().getAll();
            runOnUiThread(() -> {
                StadiumAdapter adapter = new StadiumAdapter(this, stadiums, field -> {
                    // Handle item click, e.g., open detail
                });
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}
