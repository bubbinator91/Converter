package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.acceleration.CentimetersPerSecondSquared;
import com.bubbinator91.conversion.acceleration.FeetPerSecondSquared;
import com.bubbinator91.conversion.acceleration.MetersPerSecondSquared;
import com.bubbinator91.conversion.acceleration.StandardGravity;
import com.bubbinator91.converter.ui.interfaces.acceleration.AccelerationPresenter;
import com.bubbinator91.converter.ui.interfaces.acceleration.AccelerationView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link AccelerationPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.AccelerationFragment}
 */
public class AccelerationPresenterImpl implements AccelerationPresenter {

    private AccelerationView mAccelerationView;

    public void registerView(AccelerationView activity) {
        mAccelerationView = activity;
    }

    public void getConversionFromCentimetersPerSecondSquaredResults(String cmpss, int decimalPlaces) {
        Observable.just(CentimetersPerSecondSquared.toAll(cmpss, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromCentimetersPerSecondSquared(
                                        conversionResults.first,
                                        conversionResults.second
                                );
                    }
                });
    }

    public void getConversionFromFeetPerSecondSquaredResults(String fpss, int decimalPlaces) {
        Observable.just(FeetPerSecondSquared.toAll(fpss, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromFeetPerSecondSquared(
                                        conversionResults.first,
                                        conversionResults.second
                                );
                    }
                });
    }

    public void getConversionFromMetersPerSecondSquaredResults(String mpss, int decimalPlaces) {
        Observable.just(MetersPerSecondSquared.toAll(mpss, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromMetersPerSecondSquared(
                                        conversionResults.first,
                                        conversionResults.second
                                );
                    }
                });
    }

    public void getConversionFromStandardGravity(String sg, int decimalPlaces) {
        Observable.just(StandardGravity.toAll(sg, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mAccelerationView
                                .displayConversionFromStandardGravity(
                                        conversionResults.first,
                                        conversionResults.second
                                );
                    }
                });
    }
}
