package com.androidchallenge.di;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    private static final String PREF_NAME = "app_preferences";
    private static final String VIEW_MODE_KEY = "view_mode";

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    public static boolean provideViewMode(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(VIEW_MODE_KEY, false);
    }
}

