package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.DataHelper.DataSample;
import com.example.sansieutoc.Entity.Field;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Khởi tạo database (singleton)
        db = AppDatabase.getInstance(this);
        // Insert dữ liệu mẫu
        DataSample.insertSampleData(db);

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

        findViewById(R.id.rdnyndtuy0u9).setOnClickListener(v -> {
            Intent intent = new Intent(this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân Cầu Lông Kim Bông");
            intent.putExtra("time", "08:00 - 22:00");
            intent.putExtra("price", "50,000đ/giờ");
            intent.putExtra("address", "Xã Tân Xã, Huyện Thạch Thất, Hà Nội");
            intent.putExtra("imageRes", R.drawable.san_kim_bong);
            startActivity(intent);
        });

        findViewById(R.id.reol4m9a1gnc).setOnClickListener(v -> {
            Intent intent = new Intent(this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân cỏ nhân tạo Đại Dương");
            intent.putExtra("time", "05:00 - 22:00");
            intent.putExtra("price", "550,000đ/giờ");
            intent.putExtra("address", "Cổng làng thôn 2, Xã Tân Xã, Thạch Thất, Hà Nội");
            intent.putExtra("imageRes", R.drawable.san_dai_duong);
            startActivity(intent);
        });

        findViewById(R.id.rndgfcvknhb).setOnClickListener(v -> {
            Intent intent = new Intent(this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân bóng Đại học FPT Hà Nội");
            intent.putExtra("time", "05:00 - 22:00");
            intent.putExtra("price", "500,000đ/giờ");
            intent.putExtra("address", "Thạch Hoà, Thạch Thất, Hà Nội");
            intent.putExtra("imageRes", R.drawable.san_fpt);
            startActivity(intent);
        });

        findViewById(R.id.rvs8di1y63yg).setOnClickListener(v -> {
            Intent intent = new Intent(this, StadiumDetailActivity.class);
            intent.putExtra("name", "Sân bóng rổ đại học Quốc gia Hoà Lạc");
            intent.putExtra("time", "08:00 - 22:00");
            intent.putExtra("price", "300,000đ/giờ");
            intent.putExtra("address", "Thạch Hoà, Thạch Thất, Hà Nội");
            intent.putExtra("imageRes", R.drawable.san_bong_ro_quoc_gia);
            startActivity(intent);
        });
    }
}
