package com.bubbinator91.converter.tasks.fuelconsumption;

import android.os.AsyncTask;
import android.util.Pair;

import com.bubbinator91.conversion.util.ConversionErrorCodes;

import java.util.List;

import timber.log.Timber;

/**
 * An implementation of {@link AsyncTask} that will handle the conversion from kilometers per liter
 * to the other fuel consumption values in a separate thread.
 *
 * Usage: When using the execute() method, you must pass in a {@link String} array of size two, with
 * index 0 being the value you want to convert, and index 1 being the number of decimal places to
 * round to.
 */
public class FromKilometersPerLiterTask
        extends AsyncTask<String, Void, Pair<List<String>, ConversionErrorCodes>> {
    protected final String TAG = FromKilometersPerLiterTask.class.getSimpleName();

    @Override
    protected Pair<List<String>, ConversionErrorCodes> doInBackground(String... params) {
        Timber.tag(TAG + ".doInBackground").i("Entered");

        if ((params == null) || (params.length < 2)) {
            return null;
        }

        // TODO Link to library here
        /*
        try {
            return KilometersPerLiter.toAll(params[0], Integer.parseInt(params[1]));
        } catch (NumberFormatException e) {
            return null;
        }*/
        return null;
    }
}
