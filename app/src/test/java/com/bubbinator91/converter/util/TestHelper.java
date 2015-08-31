package com.bubbinator91.converter.util;

import com.bubbinator91.converter.dagger.components.DaggerTestClassInjector;
import com.bubbinator91.converter.dagger.components.TestClassInjector;
import com.bubbinator91.converter.dagger.mock.modules.MockThreadModule;
import com.bubbinator91.converter.dagger.modules.ConversionModule;
import com.bubbinator91.converter.dagger.modules.PresenterModule;

public class TestHelper {
    private static TestClassInjector sTestClassInjector;

    public static TestClassInjector getTestClassInjector() {
        if (sTestClassInjector == null) {
            sTestClassInjector = DaggerTestClassInjector
                    .builder()
                    .conversionModule(new ConversionModule())
                    .presenterModule(new PresenterModule())
                    .threadModule(new MockThreadModule())
                    .build();
        }

        return sTestClassInjector;
    }

    public static void waitFor(IWaitingCallback callback) {
        waitFor(10, callback);
    }

    public static void waitFor(int maxCycles, IWaitingCallback callback) {
        while(true){
            maxCycles --;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(callback.checkCondition()){
                break;
            }
            if(maxCycles <= 0){
                break;
            }
        }
    }

    public interface IWaitingCallback {
        /**
         *
         * @return true if condition is met, false if we should keep waiting
         */
        boolean checkCondition();
    }
}
