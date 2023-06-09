package com.example.divelogoffline;

import android.app.Application;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class Repository {

    private final DiveDAO diveDAO;
    private final Flowable<List<Dive>> allDives;

    public Repository(Application application) {
        DiveDatabase db = DiveDatabase.getDatabase(application);
        diveDAO = db.diveDao();
        allDives = diveDAO.getDives();
    }

    public Flowable<List<Dive>> getAllDives(){
        return allDives;
    }

    public void insertDive(Dive dive) {
        DiveDatabase.databaseWriteExecutor.execute(() -> {
            diveDAO.insertDive(dive);
        });
    }

    public void deleteDive(int diveID){
        DiveDatabase.databaseWriteExecutor.execute(() -> {
            diveDAO.deleteDiveById((diveID));
        });
    }


}
