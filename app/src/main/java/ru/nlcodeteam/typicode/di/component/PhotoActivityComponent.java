package ru.nlcodeteam.typicode.di.component;

import dagger.Component;
import ru.nlcodeteam.typicode.di.module.activity.PhotoActivityModule;
import ru.nlcodeteam.typicode.di.scope.DetailActivityScope;
import ru.nlcodeteam.typicode.view.PhotoActivity;

/**
 * Created by eldar on 29.10.2017.
 */
@DetailActivityScope
@Component(modules = PhotoActivityModule.class, dependencies = ApplicationComponent.class)
public interface PhotoActivityComponent {
    void injectPhotoActivity(PhotoActivity activity);
}


