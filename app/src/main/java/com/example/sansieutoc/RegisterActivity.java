package com.example.sansieutoc;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtFullName, edtBirthdate, edtGender, edtAddress, edtPhone, edtPassword, edtConfirmPassword;
    private Button btnRegister;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ view
        edtFullName = findViewById(R.id.edtFullName);
        edtBirthdate = findViewById(R.id.edtBirthdate);
        edtGender = findViewById(R.id.edtGender);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Khởi tạo database
        db = AppDatabase.getInstance(this);

        btnRegister.setOnClickListener(v -> handleRegister());
    }

    private void handleRegister() {
        String fullName = edtFullName.getText().toString().trim();
        String birthdate = edtBirthdate.getText().toString().trim();
        String gender = edtGender.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();

        // Kiểm tra dữ liệu
        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(birthdate) ||
                TextUtils.isEmpty(gender) || TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo user mới
        User user = new User();
        user.name = fullName;
        user.phone = phone;
        user.address = address;
        user.passwordHash = password;
        user.role = "user";
        user.createdAt = String.valueOf(System.currentTimeMillis());
        user.updatedAt = user.createdAt;


        // Lưu xuống database (phải chạy trong thread khác)
        new Thread(() -> {
            db.userDao().insert(user);
            runOnUiThread(() -> {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}
