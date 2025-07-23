package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert void insert(User user);
    @Update void update(User user);
    @Delete void delete(User user);
    @Query("SELECT * FROM users") List<User> getAll();
    @Query("SELECT * FROM users WHERE id = :id") User getById(int id);
    @Query("SELECT * FROM users WHERE phone = :phone AND passwordHash = :password LIMIT 1")
    User findByPhoneAndPassword(String phone, String password);
    @Query("UPDATE users SET isBanned = :isBanned WHERE id = :userId")
    void updateBanStatus(int userId, boolean isBanned);
}