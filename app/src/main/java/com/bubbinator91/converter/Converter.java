package com.bubbinator91.converter;

import android.app.Application;
import android.util.Log;

import com.bubbinator91.converter.util.Globals;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class Converter extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.d("Debug logging enabled");
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if ((priority != Log.VERBOSE) && (priority != Log.DEBUG)) {
                if (Globals.isLogcatEnabled) {
                    Crashlytics.log(priority, tag, message);
                } else {
                    Crashlytics.log(message);
                }
            }
        }
    }

}
