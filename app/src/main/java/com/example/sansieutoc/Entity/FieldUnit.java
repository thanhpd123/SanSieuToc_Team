package com.example.sansieutoc.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "field_units")
public class FieldUnit {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String remoteId;
    public String fieldId;
    public int unitNumber;
    public boolean isAvailable;
}
