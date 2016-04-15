package com.bubbinator91.converter.presenters;

import com.bubbinator91.converter.conversion.acceleration.CentimetersPerSecondSquared;
import com.bubbinator91.converter.conversion.acceleration.FeetPerSecondSquared;
import com.bubbinator91.converter.conversion.acceleration.MetersPerSecondSquared;
import com.bubbinator91.converter.conversion.acceleration.StandardGravity;
import com.bubbinator91.converter.interfaces.presenter.IAccelerationPresenter;
import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.models.AccelerationModel;
import com.bubbinator91.converter.models.AccelerationModel.AccelerationUnits;
import com.bubbinator91.converter.presenters.base.BasePresenter;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.views.fragments.AccelerationFragment;

import java.util.List;

import javax.inject.Named;

import rx.Scheduler;

/**
 * Implementation of the {@link IAccelerationPresenter} interface for the
 * {@link AccelerationFragment}
 */
public class AccelerationPresenter
        extends BasePresenter<AccelerationModel, IAccelerationView, AccelerationUnits>
        implements IAccelerationPresenter {
    private final Scheduler mainScheduler, computationScheduler;

    public AccelerationPresenter(@Named(Globals.DAGGER_MAIN_THREAD) Scheduler mainScheduler,
                                 @Named(Globals.DAGGER_COMPUTATION_THREAD) Scheduler computationScheduler) {
        this.mainScheduler = mainScheduler;
        this.computationScheduler = computationScheduler;
    }

    @Override
    public void onResume() {
        setModel(getView().loadModel());
    }

    @Override
    public void onPause() {
        getView().saveModel(getModel());
    }

    @Override
    protected void updateModel(List<String> values) {
        getModel().setCmpss(values.get(0));
        getModel().setFpss(values.get(1));
        getModel().setMpss(values.get(2));
        getModel().setSg(values.get(3));
    }

    @Override
    protected void updateView() {
        getView().showNewValuesFromModel(getModel());
    }

    @Override
    protected void updateViewExceptSource(AccelerationUnits source) {
        getView().showNewValuesFromModelExcludingSource(getModel(), source);
    }

    @Override
    protected void updateViewForError(Throwable error, AccelerationUnits source) {
        getView().showErrorForSource(error, source);
    }

    @Override
    public void registerView(IAccelerationView view) {
        setView(view);
    }
    
    @Override
    public void unregisterView() {
        unsetView();
    }

    @Override
    public void afterCmpssTextChanged(String cmpss, int decimalPlaces) {
        CentimetersPerSecondSquared.toAll(cmpss, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(0, cmpss);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(AccelerationUnits.cmpss);
                }, error -> {
                    updateViewForError(error, AccelerationUnits.cmpss);
                });
    }

    @Override
    public void afterFpssTextChanged(String fpss, int decimalPlaces) {
        FeetPerSecondSquared.toAll(fpss, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(1, fpss);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(AccelerationUnits.fpss);
                }, error -> {
                    updateViewForError(error, AccelerationUnits.fpss);
                });
    }

    @Override
    public void afterMpssTextChanged(String mpss, int decimalPlaces) {
        MetersPerSecondSquared.toAll(mpss, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(2, mpss);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(AccelerationUnits.mpss);
                }, error -> {
                    updateViewForError(error, AccelerationUnits.mpss);
                });
    }

    @Override
    public void afterSgTextChanged(String sg, int decimalPlaces) {
        StandardGravity.toAll(sg, decimalPlaces)
                .filter(resultsList -> resultsList != null)
                .map(resultsList -> {
                    resultsList.add(3, sg);
                    updateModel(resultsList);
                    return null;
                })
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(notUsed -> {
                    updateViewExceptSource(AccelerationUnits.sg);
                }, error -> {
                    updateViewForError(error, AccelerationUnits.sg);
                });
    }
}
