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

    private IDataTransferSpeedView dataTransferSpeedView;

    public DataTransferSpeedPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                      @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(IDataTransferSpeedView view) {
        dataTransferSpeedView = view;
    }
    
    @Override
    public void unregisterView() {
        dataTransferSpeedView = null;
    }

    @Override
    public void getConversionFromBitsPerSecond(String bps, int decimalPlaces) {
        BitsPerSecond.toAll(bps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromBitsPerSecondResults,
                        dataTransferSpeedView::displayConversionFromBitsPerSecondError
                );
    }

    @Override
    public void getConversionFromBytesPerSecond(String byps, int decimalPlaces) {
        BytesPerSecond.toAll(byps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromBytesPerSecondResults,
                        dataTransferSpeedView::displayConversionFromBytesPerSecondError
                );
    }

    @Override
    public void getConversionFromKilobitsPerSecond(String kbps, int decimalPlaces) {
        KilobitsPerSecond.toAll(kbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromKilobitsPerSecondResults,
                        dataTransferSpeedView::displayConversionFromKilobitsPerSecondError
                );
    }

    @Override
    public void getConversionFromKilobytesPerSecond(String kbyps, int decimalPlaces) {
        KilobytesPerSecond.toAll(kbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromKilobytesPerSecondResults,
                        dataTransferSpeedView::displayConversionFromKilobytesPerSecondError
                );
    }

    @Override
    public void getConversionFromMegabitsPerSecond(String mbps, int decimalPlaces) {
        MegabitsPerSecond.toAll(mbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromMegabitsPerSecondResults,
                        dataTransferSpeedView::displayConversionFromMegabitsPerSecondError
                );
    }

    @Override
    public void getConversionFromMegabytesPerSecond(String mbyps, int decimalPlaces) {
        MegabytesPerSecond.toAll(mbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromMegabytesPerSecondResults,
                        dataTransferSpeedView::displayConversionFromMegabytesPerSecondError
                );
    }

    @Override
    public void getConversionFromGigabitsPerSecond(String gbps, int decimalPlaces) {
        GigabitsPerSecond.toAll(gbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromGigabitsPerSecondResults,
                        dataTransferSpeedView::displayConversionFromGigabitsPerSecondError
                );
    }

    @Override
    public void getConversionFromGigabytesPerSecond(String gbyps, int decimalPlaces) {
        GigabytesPerSecond.toAll(gbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromGigabytesPerSecondResults,
                        dataTransferSpeedView::displayConversionFromGigabytesPerSecondError
                );
    }

    @Override
    public void getConversionFromTerabitsPerSecond(String tbps, int decimalPlaces) {
        TerabitsPerSecond.toAll(tbps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromTerabitsPerSecondResults,
                        dataTransferSpeedView::displayConversionFromTerabitsPerSecondError
                );
    }

    @Override
    public void getConversionFromTerabytesPerSecond(String tbyps, int decimalPlaces) {
        TerabytesPerSecond.toAll(tbyps, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        dataTransferSpeedView::displayConversionFromTerabytesPerSecondResults,
                        dataTransferSpeedView::displayConversionFromTerabytesPerSecondError
                );
    }
}
