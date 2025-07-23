package com.example.sansieutoc.DAO;

import androidx.room.*;

import com.example.sansieutoc.Entity.Booking;
import com.example.sansieutoc.Entity.BookingWithFieldName;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert void insert(Booking booking);
    @Update void update(Booking booking);
    @Delete void delete(Booking booking);
    @Query("SELECT * FROM bookings") List<Booking> getAll();
    @Query("SELECT * FROM bookings WHERE id = :id") Booking getById(int id);
    @Query("SELECT b.id, f.name AS fieldName, b.date, b.startTime, b.endTime, b.status, b.createdAt " +
            "FROM bookings b " +
            "INNER JOIN fields f ON b.fieldId = f.remoteId " +
            "WHERE b.userId = :userId " +
            "ORDER BY b.createdAt DESC")
    List<BookingWithFieldName> getBookingsWithFieldNameByUser(String userId);
}
