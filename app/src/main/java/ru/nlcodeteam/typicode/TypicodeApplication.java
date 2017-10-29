package ru.nlcodeteam.typicode;

import android.app.Activity;
import android.app.Application;

import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.component.ApplicationComponent;
import ru.nlcodeteam.typicode.di.component.DaggerApplicationComponent;
import ru.nlcodeteam.typicode.di.module.base.ContextModule;

/**
 * Created by eldar on 29.10.2017.
 */

public class TypicodeApplication extends Application {
    private ApplicationComponent component;

    public static TypicodeApplication get(Activity activity) {
        return (TypicodeApplication) activity.getApplication();
    }

    private TypicodeService typicodeService;


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        typicodeService = component.getService();

    }

    public ApplicationComponent component() {
        return component;
    }
}
