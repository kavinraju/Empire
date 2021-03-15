package com.example.empire.local_database.utils;

public class DatabaseEvent<T> {
    public final DatabaseEventType eventType;
    public T data;

    public DatabaseEvent(DatabaseEventType eventType, T data) {
        this.eventType = eventType;
        this.data = data;
    }
}
