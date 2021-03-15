package com.example.empire.local_database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface CivilizationDao {

    @Query("SELECT * from civilization")
    Flowable<List<CivilizationEntry>> getCivilizationDetails();

    @Query("SELECT COUNT(*) from civilization")
    Flowable<Integer> getTotalCivilizationDetailsCount();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertCivilizationDetails(List<CivilizationEntry> civilizationEntryList);

}
