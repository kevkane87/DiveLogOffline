package com.example.divelogoffline.add_dive;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class AddDiveViewModelFactory implements ViewModelProvider.Factory {

    private final Application app;
    public AddDiveViewModelFactory(Application myApplication) {
        app = myApplication;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddDiveViewModel.class)) {
            return (T) new AddDiveViewModel(app);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
