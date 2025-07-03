package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.CoachBooking;

import java.util.List;

@Dao
public interface CoachBookingDao {
    @Insert void insert(CoachBooking coachBooking);
    @Update void update(CoachBooking coachBooking);
    @Delete void delete(CoachBooking coachBooking);
    @Query("SELECT * FROM coach_bookings") List<CoachBooking> getAll();
    @Query("SELECT * FROM coach_bookings WHERE id = :id") CoachBooking getById(int id);
}