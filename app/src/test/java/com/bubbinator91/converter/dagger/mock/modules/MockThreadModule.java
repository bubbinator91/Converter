package com.bubbinator91.converter.dagger.mock.modules;

import com.bubbinator91.converter.dagger.modules.ThreadModule;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class MockThreadModule extends ThreadModule {
    @Override
    public Scheduler providesComputationThread() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler providesMainThread() {
        return Schedulers.immediate();
    }
}
