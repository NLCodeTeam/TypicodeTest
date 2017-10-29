package ru.nlcodeteam.typicode.di.module.activity;

import dagger.Module;
import dagger.Provides;
import ru.nlcodeteam.typicode.di.scope.PhotoActivityScope;
import ru.nlcodeteam.typicode.view.PhotoActivity;

/**
 * Created by eldar on 29.10.2017.
 */
@Module
public class PhotoActivityModule {
    private final PhotoActivity activity;

    public PhotoActivityModule(PhotoActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PhotoActivityScope
    public PhotoActivity getActivity() {
        return activity;
    }
}
