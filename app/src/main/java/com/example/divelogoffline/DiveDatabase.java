package com.example.divelogoffline;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dive.class}, version = 1, exportSchema = false)
public abstract class DiveDatabase extends RoomDatabase {
        public abstract DiveDAO diveDao();

    private static volatile DiveDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DiveDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiveDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DiveDatabase.class, "dive_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
