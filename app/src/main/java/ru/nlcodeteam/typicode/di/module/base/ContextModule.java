package ru.nlcodeteam.typicode.di.module.base;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.nlcodeteam.typicode.di.scope.ApplicationContext;
import ru.nlcodeteam.typicode.di.scope.ApplicationScope;

/**
 * Created by eldar on 29.10.2017.
 */

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context context() {
        return context;
    }
}
