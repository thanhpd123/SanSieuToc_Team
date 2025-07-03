package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.Booking;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert void insert(Booking booking);
    @Update void update(Booking booking);
    @Delete void delete(Booking booking);
    @Query("SELECT * FROM bookings") List<Booking> getAll();
    @Query("SELECT * FROM bookings WHERE id = :id") Booking getById(int id);
}
