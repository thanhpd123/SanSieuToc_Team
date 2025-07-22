package com.example.sansieutoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.User;

import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class activity_personal_info extends AppCompatActivity {

    private User user;
    private int userId = -1;

    private EditText etName, etPhone, etEmail, etAddress;
    private TextView tvRole, tvName, tvPhone;  // tvName và tvPhone ở header
    private Button btnSave, btnBack;
    private ImageButton btnEditAvatar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        // Ánh xạ views
        tvName = findViewById(R.id.tv_name);
        tvPhone = findViewById(R.id.tv_phone);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        btnSave = findViewById(R.id.btn_save);
        btnEditAvatar = findViewById(R.id.btn_edit_avatar);
        btnBack = findViewById(R.id.btn_back);

        // Lấy userId từ Intent
        userId = getIntent().getIntExtra("user_id", -1);
        if (userId == -1) {
            Toast.makeText(this, "Không tìm thấy user", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Load user từ Room (async)
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            var users = db.userDao().getAll();  // Giả sử bạn có method getAll trong UserDao
            user = db.userDao().getById(userId);  // Giả sử bạn có method getUserById trong UserDao
            runOnUiThread(() -> {
                if (user != null) {
                    bindDataToViews();
                } else {
                    Toast.makeText(activity_personal_info.this, "Không tìm thấy user", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        });

        // Xử lý nút Lưu thay đổi
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    updateUserData();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Quay về màn hình trước
            }
        });
    }

    // Bind dữ liệu user vào views
    private void bindDataToViews() {
        tvName.setText(user.name);
        tvPhone.setText(user.phone);
        etName.setText(user.name);
        etPhone.setText(user.phone);
        etEmail.setText(user.email);
        etAddress.setText(user.address);

        // TODO: Load avatar nếu có (ví dụ: imgAvatar.setImageURI(Uri.parse(user.avatarUrl)));
    }

    // Kiểm tra dữ liệu trước khi save
    private boolean validateInputs() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.isEmpty() || !Pattern.matches("\\d{10,11}", phone)) {  // Kiểm tra phone 10-11 số
            Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Update dữ liệu và lưu vào Room
    private void updateUserData() {
        user.name = etName.getText().toString().trim();
        user.phone = etPhone.getText().toString().trim();
        user.email = etEmail.getText().toString().trim();
        user.address = etAddress.getText().toString().trim();
        user.updatedAt = String.valueOf(System.currentTimeMillis());  // Cập nhật thời gian

        // Update async vào Room
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(activity_personal_info.this);
            db.userDao().update(user);
            runOnUiThread(() -> {
                bindDataToViews();
                Toast.makeText(activity_personal_info.this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show();
                // Quay về màn hình trước nếu cần: finish();
            });
        });
    }
}
