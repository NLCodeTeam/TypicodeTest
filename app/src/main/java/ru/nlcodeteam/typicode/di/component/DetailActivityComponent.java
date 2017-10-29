package ru.nlcodeteam.typicode.di.component;

import dagger.Component;
import ru.nlcodeteam.typicode.di.module.activity.DetailActivityModule;
import ru.nlcodeteam.typicode.di.scope.DetailActivityScope;
import ru.nlcodeteam.typicode.view.DetailActivity;

/**
 * Created by eldar on 29.10.2017.
 */
@DetailActivityScope
@Component(modules = DetailActivityModule.class, dependencies = ApplicationComponent.class)
public interface DetailActivityComponent {
    void injectDetailActivity(DetailActivity activity);
}


