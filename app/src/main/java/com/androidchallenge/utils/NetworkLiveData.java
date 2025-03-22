package com.androidchallenge.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import androidx.lifecycle.LiveData;

public class NetworkLiveData extends LiveData<Boolean> {
    private static NetworkLiveData instance;
    private final Context context;
    private final ConnectivityManager connectivityManager;

    private final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            postValue(true);
        }

        @Override
        public void onLost(Network network) {
            postValue(false);
        }
    };

    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            postValue(NetworkUtils.isNetworkAvailable(context));
        }
    };

    private NetworkLiveData(Context context) {
        this.context = context.getApplicationContext();
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static synchronized NetworkLiveData getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkLiveData(context);
        }
        return instance;
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback);
        } else {
            context.registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        postValue(NetworkUtils.isNetworkAvailable(context));
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        } else {
            context.unregisterReceiver(networkReceiver);
        }
    }
}

