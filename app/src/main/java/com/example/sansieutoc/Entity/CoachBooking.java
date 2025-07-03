package com.example.sansieutoc.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coach_bookings")
public class CoachBooking {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String remoteId;
    public String coachId; // remoteId của Coach
    public String userId;  // remoteId của User
    public String date;
    public String startTime;
    public String endTime;
    public String status;
    public double totalPrice;
    public String createdAt;
}
