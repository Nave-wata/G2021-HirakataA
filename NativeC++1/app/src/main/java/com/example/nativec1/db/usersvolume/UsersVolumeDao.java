package com.example.nativec1.db.usersvolume;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersVolumeDao {
    @Insert
    void insert(UsersVolume usersVolume);

    @Query("DELETE FROM UsersVolume")
    void deleteAllUsersVolume();

    @Query("SELECT * FROM UsersVolume")
    List<UsersVolume> getAllUsersVolume();
}
