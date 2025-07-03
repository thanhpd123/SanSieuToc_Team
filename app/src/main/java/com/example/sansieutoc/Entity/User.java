package com.example.sansieutoc.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String remoteId;
    public String name;
    public String email;
    public String passwordHash;
    public String role;
    public String phone;
    public String address;
    public String createdAt;
    public String updatedAt;
}
