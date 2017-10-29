package ru.nlcodeteam.typicode.di.scope;

/**
 * Created by eldar on 29.10.2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
