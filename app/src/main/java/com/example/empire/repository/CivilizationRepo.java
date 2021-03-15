package com.example.empire.repository;

import com.example.empire.network.services.APIClient;
import com.example.empire.network.services.CivilizationAPIService;
import com.example.empire.ui.civilization.model.CivilizationModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Repository to house the logic for fetching the data. This class queries from Civilization API
 * through network call if network is available, it's stored in local DB when the data is fetched
 * from network call for the first time, else it quires the data from local DB.
 */
public class CivilizationRepo {

    private final CivilizationAPIService apiService;

    public CivilizationRepo() {
        this.apiService = APIClient.getClient().create(CivilizationAPIService.class);
    }

    /**
     * Helper method to make the Network API call to fetch Civilization Details.
     *
     * @return Observable<List < CivilizationModel>>
     */
    private Observable<List<CivilizationModel>> getCivilizationDetailsFromNetwork() {
        Observable<List<CivilizationModel>> observable = apiService.fetchCivilizationsDetails()
                // Convert List<CivilizationNetworkModel> object to List<CivilizationModel>
                .map(CivilizationModel::fromCivilizationNetworkModel)
                .toObservable();
        return observable;
    }

    /**
     * Helper method that is used by the ViewModel to get the List<CivilizationModel> data as
     * Observable type so that it can subscribe.
     * @return Observable<List < CivilizationModel>>
     */
    public Observable<List<CivilizationModel>> getCivilizationDetails() {
        // Check if network is available or not
        // If network is available fetch from API call else from local DB
        // TODO: Replace this condition check with actual network condition check
        if (true) {
            // Return data from network API call
            return getCivilizationDetailsFromNetwork();
        } else {
            // Return data from local DB
            return null;
        }
    }
}