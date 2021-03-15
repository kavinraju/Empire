package com.example.empire.network.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * POJO class used by the Network Layer
 */
public class CivilizationListNetworkModel {

    @Json(name = "civilizations")
    public List<CivilizationNetworkModel> civilizationNetworkModelList;
}