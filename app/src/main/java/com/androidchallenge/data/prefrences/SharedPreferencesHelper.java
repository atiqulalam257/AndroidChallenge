package com.androidchallenge.data.prefrences;

import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferencesHelper {
    private static final String VIEW_MODE_KEY = "view_mode";
    private final SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveViewMode(boolean isGridView) {
        sharedPreferences.edit().putBoolean(VIEW_MODE_KEY, isGridView).apply();
    }

    public boolean getViewMode() {
        return sharedPreferences.getBoolean(VIEW_MODE_KEY, false);
    }
}
