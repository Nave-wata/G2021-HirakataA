package com.example.nativec1.db.usersvolume;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UsersVolume")
public class UsersVolume {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Volume1")
    public int volume1;

    @ColumnInfo(name = "Volume2")
    public int volume2;

    @ColumnInfo(name = "Volume3")
    public int volume3;

    @ColumnInfo(name = "Volume4")
    public int volume4;

    @ColumnInfo(name = "volume5")
    public int volume5;

    @ColumnInfo(name = "Volume6")
    public int volume6;

    @ColumnInfo(name = "Volume7")
    public int volume7;

    @ColumnInfo(name = "Volume8")
    public int volume8;

    @ColumnInfo(name = "Volume9")
    public int volume9;

    @ColumnInfo(name = "volume10")
    public int volume10;


    public UsersVolume(String name, int[] volumes) {
        this.name = name;

        this.volume1 = volumes[0];
        this.volume2 = volumes[1];
        this.volume3 = volumes[2];
        this.volume4 = volumes[3];
        this.volume5 = volumes[4];
        this.volume6 = volumes[5];
        this.volume7 = volumes[6];
        this.volume8 = volumes[7];
        this.volume9 = volumes[8];
        this.volume10 = volumes[9];
    }

    public String getName() {
        return name;
    }

    public int[] getVolume() {
        return new int[]{volume1, volume2, volume3, volume4, volume5, volume6, volume7, volume8, volume9, volume10};
    }
}
