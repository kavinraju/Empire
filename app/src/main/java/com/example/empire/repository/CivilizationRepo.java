package com.example.empire.repository;

import android.util.Log;

import com.example.empire.local_database.CivilizationDatabase;
import com.example.empire.local_database.CivilizationEntry;
import com.example.empire.network.services.APIClient;
import com.example.empire.network.services.CivilizationAPIService;
import com.example.empire.ui.civilization.model.CivilizationModel;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Repository to house the logic for fetching the data. This class queries from Civilization API
 * through network call if network is available, it's stored in local DB when the data is fetched
 * from network call for the first time, else it quires the data from local DB.
 */
public class CivilizationRepo {

    private static final String LOG = CivilizationRepo.class.getSimpleName();

    private final CivilizationAPIService apiService;
    private final CivilizationDatabase database;

    public CivilizationRepo(CivilizationDatabase database) {
        this.apiService = APIClient.getClient().create(CivilizationAPIService.class);
        this.database = database;
    }

    /**
     * Helper method to make the Network API call to fetch Civilization Details.
     *
     * @return Flowable<List < CivilizationModel>>
     */
    private Flowable<List<CivilizationModel>> getCivilizationDetailsFromNetwork() {
        return apiService.fetchCivilizationsDetails()
                // Convert List<CivilizationNetworkModel> object to List<CivilizationModel>
                .map(CivilizationModel::fromCivilizationNetworkModel);
    }

    /**
     * Helper method to add the List<CivilizationEntry> into local database.
     *
     * @param civilizationEntryList
     */
    public void addCivilizationDetailsIntoLocalDatabase(List<CivilizationEntry> civilizationEntryList) {
        database.civilizationDao()
                .insertCivilizationDetails(civilizationEntryList)
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(LOG, "onSubscribe ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(LOG, "onComplete ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(LOG, "onError " + e);
                    }
                });
    }

    /**
     * Helper method to fetch Civilization Details from local Database
     *
     * @return Flowable<List < CivilizationModel>>
     */
    private Flowable<List<CivilizationModel>> getCivilizationDetailsFromLocalDatabase() {
        Flowable<List<CivilizationModel>> flowable = database.civilizationDao()
                .getCivilizationDetails()
                // Convert List<CivilizationEntry> object to List<CivilizationModel>
                .map(CivilizationModel::fromCivilizationDatabaseModel);
        return flowable;
    }

    /**
     * Helper method that is used by the ViewModel to get the List<CivilizationModel> data as
     * Flowable type so that it can subscribe.
     *
     * @param isNetworkAvailable
     * @return Flowable<List < CivilizationModel>>
     */
    public Flowable<List<CivilizationModel>> getCivilizationDetails(boolean isNetworkAvailable) {

        // Check if network is available or not
        // If network is available fetch from API call and store into local DB
        if (isNetworkAvailable) {
            Log.d(LOG, "isNetworkAvailable");

            // Query from network call
            getCivilizationDetailsFromNetwork()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new DisposableSubscriber<List<CivilizationModel>>() {
                        @Override
                        public void onNext(List<CivilizationModel> civilizationModelList) {
                            Log.d(LOG, "onNext " + civilizationModelList.toString());
                            // Update the Database
                            // Same data will be ignored and only the new data will be inserted
                            addCivilizationDetailsIntoLocalDatabase(CivilizationEntry
                                    .fromCivilizationModel(civilizationModelList));
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.e(LOG, t.toString());
                        }

                        @Override
                        public void onComplete() {
                            Log.d(LOG, "onComplete ");
                        }
                    });

            /*
            PublishSubject<Observable<List<CivilizationModel>>> sub = PublishSubject.create();
            Observable<List<CivilizationModel>> observable = getCivilizationDetailsFromNetwork();

            sub.subscribe(new DisposableObserver<Observable<List<CivilizationModel>>>() {
                @Override
                public void onNext(@NonNull Observable<List<CivilizationModel>> listObservable) {
                    Log.d(LOG, "onNext " + listObservable.toString());
                    listObservable.fla
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

            sub.onNext(observable);
            return getCivilizationDetailsFromNetwork();*/
        } else {
            Log.d(LOG, "Network not Available");
        }

        // Return data from local DB
        return getCivilizationDetailsFromLocalDatabase();
    }
}