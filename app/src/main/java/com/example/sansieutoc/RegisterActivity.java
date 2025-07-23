package com.example.sansieutoc;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.User;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtFullName, edtAddress, edtPhone, edtEmail, edtPassword, edtConfirmPassword;
    private Button btnRegister;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ view
        edtFullName = findViewById(R.id.edtFullName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Khởi tạo database
        db = AppDatabase.getInstance(this);

        btnRegister.setOnClickListener(v -> handleRegister());
    }

    private void handleRegister() {
        String fullName = edtFullName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();

        // Kiểm tra dữ liệu
        if (TextUtils.isEmpty(fullName) ||
                TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            // Kiểm tra trùng số điện thoại
            User userByPhone = db.userDao().findByPhone(phone);
            if (userByPhone != null) {
                runOnUiThread(() -> Toast.makeText(this, "Số điện thoại đã tồn tại", Toast.LENGTH_SHORT).show());
                return;
            }

            // Kiểm tra trùng email
            User userByEmail = db.userDao().findByEmail(email);
            if (userByEmail != null) {
                runOnUiThread(() -> Toast.makeText(this, "Email đã tồn tại", Toast.LENGTH_SHORT).show());
                return;
            }

            // Tạo user mới
            User user = new User();
            user.name = fullName;
            user.phone = phone;
            user.email = email;
            user.address = address;
            user.passwordHash = password;
            user.role = "user";
            user.remoteId = UUID.randomUUID().toString();
            user.createdAt = String.valueOf(System.currentTimeMillis());
            user.updatedAt = user.createdAt;

            db.userDao().insert(user);

            runOnUiThread(() -> {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}


