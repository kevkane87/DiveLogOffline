package com.example.divelogoffline;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DiveDAO {

    @Query("SELECT * FROM Dive")
    Flowable<List<Dive>> getDives();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDive(Dive dive);

    @Query("DELETE FROM Dive where id = :id")
    public void deleteDiveById(int id);

}
