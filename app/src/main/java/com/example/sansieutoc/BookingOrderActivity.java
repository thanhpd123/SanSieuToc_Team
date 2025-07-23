// app/src/main/java/com/example/sansieutoc/BookingOrderActivity.java
package com.example.sansieutoc;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Adapter.TimeSlotAdapter;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.Booking;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BookingOrderActivity extends AppCompatActivity {

    private String selectedDate;
    private String selectedTimeSlot;
    private String fieldId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_order);

        Button btnSelectDate = findViewById(R.id.btnSelectDate);
        Button btnDatSanNgay = findViewById(R.id.btnDatSanNgay);
        RecyclerView rvTimeSlots = findViewById(R.id.rvTimeSlots);
        ImageView tvBack = findViewById(R.id.tvBack);

        Intent intent = getIntent();
        fieldId = intent.getStringExtra("fieldId");

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        List<String> timeSlots = Arrays.asList("08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00", "6h00 - 8h00");
        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(timeSlots, slot -> {
            selectedTimeSlot = slot;
            Toast.makeText(this, "Bạn đã chọn: " + slot, Toast.LENGTH_SHORT).show();
        });
        rvTimeSlots.setLayoutManager(new LinearLayoutManager(this));
        rvTimeSlots.setAdapter(timeSlotAdapter);

        btnSelectDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                btnSelectDate.setText(selectedDate);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnDatSanNgay.setOnClickListener(v -> {
            if (selectedDate == null || selectedTimeSlot == null) {
                Toast.makeText(this, "Vui lòng điền toàn bộ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            Booking booking = new Booking();
            booking.fieldId = fieldId;
            booking.date = selectedDate;
            booking.startTime = selectedTimeSlot.split(" - ")[0];
            new Thread(() -> {
                db.bookingDao().insert(booking);
                runOnUiThread(() -> Toast.makeText(this, "Đặt sân thành công!", Toast.LENGTH_SHORT).show());
            }).start();
        });

        tvBack.setOnClickListener(v -> onBackPressed());
    }
}