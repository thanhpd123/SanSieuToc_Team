package com.example.sansieutoc.DataHelper;

import android.content.Context;

import com.example.sansieutoc.Entity.Booking;
import com.example.sansieutoc.Entity.Coach;
import com.example.sansieutoc.Entity.CoachBooking;
import com.example.sansieutoc.Entity.Field;
import com.example.sansieutoc.Entity.FieldType;
import com.example.sansieutoc.Entity.FieldUnit;
import com.example.sansieutoc.Entity.User;

import java.util.concurrent.Executors;

// Import các entity và dao đã tạo
// import com.example.sansieutoc.database.*;

public class DataSample {
    public static void insertSampleData(AppDatabase db) {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (db.userDao().getAll().size() > 0) return;

            // FieldType
            FieldType fieldType = new FieldType();
            fieldType.remoteId = "6836d3231f7f6d0deb0f98d4";
            fieldType.name = "Cầu lông";
            fieldType.images = "caulong.png";
            db.fieldTypeDao().insert(fieldType);

            // User
            User user = new User();
            user.remoteId = "68515ce1589486f81920b587";
            user.name = "Phan Duy Thành";
            user.email = "plane0802@gmail.com";
            user.passwordHash = "2";
            user.role = "user";
            user.phone = "0336677789";
            user.address = "Khối 2 thị trấn Con Cuông";
            user.createdAt = "2025-06-17T12:17:37.548Z";
            user.updatedAt = "2025-06-17T12:17:37.548Z";
            db.userDao().insert(user);

            // Field
            Field field = new Field();
            field.remoteId = "68503ae64dba71d075e1849a";
            field.ownerId = user.remoteId;
            field.name = "Sân Cầu Lông Kim Bông";
            field.typeId = fieldType.remoteId;
            field.address = "Xã Tân Xã, Huyện Thạch Thất, Hà Nội";
            field.description = "Sân Cầu Lông Kim Bông Xã Tân Xã, Huyện Thạch Thất, Hà Nội";
            field.pricePerHour = 50000;
            field.images = "/images/1750088405865-200961539.png";
            field.availableTimes = "08:00-11:00,14:00-22:00";
            field.createdAt = "2025-06-16T15:40:22.280Z";
            field.updatedAt = "2025-06-16T15:40:22.280Z";
            db.fieldDao().insert(field);

            // FieldUnit
            FieldUnit fieldUnit = new FieldUnit();
            fieldUnit.remoteId = "684934d7d334f2252ccebf6b";
            fieldUnit.fieldId = field.remoteId;
            fieldUnit.unitNumber = 1;
            fieldUnit.isAvailable = true;
            db.fieldUnitDao().insert(fieldUnit);

            // Coach
            Coach coach = new Coach();
            coach.remoteId = "683ace7cd4448490b898684e";
            coach.name = "Nguyễn Văn An";
            coach.email = "an.nguyen@example.com";
            coach.phone = "0912345678";
            coach.specialties = "Fitness,Yoga,Cardio";
            coach.pricePerHour = 200000;
            coach.description = "HLV thể hình và yoga, kinh nghiệm 8 năm.";
            coach.availableTimes = "Monday 08:00-10:00,Wednesday 14:00-16:00,Friday 18:00-20:00";
            coach.rating = 4.8;
            coach.image = "/images/coach_an.jpg";
            coach.createdAt = "2023-01-15T10:30:00.000Z";
            db.coachDao().insert(coach);

            // Booking
            Booking booking = new Booking();
            booking.remoteId = "683a01571ae466fde6f4cfbe";
            booking.fieldId = field.remoteId;
            booking.fieldUnitId = fieldUnit.remoteId;
            booking.userId = user.remoteId;
            booking.date = "2025-05-30";
            booking.startTime = "11:00";
            booking.endTime = "12:00";
            booking.status = "pending";
            booking.totalPrice = 0;
            booking.createdAt = "2025-05-30T19:04:55.688Z";
            booking.updatedAt = "2025-05-30T19:04:55.688Z";
            db.bookingDao().insert(booking);

            // CoachBooking
            CoachBooking coachBooking = new CoachBooking();
            coachBooking.remoteId = "6848d75dd9b487d3947cba87";
            coachBooking.coachId = coach.remoteId;
            coachBooking.userId = user.remoteId;
            coachBooking.date = "2025-06-09";
            coachBooking.startTime = "11:00";
            coachBooking.endTime = "12:00";
            coachBooking.status = "pending";
            coachBooking.totalPrice = 210000;
            coachBooking.createdAt = "2025-06-11T01:09:49.968Z";
            db.coachBookingDao().insert(coachBooking);
        });
    }
}