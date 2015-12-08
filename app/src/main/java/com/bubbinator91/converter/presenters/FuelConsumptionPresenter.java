package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.fuelconsumption.KilometersPerLiter;
import com.bubbinator91.converter.conversion.fuelconsumption.LitersPer100Kilometers;
import com.bubbinator91.converter.conversion.fuelconsumption.UKMilesPerGallon;
import com.bubbinator91.converter.conversion.fuelconsumption.USMilesPerGallon;
import com.bubbinator91.converter.interfaces.presenter.IFuelConsumptionPresenter;
import com.bubbinator91.converter.interfaces.view.IFuelConsumptionView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link IFuelConsumptionPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment}
 */
public class FuelConsumptionPresenter implements IFuelConsumptionPresenter {
    private final Scheduler mainScheduler;
    private final Scheduler computationScheduler;

    private IFuelConsumptionView mFuelConsumptionView;


    public FuelConsumptionPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                    @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void registerView(IFuelConsumptionView activity) {
        mFuelConsumptionView = activity;
    }

    @Override
    public void getConversionFromKilometersPerLiter(String kpl, int decimalPlaces) {
        KilometersPerLiter.toAll(kpl, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromKilometersPerLiterResults,
                        mFuelConsumptionView::displayConversionFromKilometersPerLiterError
                );
    }

    @Override
    public void getConversionFromLitersPer100Kilometers(String l100km, int decimalPlaces) {
        LitersPer100Kilometers.toAll(l100km, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromLitersPer100KilometersResults,
                        mFuelConsumptionView::displayConversionFromLitersPer100KilometersError
                );
    }

    @Override
    public void getConversionFromUKMilesPerGallon(String ukmpg, int decimalPlaces) {
        UKMilesPerGallon.toAll(ukmpg, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromUKMilesPerGallonResults,
                        mFuelConsumptionView::displayConversionFromUKMilesPerGallonError
                );
    }

    @Override
    public void getConversionFromUSMilesPerGallon(String usmpg, int decimalPlaces) {
        USMilesPerGallon.toAll(usmpg, decimalPlaces)
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .filter(conversionResults -> conversionResults != null)
                .subscribe(
                        mFuelConsumptionView::displayConversionFromUSMilesPerGallonResults,
                        mFuelConsumptionView::displayConversionFromUSMilesPerGallonError
                );
    }
}
