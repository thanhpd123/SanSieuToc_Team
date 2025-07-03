package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.Coach;

import java.util.List;

@Dao
public interface CoachDao {
    @Insert void insert(Coach coach);
    @Update void update(Coach coach);
    @Delete void delete(Coach coach);
    @Query("SELECT * FROM coaches") List<Coach> getAll();
    @Query("SELECT * FROM coaches WHERE id = :id") Coach getById(int id);
}