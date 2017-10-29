package ru.nlcodeteam.typicode.di.component;

import dagger.Component;
import ru.nlcodeteam.typicode.di.module.fragment.AlbumFragmentModule;
import ru.nlcodeteam.typicode.di.scope.AlbumsFragmentScope;
import ru.nlcodeteam.typicode.view.fragment.AlbumFragment;

/**
 * Created by eldar on 29.10.2017.
 */
@AlbumsFragmentScope
@Component(modules = AlbumFragmentModule.class, dependencies = ApplicationComponent.class)
public interface AlbumFragmentComponent {
    void injectAlbumFragment(AlbumFragment fragment);
}


