package com.bubbinator91.converter;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.bubbinator91.converter.util.GlobalsManager;

import timber.log.Timber;

public class Converter extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.d("Debug logging enabled");

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    private static class ReleaseTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (GlobalsManager.INSTANCE.isLogcatEnabled()) {
                if (priority == Log.ERROR) {
                    Log.e(tag, message, t);
                } else if (priority == Log.INFO) {
                    Log.i(tag, message, t);
                } else if (priority == Log.WARN) {
                    Log.w(tag, message, t);
                }
            }
        }
    }

}
