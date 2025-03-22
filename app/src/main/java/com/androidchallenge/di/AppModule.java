package com.androidchallenge.di;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * Dagger Hilt module that provides application-level dependencies.
 * This module is installed in the {@link SingletonComponent}, ensuring a single instance of each provided dependency.
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    /** The name of the SharedPreferences file used for storing app preferences. */
    private static final String PREF_NAME = "app_preferences";

    /** Key used to store the user's preferred view mode (Grid or List). */
    private static final String VIEW_MODE_KEY = "view_mode";

    /**
     * Provides a singleton instance of {@link SharedPreferences}.
     * Used for storing and retrieving persistent app preferences.
     *
     * @param context The application context injected by Hilt.
     * @return A {@link SharedPreferences} instance.
     */
    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Provides the saved view mode preference.
     * Retrieves whether the user has selected Grid View or List View.
     *
     * @param sharedPreferences The SharedPreferences instance.
     * @return {@code true} if Grid View is selected, {@code false} for List View.
     */
    @Provides
    public static boolean provideViewMode(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(VIEW_MODE_KEY, false);
    }
}
