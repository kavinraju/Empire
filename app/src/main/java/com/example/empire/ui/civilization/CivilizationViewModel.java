package com.example.empire.ui.civilization;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.empire.repository.CivilizationRepo;
import com.example.empire.utils.State;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CivilizationViewModel extends ViewModel {

    private CivilizationRepo repo = new CivilizationRepo();
    private final CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<State> response = new MutableLiveData<>();

    /**
     * Helper method which exposes the response, of type MutableLiveData<State> to the Activity/Fragment.
     *
     * @return LiveData<State>
     */
    public LiveData<State> civilizationDetails() {
        return response;
    }

    /**
     * Helper method to fetch Civilization Details and it's exposed to the Activity/Fragment
     *
     * @param isNetworkAvailable
     */
    public void fetchCivilizationDetails(boolean isNetworkAvailable) {
        fetchCivilizationDetailsFromRepo(isNetworkAvailable);
    }

    /**
     * Helper method to fetch Civilization Details from the Model Layer.
     * It uses the Repository object.
     *
     * @param isNetworkAvailable
     */
    private void fetchCivilizationDetailsFromRepo(boolean isNetworkAvailable) {
        disposable.add(repo.getCivilizationDetails(isNetworkAvailable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> response.setValue(State.loading()))
                .subscribe(
                        data -> response.setValue(State.success(data)),
                        throwable -> response.setValue(State.failed(throwable))
                )
        );
    }

    @Override
    protected void onCleared() {
        // Clear the CompositeDisposable object when the view is getting destroyed
        disposable.clear();
    }
}