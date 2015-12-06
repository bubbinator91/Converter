package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.datatransferspeed.DataTransferSpeed;
import com.bubbinator91.converter.interfaces.presenter.IDataTransferSpeedPresenter;
import com.bubbinator91.converter.interfaces.view.IDataTransferSpeedView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Implementation of the {@link IDataTransferSpeedPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment}
 */
public class DataTransferSpeedPresenter implements IDataTransferSpeedPresenter {

    private final DataTransferSpeed dataTransferSpeed;
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private IDataTransferSpeedView mDataTransferSpeedView;

    public DataTransferSpeedPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                      @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                      DataTransferSpeed dataTransferSpeed) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
        this.dataTransferSpeed = dataTransferSpeed;
    }

    @Override
    public void registerView(IDataTransferSpeedView activity) {
        mDataTransferSpeedView = activity;
    }

    @Override
    public void getConversionFromBitsPerSecondResults(String bps, int decimalPlaces) {
        Observable.just(dataTransferSpeed.BitsPerSecond().toAll(bps, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
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
