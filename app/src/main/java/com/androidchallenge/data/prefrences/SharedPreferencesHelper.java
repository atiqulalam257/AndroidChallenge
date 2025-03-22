package com.androidchallenge.data.prefrences;

import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/**
 * Helper class for managing SharedPreferences in the application.
 * This class handles saving and retrieving the user's preferred view mode (Grid or List).
 */
public class SharedPreferencesHelper {

    /** Key used to store the view mode preference in SharedPreferences. */
    private static final String VIEW_MODE_KEY = "view_mode";

    /** Instance of SharedPreferences for storing key-value pairs. */
    private final SharedPreferences sharedPreferences;

    /**
     * Constructor for SharedPreferencesHelper.
     * Uses dependency injection to get an instance of SharedPreferences.
     *
     * @param sharedPreferences The SharedPreferences instance injected via Dagger.
     */
    @Inject
    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * Saves the user's preferred view mode (Grid or List).
     *
     * @param isGridView {@code true} if Grid View is selected, {@code false} for List View.
     */
    public void saveViewMode(boolean isGridView) {
        sharedPreferences.edit().putBoolean(VIEW_MODE_KEY, isGridView).apply();
    }

    /**
     * Retrieves the user's saved view mode preference.
     *
     * @return {@code true} if Grid View is enabled, {@code false} for List View.
     */
    public boolean getViewMode() {
        return sharedPreferences.getBoolean(VIEW_MODE_KEY, false);
    }
}

