package ru.nlcodeteam.typicode.di.module.activity;

import dagger.Module;
import dagger.Provides;
import ru.nlcodeteam.typicode.di.scope.DetailActivityScope;
import ru.nlcodeteam.typicode.di.scope.MainActivityScope;
import ru.nlcodeteam.typicode.view.DetailActivity;

/**
 * Created by eldar on 29.10.2017.
 */
@Module
public class DetailActivityModule {
    private final DetailActivity activity;

    public DetailActivityModule(DetailActivity activity) {
        this.activity = activity;
    }

    @Provides
    @DetailActivityScope
    public DetailActivity getActivity() {
        return activity;
    }
}
