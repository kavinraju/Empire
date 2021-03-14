package com.example.empire.repository;

import com.example.empire.network.model.CivilizationNetworkModel;
import com.example.empire.network.services.APIClient;
import com.example.empire.network.services.CivilizationAPIService;

import java.util.List;

import io.reactivex.Single;

public class CivilizationRepo {

    private CivilizationAPIService apiService;

    public CivilizationRepo() {
        this.apiService = APIClient.getClient().create(CivilizationAPIService.class);
    }

    public Single<List<CivilizationNetworkModel>> getCivilizationDetails(){
        return apiService.fetchCivilizationsDetails();
    }
}
