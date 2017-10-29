package ru.nlcodeteam.typicode.di.module.fragment;

import dagger.Module;
import dagger.Provides;
import ru.nlcodeteam.typicode.di.scope.MainActivityScope;
import ru.nlcodeteam.typicode.view.fragment.AlbumFragment;

/**
 * Created by eldar on 29.10.2017.
 */
@Module
public class AlbumFragmentModule {
    private final AlbumFragment fragment;

    public AlbumFragmentModule(AlbumFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @MainActivityScope
    public AlbumFragment getActivity() {
        return fragment;
    }
}
