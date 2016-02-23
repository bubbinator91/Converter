package com.bubbinator91.converter.util;

import android.os.Bundle;

import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import timber.log.Timber;

public class PresenterCache {
    private final String TAG = PresenterCache.class.getSimpleName();
    private static final String PRESENTER_STORAGE_KEY = "presenter_storage_id";
    private static PresenterCache instance;

    private final AtomicLong currentPresenterId;
    private final Cache<Long, IConverterPresenter<?>> presenterCache;

    private PresenterCache(long maxSize, long expirationValue, TimeUnit expirationUnit) {
        currentPresenterId = new AtomicLong();

        presenterCache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expirationValue, expirationUnit)
                .build();
    }

    public static PresenterCache getInstance() {
        if (instance == null) {
            instance = new PresenterCache(10, 30, TimeUnit.SECONDS);
        }

        return instance;
    }

    @SuppressWarnings("unchecked")
    public <P extends IConverterPresenter<?>> P restorePresenter(Bundle savedInstanceState) {
        Timber.tag(TAG + ".restorePresenter").i("Entered");
        Long presenterId = savedInstanceState.getLong(PRESENTER_STORAGE_KEY);
        P presenter = (P) presenterCache.getIfPresent(presenterId);
        presenterCache.invalidate(presenterId);
        return presenter;
    }

    public void savePresenter(IConverterPresenter<?> presenter, Bundle outState) {
        Timber.tag(TAG + ".savePresenter").i("Entered");
        long presenterId = currentPresenterId.incrementAndGet();
        presenterCache.put(presenterId, presenter);
        outState.putLong(PRESENTER_STORAGE_KEY, presenterId);
    }
}
