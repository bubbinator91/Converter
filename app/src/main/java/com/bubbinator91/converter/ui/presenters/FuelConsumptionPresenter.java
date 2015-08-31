package com.bubbinator91.converter.ui.presenters;

import com.bubbinator91.conversion.fuelconsumption.FuelConsumption;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionPresenter;
import com.bubbinator91.converter.ui.interfaces.fuelconsumption.IFuelConsumptionView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link IFuelConsumptionPresenter} interface for the
 * {@link com.bubbinator91.converter.ui.fragments.FuelConsumptionFragment}
 */
public class FuelConsumptionPresenter implements IFuelConsumptionPresenter {

    private IFuelConsumptionView mFuelConsumptionView;

    private final FuelConsumption fuelConsumption;

    public FuelConsumptionPresenter(FuelConsumption fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public void registerView(IFuelConsumptionView activity) {
        mFuelConsumptionView = activity;
    }

    @Override
    public void getConversionFromKilometersPerLiterResults(String kpl, int decimalPlaces) {
        Observable.just(fuelConsumption.KilometersPerLiter().toAll(kpl, decimalPlaces))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
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
