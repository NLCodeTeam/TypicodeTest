package ru.nlcodeteam.typicode.di.module.fragment;

import dagger.Module;
import dagger.Provides;
import ru.nlcodeteam.typicode.di.scope.MainActivityScope;
import ru.nlcodeteam.typicode.view.fragment.PostFragment;

/**
 * Created by eldar on 29.10.2017.
 */
@Module
public class PostFragmentModule {
    private final PostFragment fragment;

    public PostFragmentModule(PostFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @MainActivityScope
    public PostFragment getActivity() {
        return fragment;
    }
}
