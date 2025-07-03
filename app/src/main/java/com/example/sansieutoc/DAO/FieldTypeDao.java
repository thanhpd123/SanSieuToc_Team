package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.FieldType;

import java.util.List;

@Dao
public interface FieldTypeDao {
    @Insert void insert(FieldType fieldType);
    @Update void update(FieldType fieldType);
    @Delete void delete(FieldType fieldType);
    @Query("SELECT * FROM field_types") List<FieldType> getAll();
    @Query("SELECT * FROM field_types WHERE id = :id") FieldType getById(int id);
}
