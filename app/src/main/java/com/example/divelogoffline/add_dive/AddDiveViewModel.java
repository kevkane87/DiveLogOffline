package com.example.divelogoffline.add_dive;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.divelogoffline.Dive;
import com.example.divelogoffline.Repository;

public class AddDiveViewModel extends ViewModel {

    private final Repository repository;

    public AddDiveViewModel(Application application) {
        repository = new Repository(application);
    }

    public void insert(Dive dive) { repository.insertDive(dive); }
}
