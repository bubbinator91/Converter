package com.bubbinator91.converter.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * An implementation of {@link Scope} that determines the length to retain things in the scope of an
 * activity.
 */
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ActivityScope {}
