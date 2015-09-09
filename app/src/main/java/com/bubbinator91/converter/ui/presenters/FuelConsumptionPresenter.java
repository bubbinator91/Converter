package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.fuelconsumption.FuelConsumption;
import com.bubbinator91.conversion.util.ValueBelowZeroException;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionPresenter;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionView;
import com.bubbinator91.converter.util.Globals;

import java.util.List;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Implementation of the {@link IFuelConsumptionPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment}
 */
public class FuelConsumptionPresenter implements IFuelConsumptionPresenter {

    private final FuelConsumption fuelConsumption;
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private IFuelConsumptionView mFuelConsumptionView;


    public FuelConsumptionPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                    @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler,
                                    FuelConsumption fuelConsumption) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public void registerView(IFuelConsumptionView activity) {
        mFuelConsumptionView = activity;
    }

    @Override
    public void getConversionFromKilometersPerLiterResults(String kpl, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(fuelConsumption.KilometersPerLiter().toAll(kpl, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromKilometersPerLiterResults,
                        mFuelConsumptionView::displayConversionFromKilometersPerLiterError
                );
    }

    @Override
    public void getConversionFromLitersPer100KilometersResults(String l100k, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(fuelConsumption.LitersPer100Kilometers().toAll(l100k, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromLitersPer100KilometersResults,
                        mFuelConsumptionView::displayConversionFromLitersPer100KilometersError
                );
    }

    @Override
    public void getConversionFromUKMilesPerGallonResults(String ukmpg, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(fuelConsumption.UKMilesPerGallon().toAll(ukmpg, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromUKMilesPerGallonResults,
                        mFuelConsumptionView::displayConversionFromUKMilesPerGallonError
                );
    }

    @Override
    public void getConversionFromUSMilesPerGallonResults(String usmpg, int decimalPlaces) {
        Observable.<List<String>>create(subscriber -> {
            try {
                subscriber.onNext(fuelConsumption.USMilesPerGallon().toAll(usmpg, decimalPlaces));
            } catch (NumberFormatException | ValueBelowZeroException e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromUSMilesPerGallonResults,
                        mFuelConsumptionView::displayConversionFromUSMilesPerGallonError
                );
    }
}
