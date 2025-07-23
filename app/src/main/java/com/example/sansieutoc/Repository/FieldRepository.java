package com.example.sansieutoc.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sansieutoc.DAO.FieldDao;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.Field;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FieldRepository {
    private FieldDao fieldDao;
    private ExecutorService executorService;

    public FieldRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        fieldDao = db.fieldDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Field field) {
        executorService.execute(() -> fieldDao.insert(field));
    }

    public void update(Field field) {
        executorService.execute(() -> fieldDao.update(field));
    }

    public void delete(Field field) {
        executorService.execute(() -> fieldDao.delete(field));
    }

    public LiveData<List<Field>> getAllFields() {
        // Để sử dụng LiveData, bạn cần sửa FieldDao trả về LiveData<List<Field>>
        // Hoặc giữ List<Field> và tự handle thread như dưới
        final androidx.lifecycle.MutableLiveData<List<Field>> fieldsLiveData = new androidx.lifecycle.MutableLiveData<>();
        executorService.execute(() -> fieldsLiveData.postValue(fieldDao.getAll()));
        return fieldsLiveData;
    }

    public LiveData<Field> getFieldById(int id) {
        final androidx.lifecycle.MutableLiveData<Field> fieldLiveData = new androidx.lifecycle.MutableLiveData<>();
        executorService.execute(() -> fieldLiveData.postValue(fieldDao.getById(id)));
        return fieldLiveData;
    }
}
