package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.FieldUnit;

import java.util.List;

@Dao
public interface FieldUnitDao {
    @Insert void insert(FieldUnit fieldUnit);
    @Update void update(FieldUnit fieldUnit);
    @Delete void delete(FieldUnit fieldUnit);
    @Query("SELECT * FROM field_units") List<FieldUnit> getAll();
    @Query("SELECT * FROM field_units WHERE id = :id") FieldUnit getById(int id);
}
