package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private int userId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userId = getIntent().getIntExtra("user_id", -1);
        if (userId == -1) {
            Toast.makeText(this, "Không tìm thấy thông tin user. Vui lòng đăng nhập lại.", Toast.LENGTH_SHORT).show();
            // Quay về LoginActivity nếu cần
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        LinearLayout menuPersonalInfo = findViewById(R.id.menu_personal_info);
        menuPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý chuyển trang: Start PersonalInfoActivity và truyền userId
                Intent intent = new Intent(ProfileActivity.this, activity_personal_info.class);
                intent.putExtra("user_id", userId);  // Truyền dữ liệu nếu cần
                startActivity(intent);

                // Tùy chọn: Thêm Toast để test
                Toast.makeText(ProfileActivity.this, "Chuyển sang Thông tin cá nhân", Toast.LENGTH_SHORT).show();
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // Chọn đúng nút "Cá nhân"
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

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
    }
}
