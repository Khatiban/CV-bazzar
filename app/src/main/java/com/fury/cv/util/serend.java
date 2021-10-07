package com.fury.cv.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by fury on 5/8/2017.
 */
public class serend extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Intent stop = new Intent(getBaseContext(),VideoEngine.class);
            stopService(stop);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "StopService", Toast.LENGTH_SHORT).show();
        }
    }
}
