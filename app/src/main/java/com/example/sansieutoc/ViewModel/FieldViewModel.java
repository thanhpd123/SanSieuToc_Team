package com.example.sansieutoc.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sansieutoc.Entity.Field;
import com.example.sansieutoc.Repository.FieldRepository;

import java.util.List;

public class FieldViewModel extends AndroidViewModel {
    private FieldRepository repository;
    private LiveData<List<Field>> allFields;

    public FieldViewModel(@NonNull Application application) {
        super(application);
        repository = new FieldRepository(application);
        allFields = repository.getAllFields();
    }

    public void insert(Field field) { repository.insert(field); }
    public void update(Field field) { repository.update(field); }
    public void delete(Field field) { repository.delete(field); }
    public LiveData<List<Field>> getAllFields() { return allFields; }
    public LiveData<Field> getFieldById(int id) { return repository.getFieldById(id); }
}
