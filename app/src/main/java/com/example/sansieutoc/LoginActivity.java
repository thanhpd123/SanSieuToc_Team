package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.DataHelper.DataSample;
import com.example.sansieutoc.Entity.CurrentUser;
import com.example.sansieutoc.Entity.User;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppDatabase db = AppDatabase.getInstance(this);

        // Insert dữ liệu mẫu (nếu cần)
        DataSample.insertSampleData(db);

        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegisterNow = findViewById(R.id.tvRegisterNow);

        btnLogin.setOnClickListener(view -> {
            String phone = edtPhone.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Đăng nhập admin (hardcoded)
            if (phone.equals("0363065589") && password.equals("1")) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công (Admin)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, AdminManagementActivity.class);
                startActivity(intent);
                finish();
                return;
            }

            // Kiểm tra đăng nhập bằng Room DB
            Executors.newSingleThreadExecutor().execute(() -> {
                User user = db.userDao().findByPhoneAndPassword(phone, password);

                runOnUiThread(() -> {
                    if (user != null) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        // Gán CurrentUser
                        CurrentUser.user = user;

                        // Lưu role vào SharedPreferences
                        getSharedPreferences("MyPrefs", MODE_PRIVATE)
                                .edit()
                                .putString("role", user.role)
                                .apply();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("user_id", user.id);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Sai số điện thoại hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });

        // Sự kiện chuyển sang RegisterActivity
        tvRegisterNow.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
