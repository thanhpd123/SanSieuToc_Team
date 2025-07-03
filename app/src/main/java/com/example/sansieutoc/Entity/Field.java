package com.example.sansieutoc.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fields")
public class Field {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String remoteId;
    public String ownerId;
    public String name;
    public String typeId;
    public String address;
    public String description;
    public int pricePerHour;
    public String images;
    public String availableTimes;
    public String createdAt;
    public String updatedAt;
}