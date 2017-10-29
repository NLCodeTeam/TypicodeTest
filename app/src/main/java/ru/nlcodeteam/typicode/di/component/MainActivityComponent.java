package ru.nlcodeteam.typicode.di.component;

import dagger.Component;
import ru.nlcodeteam.typicode.di.module.activity.MainActivityModule;
import ru.nlcodeteam.typicode.di.scope.MainActivityScope;
import ru.nlcodeteam.typicode.view.MainActivity;

/**
 * Created by eldar on 29.10.2017.
 */
@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void injectMainActivity(MainActivity activity);
}


