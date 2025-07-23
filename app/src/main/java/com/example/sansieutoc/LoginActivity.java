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
import com.example.sansieutoc.Entity.User;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo database (singleton)
        AppDatabase db = AppDatabase.getInstance(this);

        // Insert dữ liệu mẫu (chỉ nên gọi khi lần đầu chạy app hoặc debug, gọi nhiều lần sẽ có dữ liệu trùng)
        DataSample.insertSampleData(db);

        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegisterNow = findViewById(R.id.tvRegisterNow);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edtPhone.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.equals("0363065589") && password.equals("1")) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công (Admin)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, AdminManagementActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                // Kiểm tra đăng nhập bằng Room DB (không hardcode nữa)
                Executors.newSingleThreadExecutor().execute(() -> {
                    User user = db.userDao().findByPhoneAndPassword(phone, password);
                    runOnUiThread(() -> {
                        if (user != null && !user.isBanned ) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("user_id", user.id);  // Truyền userId sang HomeActivity
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Sai số điện thoại hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            }
        });

        tvRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}