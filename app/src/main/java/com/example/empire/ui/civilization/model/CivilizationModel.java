package com.example.empire.ui.civilization.model;

import com.example.empire.network.model.CivilizationListNetworkModel;
import com.example.empire.network.model.CivilizationNetworkModel;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO class used by the View
 */
public class CivilizationModel {
    public int id;
    public String name;
    public String expansion;
    public String armyType;
    public List<String> uniqueUnit;
    public List<String> uniqueTech;
    public String teamBonus;
    public List<String> civilizationBonus;

    public CivilizationModel(int id, String name, String expansion, String armyType, List<String> uniqueUnit, List<String> uniqueTech, String teamBonus, List<String> civilizationBonus) {
        this.id = id;
        this.name = name;
        this.expansion = expansion;
        this.armyType = armyType;
        this.uniqueUnit = uniqueUnit;
        this.uniqueTech = uniqueTech;
        this.teamBonus = teamBonus;
        this.civilizationBonus = civilizationBonus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public String getArmyType() {
        return armyType;
    }

    public void setArmyType(String armyType) {
        this.armyType = armyType;
    }

    public List<String> getUniqueUnit() {
        return uniqueUnit;
    }

    public void setUniqueUnit(List<String> uniqueUnit) {
        this.uniqueUnit = uniqueUnit;
    }

    public List<String> getUniqueTech() {
        return uniqueTech;
    }

    public void setUniqueTech(List<String> uniqueTech) {
        this.uniqueTech = uniqueTech;
    }

    public String getTeamBonus() {
        return teamBonus;
    }

    public void setTeamBonus(String teamBonus) {
        this.teamBonus = teamBonus;
    }

    public List<String> getCivilizationBonus() {
        return civilizationBonus;
    }

    public void setCivilizationBonus(List<String> civilizationBonus) {
        this.civilizationBonus = civilizationBonus;
    }

    public static List<CivilizationModel> fromCivilizationNetworkModel(CivilizationListNetworkModel model) {
        List<CivilizationModel> civilizationModelList = new ArrayList<>();
        for (CivilizationNetworkModel item : model.civilizationNetworkModelList) {
            civilizationModelList.add(
                    new CivilizationModel(item.id, item.name, item.expansion, item.armyType,
                            item.uniqueUnit, item.uniqueTech, item.teamBonus, item.civilizationBonus)
            );
        }
        return civilizationModelList;
    }
}
