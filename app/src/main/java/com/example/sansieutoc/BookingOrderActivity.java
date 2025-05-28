package com.example.sansieutoc;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_order); // layout đã tạo sẵn

        // Nhận dữ liệu từ StadiumDetailActivity (nếu có)
        String date = getIntent().getStringExtra("selectedDate");
        String type = getIntent().getStringExtra("fieldType");
        String time = getIntent().getStringExtra("timeSlot");

        // Ví dụ: tìm TextView và gán lại nếu cần
        // TextView tvSummary = findViewById(R.id.tvSummary);
        // tvSummary.setText(date + " - " + type + " - " + time);

        ImageView tvBack = findViewById(R.id.tvBack);
        tvBack.setOnClickListener(v -> onBackPressed()); // hoặc finish();
    }
}
