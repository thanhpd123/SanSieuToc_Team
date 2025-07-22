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
//            db.fieldDao().deleteAllField();
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
            Field f1 = new Field();
            f1.remoteId = "68503ae64dba71d075e1849a";
            f1.ownerId = user.remoteId;
            f1.name = "Sân Cầu Lông Kim Bông";
            f1.typeId = fieldType.remoteId;
            f1.address = "Xã Tân Xã, Huyện Thạch Thất, Hà Nội";
            f1.description = "Sân Cầu Lông Kim Bông Xã Tân Xã, Huyện Thạch Thất, Hà Nội";
            f1.pricePerHour = 50000;
            f1.images = "san_kim_bong";
            f1.availableTimes = "08:00-11:00,14:00-22:00";
            f1.createdAt = "2025-06-16T15:40:22.280Z";
            f1.updatedAt = "2025-06-16T15:40:22.280Z";
            db.fieldDao().insert(f1);

            Field f2 = new Field();
            f2.remoteId = "68503a314dba71d075e1848e";
            f2.ownerId = "685024f3baec404117b5eb2d";
            f2.name = "Sân cỏ nhân tạo Đại Dương";
            f2.typeId = "6836d3231f7f6d0deb0f98d0";
            f2.address = "Cổng làng thôn 2, Xã Tân Xã, Thạch Thất, Hà Nội";
            f2.description = "Sân cỏ nhân tạo Đại Dương Cổng làng thôn 2, Xã Tân Xã, Thạch Thất, Hà Nội";
            f2.pricePerHour = 550000;
            f2.images = "san_dai_duong";
            f2.availableTimes = "08:00-11:00,14:00-22:00";
            f2.createdAt = "2025-06-16T15:37:21.367Z";
            f2.updatedAt = "2025-06-16T15:37:21.367Z";
            db.fieldDao().insert(f2);

            Field f3 = new Field();
            f3.remoteId = "68503c6f4dba71d075e184c4";
            f3.ownerId = "685024f3baec404117b5eb2d";
            f3.name = "Sân Bóng Hơi T3.1";
            f3.typeId = "6836d3231f7f6d0deb0f98d5";
            f3.address = "Trạm Xăng Dầu Thạch Hòa 39, Thạch Hoà, Thạch Thất, Hà Nội";
            f3.description = "Sân Bóng Hơi T3.1 Trạm Xăng Dầu Thạch Hòa 39, Thạch Hoà, Thạch Thất, Hà Nội";
            f3.pricePerHour = 350000;
            f3.images = "san_bong_hoi";
            f3.availableTimes = "08:00-11:00,14:00-22:00";
            f3.createdAt = "2025-06-16T15:46:55.261Z";
            f3.updatedAt = "2025-06-16T15:46:55.261Z";
            db.fieldDao().insert(f3);

            Field f4 = new Field();
            f4.remoteId = "68503be34dba71d075e184b2";
            f4.ownerId = "685024f3baec404117b5eb2d";
            f4.name = "Sân Pickleball ATC - Hà Nội";
            f4.typeId = "6836d3231f7f6d0deb0f98d3";
            f4.address = "Ngã Tư Cầu Liêu, ĐT419, Xã Thạch Xá, Huyện Thạch Thất, Hà Nội";
            f4.description = "Sân Pickleball ATC - Hà Nội  Ngã Tư Cầu Liêu, ĐT419, Xã Thạch Xá, Huyện Thạch Thất, Hà Nội";
            f4.pricePerHour = 170000;
            f4.images = "san_atc";
            f4.availableTimes = "08:00-11:00,14:00-22:00";
            f4.createdAt = "2025-06-16T15:44:35.699Z";
            f4.updatedAt = "2025-06-16T15:44:35.699Z";
            db.fieldDao().insert(f4);

            Field f5 = new Field();
            f5.remoteId = "68503bb04dba71d075e184ac";
            f5.ownerId = "68502278baec404117b5eb03";
            f5.name = "Sân bóng Viettel Hòa Lạc";
            f5.typeId = "6836d3231f7f6d0deb0f98d0";
            f5.address = "Thôn 6, Xã Thạch Hoà, Huyện Thạch Thất, Hà Nội";
            f5.description = "Sân bóng Viettel Hòa Lạc Thôn 6, Xã Thạch Hoà, Huyện Thạch Thất, Hà Nội";
            f5.pricePerHour = 400000;
            f5.images = "san_viettel";
            f5.availableTimes = "08:00-11:00,14:00-22:00";
            f5.createdAt = "2025-06-16T15:43:44.815Z";
            f5.updatedAt = "2025-06-16T15:43:44.815Z";
            db.fieldDao().insert(f5);

            Field f6 = new Field();
            f6.remoteId = "68503b764dba71d075e184a6";
            f6.ownerId = "685024f3baec404117b5eb2d";
            f6.name = "Sân Pickleball Bảo Kim";
            f6.typeId = "6836d3231f7f6d0deb0f98d3";
            f6.address = "Cổng phụ FPT, Thôn 3, Xã Thạch Hoà, Huyện Thạch Thất, Hà Nội";
            f6.description = "Sân Pickleball Bảo Kim Cổng phụ FPT, Thôn 3, Xã Thạch Hoà, Huyện Thạch Thất, Hà Nội";
            f6.pricePerHour = 200000;
            f6.images = "san_bao_kim";
            f6.availableTimes = "08:00-11:00,14:00-22:00";
            f6.createdAt = "2025-06-16T15:42:46.883Z";
            f6.updatedAt = "2025-06-16T15:42:46.883Z";
            db.fieldDao().insert(f6);

            Field f7 = new Field();
            f7.remoteId = "68503aa94dba71d075e18494";
            f7.ownerId = "685024f3baec404117b5eb2d";
            f7.name = "Sân bóng Hoàng Gia";
            f7.typeId = "6836d3231f7f6d0deb0f98d0";
            f7.address = "Thôn 1, Xã Tân Xã, Thạch Thất, Hà Nội";
            f7.description = "Sân bóng Hoàng Gia Thôn 1, Xã Tân Xã, Thạch Thất, Hà Nội";
            f7.pricePerHour = 400000;
            f7.images = "san_hoang_gia";
            f7.availableTimes = "08:00-11:00,14:00-22:00";
            f7.createdAt = "2025-06-16T15:39:21.646Z";
            f7.updatedAt = "2025-06-16T15:39:21.646Z";
            db.fieldDao().insert(f7);

            Field f8 = new Field();
            f8.remoteId = "68503b294dba71d075e184a0";
            f8.ownerId = "685024f3baec404117b5eb2d";
            f8.name = "Sân Pickleball Bình Yên";
            f8.typeId = "6836d3231f7f6d0deb0f98d3";
            f8.address = "Ngã tư Sen Trì, Xã Bình Yên, Huyện Thạch Thất, Hà Nội";
            f8.description = "Sân Pickleball Bình Yên Ngã tư Sen Trì, Xã Bình Yên, Huyện Thạch Thất, Hà Nội";
            f8.pricePerHour = 200000;
            f8.images = "san_binh_yen";
            f8.availableTimes = "08:00-11:00,14:00-22:00";
            f8.createdAt = "2025-06-16T15:41:29.289Z";
            f8.updatedAt = "2025-06-16T15:41:29.289Z";
            db.fieldDao().insert(f8);

            Field f9 = new Field();
            f9.remoteId = "68503c114dba71d075e184b8";
            f9.ownerId = "685024f3baec404117b5eb2d";
            f9.name = "Sân bóng Đại học FPT Hà Nội";
            f9.typeId = "6836d3231f7f6d0deb0f98d0";
            f9.address = "Khuôn viên trường Đại học FPT Hà Nội, Thạch Hoà, Thạch Thất, Hà Nội";
            f9.description = "Sân bóng Đại học FPT Hà Nội Khuôn viên trường Đại học FPT Hà Nội, Thạch Hoà, Thạch Thất, Hà Nội";
            f9.pricePerHour = 300000;
            f9.images = "san_fpt";
            f9.availableTimes = "08:00-11:00,14:00-22:00";
            f9.createdAt = "2025-06-16T15:45:21.988Z";
            f9.updatedAt = "2025-06-16T15:45:21.988Z";
            db.fieldDao().insert(f9);

            Field f10 = new Field();
            f10.remoteId = "68503c404dba71d075e184be";
            f10.ownerId = "685024f3baec404117b5eb2d";
            f10.name = "Sân bóng rổ đại học Quốc gia Hoà Lạc";
            f10.typeId = "6836d3231f7f6d0deb0f98d1";
            f10.address = "Khuôn viên trường Đại học Quốc gia Hoà, Thạch Hoà, Thạch Thất, Hà Nội";
            f10.description = "Sân bóng rổ đại học Quốc gia Hoà Lạc Khuôn viên trường Đại học Quốc gia Hoà, Thạch Hoà, Thạch Thất, Hà Nội";
            f10.pricePerHour = 180000;
            f10.images = "san_bong_ro_quoc_gia";
            f10.availableTimes = "08:00-11:00,14:00-22:00";
            f10.createdAt = "2025-06-16T15:46:08.205Z";
            f10.updatedAt = "2025-06-16T15:46:08.205Z";
            db.fieldDao().insert(f10);

            // FieldUnit
            FieldUnit fu1 = new FieldUnit();
            fu1.remoteId = "684934d7d334f2252ccebf6b";
            fu1.fieldId = f1.remoteId;
            fu1.unitNumber = 1;
            fu1.isAvailable = true;
            db.fieldUnitDao().insert(fu1);

            FieldUnit fu2 = new FieldUnit();
            fu2.remoteId = "684934d7d334f2252ccebf6d";
            fu2.fieldId = "666a1b2c3d4e5f6a7b8c9d01";
            fu2.unitNumber = 3;
            fu2.isAvailable = true;
            db.fieldUnitDao().insert(fu2);

            FieldUnit fu3 = new FieldUnit();
            fu3.remoteId = "684934d7d334f2252ccebf6c";
            fu3.fieldId = "666a1b2c3d4e5f6a7b8c9d01";
            fu3.unitNumber = 2;
            fu3.isAvailable = true;
            db.fieldUnitDao().insert(fu3);

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
            booking.fieldId = f1.remoteId;
            booking.fieldUnitId = fu1.remoteId;
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