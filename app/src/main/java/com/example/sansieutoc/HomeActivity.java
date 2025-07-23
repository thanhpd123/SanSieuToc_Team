package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.DataHelper.DataSample;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private int userId = -1;
    private String userRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userRole = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                .getString("role", "user"); // default role = user

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // ✅ Thay đổi menu nếu role != user
        if (userRole.equals("FieldOwner")) {
            MenuItem shopItem = bottomNavigationView.getMenu().findItem(R.id.nav_shop);
            shopItem.setTitle("CRUD Sân");
            shopItem.setIcon(R.drawable.ic_edit); // thay icon CRUD sân thực tế
        }
        userId = getIntent().getIntExtra("user_id", -1);
        if (userId == -1) {
            Toast.makeText(this, "Không tìm thấy user", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
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
                if (!userRole.equals("FieldOwner")) {
                    Toast.makeText(this, "Mua sắm", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(this, ShopActivity.class)); // nếu có
                } else {
                    Toast.makeText(this, "CRUD Sân", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, CrudFieldActivity.class));
                }
                return true;
            } else if (id == R.id.nav_notifications) {
                Toast.makeText(this, "Thông báo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Cá Nhân", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("user_id", userId);  // Truyền userId sang personnal info activity
                startActivity(intent);
                return true;
            }
            return false;
        });

    }
}
