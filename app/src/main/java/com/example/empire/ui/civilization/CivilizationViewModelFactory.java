package com.example.empire.ui.civilization;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CivilizationViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;

    public CivilizationViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CivilizationViewModel(application);
    }
}
