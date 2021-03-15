package com.example.empire.local_database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.empire.ui.civilization.model.CivilizationModel;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "civilization")
public class CivilizationEntry {

    @PrimaryKey
    private int id;
    private String name;
    private String expansion;
    @ColumnInfo(name = "army_type")
    private String armyType;
    @ColumnInfo(name = "unique_unit")
    private List<String> uniqueUnit;
    @ColumnInfo(name = "uniqu_tech")
    private List<String> uniqueTech;
    @ColumnInfo(name = "team_bonus")
    private String teamBonus;
    @ColumnInfo(name = "civilization_bonus")
    private List<String> civilizationBonus;

    public CivilizationEntry(int id, String name, String expansion, String armyType,
                             List<String> uniqueUnit, List<String> uniqueTech,
                             String teamBonus, List<String> civilizationBonus) {
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

    /**
     * Helper Method to convert List<CivilizationModel> as List<CivilizationEntry>
     *
     * @param civilizationModelList
     * @return List<CivilizationEntry>
     */
    public static List<CivilizationEntry> fromCivilizationModel(List<CivilizationModel> civilizationModelList) {
        List<CivilizationEntry> civilizationEntries = new ArrayList<>();
        for (CivilizationModel item : civilizationModelList) {
            civilizationEntries.add(
                    new CivilizationEntry(item.id, item.name, item.expansion, item.armyType,
                            item.uniqueUnit, item.uniqueTech, item.teamBonus, item.civilizationBonus)
            );
        }
        return civilizationEntries;
    }
}
