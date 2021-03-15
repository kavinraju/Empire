package com.example.empire.network.services;

import com.example.empire.network.model.CivilizationListNetworkModel;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * API Interfaces for Civilization API
 */
public interface CivilizationAPIService {

    @GET("civilizations")
    Single<CivilizationListNetworkModel> fetchCivilizationsDetails();

}
