package com.bubbinator91.converter.mock.views;

import com.bubbinator91.converter.interfaces.view.IAccelerationView;
import com.bubbinator91.converter.models.AccelerationModel;
import com.bubbinator91.converter.models.AccelerationModel.AccelerationUnits;

import java.util.List;

public class MockAccelerationView implements IAccelerationView {

    public String cmpssValue = null, fpssValue = null, mpssValue = null, sgValue = null;
    public boolean cmpssError = false, fpssError = false, mpssError = false, sgError = false;

    @Override
    public void showNewValuesFromModel(AccelerationModel model) {
        cmpssValue = model.getCmpss();
        fpssValue = model.getFpss();
        mpssValue = model.getMpss();
        sgValue = model.getSg();

        cmpssError = false;
        fpssError = false;
        mpssError = false;
        sgError = false;
    }

    @Override
    public void showNewValuesFromModelExcludingSource(AccelerationModel model, AccelerationUnits source) {
        if (source != AccelerationUnits.cmpss) {
            cmpssValue = model.getCmpss();
        }
        if (source != AccelerationUnits.fpss) {
            fpssValue = model.getFpss();
        }
        if (source != AccelerationUnits.mpss) {
            mpssValue = model.getMpss();
        }
        if (source != AccelerationUnits.sg) {
            sgValue = model.getSg();
        }

        cmpssError = false;
        fpssError = false;
        mpssError = false;
        sgError = false;
    }

    @Override
    public void showErrorForSource(Throwable error, AccelerationUnits source) {
        switch (source) {
            case cmpss:
                cmpssError = true;
                fpssValue = null;
                mpssValue = null;
                sgValue = null;
                break;
            case fpss:
                fpssError = true;
                cmpssValue = null;
                mpssValue = null;
                sgValue = null;
                break;
            case mpss:
                mpssError = true;
                cmpssValue = null;
                fpssValue = null;
                sgValue = null;
                break;
            case sg:
                sgError = true;
                cmpssValue = null;
                fpssValue = null;
                mpssValue = null;
                break;
            default:
                break;
        }
    }

    @Override
    public AccelerationModel loadModel() {
        return new AccelerationModel();
    }

    @Override
    public void saveModel(AccelerationModel model) {
        // No relevant implementation for testing
    }

    public void resetValues() {
        cmpssValue = null;
        fpssValue = null;
        mpssValue = null;
        sgValue = null;

        cmpssError = false;
        fpssError = false;
        mpssError = false;
        sgError = false;
    }
}
