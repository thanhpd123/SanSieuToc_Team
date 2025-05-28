package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StadiumDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium_detail);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String time = intent.getStringExtra("time");
        String price = intent.getStringExtra("price");
        int imageRes = intent.getIntExtra("imageRes", R.drawable.d3); // fallback ảnh

        // Gán dữ liệu lên UI
        TextView tvName = findViewById(R.id.tvStadiumName);
        TextView tvTime = findViewById(R.id.tvTime);
        TextView tvPrice = findViewById(R.id.tvPrice);
        ImageView ivImage = findViewById(R.id.ivStadiumImage);

        tvName.setText(name);
        tvTime.setText("⏰ Mở cửa: " + time);
        tvPrice.setText("Chỉ từ " + price);
        ivImage.setImageResource(imageRes);

        // Xử lý nút quay lại
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> onBackPressed());

        Button btnDatSanNgay = findViewById(R.id.btnBookNow);
        btnDatSanNgay.setOnClickListener(v -> {
            Intent intent2 = new Intent(StadiumDetailActivity.this, BookingOrderActivity.class);

            // (Tùy chọn) Nếu bạn muốn truyền thêm thông tin đặt sân:
            intent2.putExtra("selectedDate", "Hôm nay - Thứ 7");
            intent2.putExtra("fieldType", "Sân 5x5");
            intent2.putExtra("timeSlot", "6h00 - 8h00");

            startActivity(intent2);
        });
    }
}
