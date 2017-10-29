package ru.nlcodeteam.typicode.di.component;

import dagger.Component;
import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.module.base.ActivityModule;
import ru.nlcodeteam.typicode.di.module.base.FragmentModule;
import ru.nlcodeteam.typicode.di.module.base.TypicodeServiceModule;
import ru.nlcodeteam.typicode.di.scope.ApplicationScope;


/**
 * Created by eldar on 29.10.2017.
 */

@ApplicationScope
@Component(modules = {TypicodeServiceModule.class, ActivityModule.class, FragmentModule.class})
public interface ApplicationComponent {
    TypicodeService getService();
}
