package com.example.sansieutoc;



import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Adapter.UserAdapter;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.User;

import java.util.List;
import java.util.concurrent.Executors;

public class AdminManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerUsers;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_management);

        // Ánh xạ RecyclerView
        recyclerUsers = findViewById(R.id.recycler_users);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));

        // Load danh sách người dùng async với try-catch và log
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Log.d("Debug", "Starting DB query in background");

                AppDatabase db = AppDatabase.getInstance(this);
                if (db == null) {
                    throw new RuntimeException("Database instance is null");
                }
                Log.d("Debug", "Database instance obtained successfully");

                // Dòng gây lỗi: Thêm log trước/sau để trace
                Log.d("Debug", "Calling getAll()...");
                userList = db.userDao().getAll();
                Log.d("Debug", "getAll() completed. Users size: " + (userList != null ? userList.size() : 0));

                runOnUiThread(() -> {
                    if (userList != null && !userList.isEmpty()) {
                        userAdapter = new UserAdapter(AdminManagementActivity.this, userList);
                        recyclerUsers.setAdapter(userAdapter);
                    } else {
                        Toast.makeText(this, "Không có người dùng nào", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Log.e("Debug", "Error loading users: " + e.getMessage(), e);  // Log exception đầy đủ
                runOnUiThread(() -> Toast.makeText(this, "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}

