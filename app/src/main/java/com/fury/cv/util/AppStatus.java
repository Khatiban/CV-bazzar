package com.fury.cv.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class AppStatus {
    static Context context;
    private static AppStatus instance;
    boolean connected;
    ConnectivityManager connectivityManager;
    NetworkInfo mobileInfo;
    NetworkInfo wifiInfo;

    public AppStatus() {
        this.connected = false;
    }

    static {
        instance = new AppStatus();
    }

    public static AppStatus getInstance(Context ctx) {
        context = ctx;
        return instance;
    }

    public boolean isOnline(Context con) {
        try {
            this.connectivityManager = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
            this.connectivityManager.getActiveNetworkInfo();
            NetworkInfo networkInfo = null;
            boolean z = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            this.connected = z;
            return this.connected;
        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
            return this.connected;
        }
    }
}
