package com.example.sansieutoc.DataHelper;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sansieutoc.DAO.BookingDao;
import com.example.sansieutoc.DAO.CoachBookingDao;
import com.example.sansieutoc.DAO.CoachDao;
import com.example.sansieutoc.DAO.FieldDao;
import com.example.sansieutoc.DAO.FieldTypeDao;
import com.example.sansieutoc.DAO.FieldUnitDao;
import com.example.sansieutoc.DAO.UserDao;
import com.example.sansieutoc.Entity.Booking;
import com.example.sansieutoc.Entity.Coach;
import com.example.sansieutoc.Entity.CoachBooking;
import com.example.sansieutoc.Entity.Field;
import com.example.sansieutoc.Entity.FieldType;
import com.example.sansieutoc.Entity.FieldUnit;
import com.example.sansieutoc.Entity.User;

@Database(
        entities = {
                User.class,
                FieldType.class,
                Field.class,
                FieldUnit.class,
                Booking.class,
                Coach.class,
                CoachBooking.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FieldTypeDao fieldTypeDao();
    public abstract FieldDao fieldDao();
    public abstract FieldUnitDao fieldUnitDao();
    public abstract BookingDao bookingDao();
    public abstract CoachDao coachDao();
    public abstract CoachBookingDao coachBookingDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "sansieutoc_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

