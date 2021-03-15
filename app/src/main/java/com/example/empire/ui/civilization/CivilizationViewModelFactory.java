package com.example.empire.ui.civilization;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CivilizationViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public CivilizationViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CivilizationViewModel(context);
    }
}
