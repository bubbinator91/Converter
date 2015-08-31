package com.bubbinator91.converter.dagger.modules;

import com.bubbinator91.converter.util.Globals;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ThreadModule {
    @Provides
    @Named(Globals.DAGGER_COMPUTATION_THREAD)
    public Scheduler providesComputationThread() {
        return Schedulers.computation();
    }

    @Provides
    @Named(Globals.DAGGER_MAIN_THREAD)
    public Scheduler providesMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
