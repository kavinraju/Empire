package com.example.empire.network.services;

import com.example.empire.network.model.CivilizationNetworkModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CivilizationAPIService {

    @GET("civilizations")
    Single<List<CivilizationNetworkModel>> fetchCivilizationsDetails();

}
