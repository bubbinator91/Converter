package com.bubbinator91.converter.tasks.length;

import android.os.AsyncTask;
import android.util.Pair;

import com.bubbinator91.conversion.length.Yards;

import java.util.List;

import timber.log.Timber;

/**
 * An implementation of {@link AsyncTask} that will handle the conversion from yards to the other
 * length values in a separate thread.
 *
 * Usage: When using the execute() method, you must pass in a {@link String} array of size two, with
 * index 0 being the value you want to convert, and index 1 being the number of decimal places to
 * round to.
 */
public class FromYardsTask
        extends AsyncTask<String, Void, Pair<List<String>, Integer>> {
    protected final String TAG = FromYardsTask.class.getSimpleName();

    @Override
    protected Pair<List<String>, Integer> doInBackground(String... params) {
        Timber.tag(TAG + ".doInBackground").i("Entered");

        if ((params == null) || (params.length < 2)) {
            return null;
        }

        try {
            return Yards.toAll(params[0], Integer.parseInt(params[1]));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
