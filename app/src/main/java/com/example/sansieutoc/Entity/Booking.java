package com.example.sansieutoc.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookings")
public class Booking {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String remoteId;
    public String fieldId;
    public String fieldUnitId;
    public String userId;
    public String date;
    public String startTime;
    public String endTime;
    public String status;
    public double totalPrice;
    public String createdAt;
    public String updatedAt;
}
