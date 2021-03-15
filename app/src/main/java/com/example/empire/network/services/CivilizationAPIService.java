package com.example.empire.network.services;

import com.example.empire.network.model.CivilizationListNetworkModel;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * API Interfaces for Civilization API
 */
public interface CivilizationAPIService {

    @GET("civilizations")
    Flowable<CivilizationListNetworkModel> fetchCivilizationsDetails();

}
