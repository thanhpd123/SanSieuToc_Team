package com.example.sansieutoc.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "field_types")
public class FieldType {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String remoteId;
    public String name;
    public String images;
}
