package com.example.empire.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Helper class to store the status of the data from the ViewModel layer.
 * @param <T>
 */
public class State<T> {

    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable error;

    public State(Status status, @Nullable T data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> State loading() {
        return new State<>(Status.LOADING, null, null);
    }

    public static <T> State success(@NonNull T data) {
        return new State<>(Status.SUCCESS, data, null);
    }

    public static <T> State failed(@Nullable Throwable error) {
        return new State<>(Status.FAILED, null, error);
    }
}
