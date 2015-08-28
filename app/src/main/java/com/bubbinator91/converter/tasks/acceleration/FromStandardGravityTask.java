package com.bubbinator91.converter.tasks.acceleration;

import android.os.AsyncTask;
import android.util.Pair;

import com.bubbinator91.conversion.acceleration.StandardGravity;

import java.util.List;

import timber.log.Timber;

/**
 * An implementation of {@link AsyncTask} that will handle the conversion from standard gravity to
 * the other acceleration values in a separate thread.
 *
 * Usage: When using the execute() method, you must pass in a {@link String} array of size two, with
 * index 0 being the value you want to convert, and index 1 being the number of decimal places to
 * round to.
 */
public class FromStandardGravityTask
        extends AsyncTask<String, Void, Pair<List<String>, Integer>> {
    protected final String TAG = FromStandardGravityTask.class.getSimpleName();

    @Override
    protected Pair<List<String>, Integer> doInBackground(String... params) {
        Timber.tag(TAG + ".doInBackground").i("Entered");

        if ((params == null) || (params.length < 2)) {
            return null;
        }

        try {
            return StandardGravity.toAll(params[0], Integer.parseInt(params[1]));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
