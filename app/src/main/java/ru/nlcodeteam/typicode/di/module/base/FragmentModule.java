package ru.nlcodeteam.typicode.di.module.base;

import android.app.Fragment;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.nlcodeteam.typicode.di.scope.ApplicationScope;

/**
 * Created by eldar on 29.10.2017.
 */
@Module
public class FragmentModule {

    private final Fragment context;

        public FragmentModule(Fragment context) {
            this.context = context;
        }

        @Provides
        @ApplicationScope
        @Named("fragment_context")
        public Context context() {
            return context.getActivity();
        }

}
