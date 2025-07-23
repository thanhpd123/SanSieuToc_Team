package com.example.sansieutoc;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.CurrentUser;
import com.example.sansieutoc.Entity.Field;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;

public class AddFieldActivity extends AppCompatActivity {

    EditText edtRemoteId, edtName, edtAddress, edtDescription, edtPrice, edtImages, edtAvailableTimes;
    Button btnAddField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_field);

        edtRemoteId = findViewById(R.id.edtRemoteId);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtDescription = findViewById(R.id.edtDescription);
        edtPrice = findViewById(R.id.edtPrice);
        edtImages = findViewById(R.id.edtImages);
        edtAvailableTimes = findViewById(R.id.edtAvailableTimes);
        btnAddField = findViewById(R.id.btnAddField);

        AppDatabase db = AppDatabase.getInstance(this);

        btnAddField.setOnClickListener(v -> {
            String remoteId = edtRemoteId.getText().toString().trim();
            String name = edtName.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();
            String priceStr = edtPrice.getText().toString().trim();
            String images = edtImages.getText().toString().trim();
            String availableTimes = edtAvailableTimes.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(priceStr)) {
                Toast.makeText(this, "Vui lòng nhập tên sân, địa chỉ, giá mỗi giờ", Toast.LENGTH_SHORT).show();
                return;
            }

            int price = Integer.parseInt(priceStr);

            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

            Field field = new Field();
            field.remoteId = remoteId;
            field.ownerId = String.valueOf(CurrentUser.user.id); // ✅ lấy id user đang login
            field.name = name;
            field.address = address;
            field.description = description;
            field.pricePerHour = price;
            field.images = images;
            field.availableTimes = availableTimes;
            field.createdAt = currentTime; // ✅ thời gian hiện tại
            field.updatedAt = currentTime; // ✅ thời gian hiện tại

            Executors.newSingleThreadExecutor().execute(() -> {
                db.fieldDao().insert(field);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Thêm sân thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Quay về CrudFieldActivity
                });
            });
        });
    }
}
