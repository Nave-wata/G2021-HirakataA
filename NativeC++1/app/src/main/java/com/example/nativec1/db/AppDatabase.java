package com.example.nativec1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.nativec1.db.usersvolume.UsersVolume;
import com.example.nativec1.db.usersvolume.UsersVolumeDao;

@Database(entities = {UsersVolume.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsersVolumeDao usersInfoDao();
}