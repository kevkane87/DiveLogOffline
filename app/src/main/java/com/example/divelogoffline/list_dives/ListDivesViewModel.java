package com.example.divelogoffline.list_dives;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.room.Database;

import com.example.divelogoffline.Dive;
import com.example.divelogoffline.Repository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class ListDivesViewModel extends ViewModel {

    private final Repository repository;

    public ListDivesViewModel(Application application) {
        repository = new Repository(application);
    }

    Flowable<List<Dive>> getAllDives() { return repository.getAllDives(); }

}
