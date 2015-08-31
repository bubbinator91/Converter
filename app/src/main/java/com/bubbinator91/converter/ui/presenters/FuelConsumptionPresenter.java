package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.fuelconsumption.FuelConsumption;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionPresenter;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionView;
import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        Observable.just(fuelConsumption.KilometersPerLiter().toAll(kpl, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mFuelConsumptionView
                                .displayConversionFromKilometersPerLiterResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromLitersPer100KilometersResults(String l100k, int decimalPlaces) {
        Observable.just(fuelConsumption.LitersPer100Kilometers().toAll(l100k, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mFuelConsumptionView
                                .displayConversionFromLitersPer100KilometersResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromUKMilesPerGallonResults(String ukmpg, int decimalPlaces) {
        Observable.just(fuelConsumption.UKMilesPerGallon().toAll(ukmpg, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mFuelConsumptionView
                                .displayConversionFromUKMilesPerGallonResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }

    @Override
    public void getConversionFromUSMilesPerGallonResults(String usmpg, int decimalPlaces) {
        Observable.just(fuelConsumption.USMilesPerGallon().toAll(usmpg, decimalPlaces))
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(conversionResults -> {
                    if (conversionResults != null) {
                        mFuelConsumptionView
                                .displayConversionFromUSMilesPerGallonResults(
                                        conversionResults.getFirst(),
                                        conversionResults.getSecond()
                                );
                    }
                });
    }
}
