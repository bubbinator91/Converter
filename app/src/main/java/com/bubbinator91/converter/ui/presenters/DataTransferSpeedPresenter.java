package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.datatransferspeed.DataTransferSpeed;
import com.bubbinator91.converter.ui.interfaces.datatransferspeed.IDataTransferSpeedPresenter;
import com.bubbinator91.converter.ui.interfaces.datatransferspeed.IDataTransferSpeedView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link IDataTransferSpeedPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment}
 */
public class DataTransferSpeedPresenter implements IDataTransferSpeedPresenter {

    private IDataTransferSpeedView mDataTransferSpeedView;

    private final DataTransferSpeed dataTransferSpeed;

    public DataTransferSpeedPresenter(DataTransferSpeed dataTransferSpeed) {
        this.dataTransferSpeed = dataTransferSpeed;
    }

    @Override
    public void registerView(IDataTransferSpeedView activity) {
        mDataTransferSpeedView = activity;
    }

    @Override
    public void getConversionFromBitsPerSecondResults(String bps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.BitsPerSecond().toAll(bps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromBitsPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromBytesPerSecondResults(String byps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.BytesPerSecond().toAll(byps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromBytesPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromKilobitsPerSecondResults(String kbps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.KilobitsPerSecond().toAll(kbps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromKilobitsPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromKilobytesPerSecondResults(String kbyps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.KilobytesPerSecond().toAll(kbyps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromKilobytesPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMegabitsPerSecondResults(String mbps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.MegabitsPerSecond().toAll(mbps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromMegabitsPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromMegabytesPerSecondResults(String mbyps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.MegabytesPerSecond().toAll(mbyps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromMegabytesPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromGigabitsPerSecondResults(String gbps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.GigabitsPerSecond().toAll(gbps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromGigabitsPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromGigabytesPerSecondResults(String gbyps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.GigabytesPerSecond().toAll(gbyps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromGigabytesPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromTerabitsPerSecondResults(String tbps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.TerabitsPerSecond().toAll(tbps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromTerabitsPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromTerabytesPerSecondResults(String tbyps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.TerabytesPerSecond().toAll(tbyps, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mDataTransferSpeedView
                                .displayConversionFromTerabytesPerSecondResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }
}
