package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.datatransferspeed.BitsPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.BytesPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.GigabitsPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.GigabytesPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.KilobitsPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.KilobytesPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.MegabitsPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.MegabytesPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.TerabitsPerSecond;
import com.bubbinator91.converter.conversion.datatransferspeed.TerabytesPerSecond;
import com.bubbinator91.converter.interfaces.presenter.IDataTransferSpeedPresenter;
import com.bubbinator91.converter.interfaces.view.IDataTransferSpeedView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link IDataTransferSpeedPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.DataTransferSpeedFragment}
 */
public class DataTransferSpeedPresenter implements IDataTransferSpeedPresenter {

    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private IDataTransferSpeedView mDataTransferSpeedView;

    public DataTransferSpeedPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                      @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(IDataTransferSpeedView activity) {
        mDataTransferSpeedView = activity;
    }

    @Override
    public void getConversionFromBitsPerSecond(String bps, int decimalPlaces) {
        BitsPerSecond.toAll(bps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromBitsPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromBitsPerSecondError
                );
    }

    @Override
    public void getConversionFromBytesPerSecond(String byps, int decimalPlaces) {
        BytesPerSecond.toAll(byps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromBytesPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromBytesPerSecondError
                );
    }

    @Override
    public void getConversionFromKilobitsPerSecond(String kbps, int decimalPlaces) {
        KilobitsPerSecond.toAll(kbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromKilobitsPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromKilobitsPerSecondError
                );
    }

    @Override
    public void getConversionFromKilobytesPerSecond(String kbyps, int decimalPlaces) {
        KilobytesPerSecond.toAll(kbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromKilobytesPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromKilobytesPerSecondError
                );
    }

    @Override
    public void getConversionFromMegabitsPerSecond(String mbps, int decimalPlaces) {
        MegabitsPerSecond.toAll(mbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromMegabitsPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromMegabitsPerSecondError
                );
    }

    @Override
    public void getConversionFromMegabytesPerSecond(String mbyps, int decimalPlaces) {
        MegabytesPerSecond.toAll(mbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromMegabytesPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromMegabytesPerSecondError
                );
    }

    @Override
    public void getConversionFromGigabitsPerSecond(String gbps, int decimalPlaces) {
        GigabitsPerSecond.toAll(gbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromGigabitsPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromGigabitsPerSecondError
                );
    }

    @Override
    public void getConversionFromGigabytesPerSecond(String gbyps, int decimalPlaces) {
        GigabytesPerSecond.toAll(gbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromGigabytesPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromGigabytesPerSecondError
                );
    }

    @Override
    public void getConversionFromTerabitsPerSecond(String tbps, int decimalPlaces) {
        TerabitsPerSecond.toAll(tbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromTerabitsPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromTerabitsPerSecondError
                );
    }

    @Override
    public void getConversionFromTerabytesPerSecond(String tbyps, int decimalPlaces) {
        TerabytesPerSecond.toAll(tbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mDataTransferSpeedView::displayConversionFromTerabytesPerSecondResults,
                        mDataTransferSpeedView::displayConversionFromTerabytesPerSecondError
                );
    }
}
