package com.example.empire.local_database;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<String> toStringList(String string) {
        String[] stringArray = string.split("`");
        return Arrays.asList(stringArray);
    }

    @TypeConverter
    public static String toStringArray(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (String str : stringList) {
            builder.append(str).append("`");
        }
        return builder.toString();
    }
}
