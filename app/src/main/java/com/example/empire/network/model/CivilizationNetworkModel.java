package com.example.empire.network.model;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class CivilizationNetworkModel {
    public int id;
    public String name;
    public String expansion;
    @Json(name = "army_type") public  String armyType;
    @Json(name = "unique_unit") public List<String> uniqueUnit;
    @Json(name = "unique_tech") public List<String> uniqueTech;
    @Json(name = "team_bonus") public  String teamBonus;
    @Json(name = "civilization_bonus") public List<String> civilizationBonus;
}
