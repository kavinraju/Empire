package com.example.empire.local_database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {CivilizationEntry.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class CivilizationDatabase extends RoomDatabase {

    private static final String LOG_TAG = CivilizationDatabase.class.getSimpleName();
    private static final Object OBJECT = new Object();
    private static final String DATABASE_NAME = "civilization_db";
    private static CivilizationDatabase database;

    public static CivilizationDatabase getInstance(Context context) {
        if (database == null) {
            synchronized (OBJECT) {
                Log.d(LOG_TAG, "Creating a new Database");
                database = Room.databaseBuilder(context.getApplicationContext(),
                        CivilizationDatabase.class,
                        DATABASE_NAME)
                        .build();
            }
        }
        return database;
    }

    public abstract CivilizationDao civilizationDao();
}
