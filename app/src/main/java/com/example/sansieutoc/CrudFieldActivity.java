package com.example.sansieutoc;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.Adapter.FieldAdapter;
import com.example.sansieutoc.DAO.FieldDao;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.Field;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.Executors;

public class CrudFieldActivity extends AppCompatActivity {

    private FieldDao fieldDao;
    private FieldAdapter adapter;
    private List<Field> fieldList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_field);

        // Khởi tạo Room DB và DAO
        AppDatabase db = AppDatabase.getInstance(this);
        fieldDao = db.fieldDao();

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFields);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo adapter với context + list rỗng ban đầu
        adapter = new FieldAdapter(this, fieldList);
        recyclerView.setAdapter(adapter);

        // Load dữ liệu
        loadFields();

        // FloatingActionButton: Thêm sân mới
        FloatingActionButton fabAdd = findViewById(R.id.fabAddField);
//        fabAdd.setOnClickListener(v -> {
//            Intent intent = new Intent(CrudFieldActivity.this, UpdateFieldActivity.class);
//            // Không truyền field_id để phân biệt là thêm mới
//            startActivity(intent);
//        });

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(CrudFieldActivity.this, AddFieldActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Load dữ liệu sân từ Room DB
     */
    private void loadFields() {
        Executors.newSingleThreadExecutor().execute(() -> {
            fieldList = fieldDao.getAll();
            runOnUiThread(() -> adapter.setFields(fieldList));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load lại dữ liệu khi quay về từ UpdateFieldActivity
        loadFields();
    }
}
