package com.example.divelogoffline;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dive {
    @PrimaryKey(autoGenerate = true)public int id;
    @ColumnInfo(name = "date") public String date;
    @ColumnInfo(name = "diveTitle") public String diveTitle;
    @ColumnInfo(name = "diveSite") public String diveSite;
    @ColumnInfo(name = "bottomTime") public int bottomTime;
    @ColumnInfo(name = "maxDepth") public int maxDepth;
}