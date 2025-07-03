package com.example.sansieutoc.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coaches")
public class Coach {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String remoteId;
    public String name;
    public String email;
    public String phone;
    public String specialties;
    public int pricePerHour;
    public String description;
    public String availableTimes;
    public double rating;
    public String image;
    public String createdAt;
}