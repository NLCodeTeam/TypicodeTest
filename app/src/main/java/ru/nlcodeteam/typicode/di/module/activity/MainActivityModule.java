package ru.nlcodeteam.typicode.di.module.activity;

import dagger.Module;
import dagger.Provides;
import ru.nlcodeteam.typicode.di.scope.MainActivityScope;
import ru.nlcodeteam.typicode.view.MainActivity;

/**
 * Created by eldar on 29.10.2017.
 */
@Module
public class MainActivityModule {
    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainActivityScope
    public MainActivity getActivity() {
        return activity;
    }
}
