package ru.nlcodeteam.typicode.di.component;

import dagger.Component;
import ru.nlcodeteam.typicode.di.module.fragment.PostFragmentModule;
import ru.nlcodeteam.typicode.di.scope.PostsFragmentScope;
import ru.nlcodeteam.typicode.view.fragment.PostFragment;

/**
 * Created by eldar on 29.10.2017.
 */
@PostsFragmentScope
@Component(modules = PostFragmentModule.class, dependencies = ApplicationComponent.class)
public interface PostFragmentComponent {
    void injectPostFragment(PostFragment fragment);
}


