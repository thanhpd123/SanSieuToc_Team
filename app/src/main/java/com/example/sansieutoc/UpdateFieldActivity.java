package com.example.sansieutoc;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.Field;

import java.util.concurrent.Executors;

public class UpdateFieldActivity extends AppCompatActivity {

    private EditText edtName, edtAddress, edtPrice, edtDescription, edtAvailableTimes;
    private Button btnSave;

    private Field currentField;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_field);

        db = AppDatabase.getInstance(this);

        edtName = findViewById(R.id.edtFieldName);
        edtAddress = findViewById(R.id.edtFieldAddress);
        edtPrice = findViewById(R.id.edtFieldPrice);
        edtDescription = findViewById(R.id.edtFieldDescription);
        edtAvailableTimes = findViewById(R.id.edtFieldAvailableTimes);
        btnSave = findViewById(R.id.btnSaveField);

        // Nhận dữ liệu từ Intent
        int fieldId = getIntent().getIntExtra("field_id", -1);
        if (fieldId == -1) {
            Toast.makeText(this, "Không tìm thấy thông tin sân", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Load dữ liệu field từ DB theo id
        Executors.newSingleThreadExecutor().execute(() -> {
            currentField = db.fieldDao().getById(fieldId);
            runOnUiThread(() -> {
                if (currentField != null) {
                    edtName.setText(currentField.name);
                    edtAddress.setText(currentField.address);
                    edtPrice.setText(String.valueOf(currentField.pricePerHour));
                    edtDescription.setText(currentField.description);
                    edtAvailableTimes.setText(currentField.availableTimes);
                } else {
                    Toast.makeText(this, "Không tìm thấy sân", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        });

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();
            String priceStr = edtPrice.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();
            String availableTimes = edtAvailableTimes.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(priceStr)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            int price = Integer.parseInt(priceStr);

            // Cập nhật dữ liệu field
            currentField.name = name;
            currentField.address = address;
            currentField.pricePerHour = price;
            currentField.description = description;
            currentField.availableTimes = availableTimes;

            Executors.newSingleThreadExecutor().execute(() -> {
                db.fieldDao().update(currentField);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Cập nhật sân thành công", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });
    }
}
