package com.androidchallenge.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import androidx.lifecycle.LiveData;

/**
 * LiveData class for monitoring network connectivity status in real-time.
 * - Uses {@link ConnectivityManager.NetworkCallback} for Android Nougat (API 24+) and above.
 * - Uses {@link BroadcastReceiver} for older Android versions.
 */
public class NetworkLiveData extends LiveData<Boolean> {

    /** Singleton instance of NetworkLiveData. */
    private static NetworkLiveData instance;

    /** Application context to prevent memory leaks. */
    private final Context context;

    /** System connectivity manager to monitor network status. */
    private final ConnectivityManager connectivityManager;

    /**
     * Network callback for detecting changes in network availability (API 24+).
     * - Calls {@link #postValue(Boolean)} when the network is available or lost.
     */
    private final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            postValue(true); // Network is available
        }

        @Override
        public void onLost(Network network) {
            postValue(false); // Network is lost
        }
    };

    /**
     * BroadcastReceiver for monitoring connectivity changes on older devices (Pre-API 24).
     * - Uses {@link NetworkUtils#isNetworkAvailable(Context)} to check network status.
     */
    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            postValue(NetworkUtils.isNetworkAvailable(context));
        }
    };

    /**
     * Private constructor to ensure singleton instance.
     *
     * @param context The application context.
     */
    private NetworkLiveData(Context context) {
        this.context = context.getApplicationContext();
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * Provides a singleton instance of NetworkLiveData.
     *
     * @param context The application context.
     * @return A single instance of NetworkLiveData.
     */
    public static synchronized NetworkLiveData getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkLiveData(context);
        }
        return instance;
    }

    /**
     * Called when there are active observers.
     * Registers network callbacks to start monitoring connectivity.
     */
    @Override
    protected void onActive() {
        super.onActive();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Register network callback for API 24+
            connectivityManager.registerDefaultNetworkCallback(networkCallback);
        } else {
            // Register broadcast receiver for older devices
            context.registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        // Post initial network status
        postValue(NetworkUtils.isNetworkAvailable(context));
    }

    /**
     * Called when there are no active observers.
     * Unregisters network callbacks and broadcast receivers to prevent memory leaks.
     */
    @Override
    protected void onInactive() {
        super.onInactive();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Unregister network callback for API 24+
            connectivityManager.unregisterNetworkCallback(networkCallback);
        } else {
            // Unregister broadcast receiver for older devices
            context.unregisterReceiver(networkReceiver);
        }
    }
}
