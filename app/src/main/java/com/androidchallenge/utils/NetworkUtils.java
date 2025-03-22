package com.androidchallenge.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Utility class for checking network connectivity status.
 * Provides methods to determine whether the device is connected to the internet.
 */
public class NetworkUtils {

    /**
     * Checks if the device has an active internet connection.
     * - Uses {@link NetworkCapabilities} for Android 6.0 (API 23) and above.
     * - Uses {@link NetworkInfo} for devices running below API 23.
     *
     * @param context The application context.
     * @return {@code true} if the device is connected to the internet, {@code false} otherwise.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // For Android 6.0 (API 23) and above, use NetworkCapabilities
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            } else {
                // For devices below Android 6.0, use NetworkInfo (deprecated in API 29)
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnected();
            }
        }
        return false;
    }
}
