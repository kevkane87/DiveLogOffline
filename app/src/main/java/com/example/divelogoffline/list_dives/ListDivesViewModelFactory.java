package com.example.divelogoffline.list_dives;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class ListDivesViewModelFactory implements ViewModelProvider.Factory {

    private final Application app;
    public ListDivesViewModelFactory(Application myApplication) {
        app = myApplication;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListDivesViewModel.class)) {
            return (T) new ListDivesViewModel(app);
        }
        //noinspection unchecked
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}