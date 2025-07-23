package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.Field;

import java.util.List;

@Dao
public interface FieldDao {
    @Insert void insert(Field field);
    @Update void update(Field field);
    @Delete void delete(Field field);

    @Query("SELECT * FROM fields") 
    List<Field> getAll();
    
    @Query("SELECT * FROM fields WHERE id = :id") 
    Field getById(int id);
    
    @Query("DELETE FROM fields")
    void deleteAllField();
}
