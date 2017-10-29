package ru.nlcodeteam.typicode.di.module.base;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nlcodeteam.typicode.di.TypicodeService;
import ru.nlcodeteam.typicode.di.TypicodeServicePath;
import ru.nlcodeteam.typicode.di.scope.ApplicationScope;
import rx.schedulers.Schedulers;

/**
 * Created by eldar on 29.10.2017.
 */
@Module(includes = NetworkModule.class)
public class TypicodeServiceModule {
    @Provides
    @ApplicationScope
    public TypicodeService githubService(Retrofit retrofit) {
        return retrofit.create(TypicodeService.class);
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, RxJavaCallAdapterFactory rxAdapter) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(TypicodeServicePath.BASE_URL)
                .addCallAdapterFactory(rxAdapter)
                .build();
    }

    @Provides
    @ApplicationScope
    public RxJavaCallAdapterFactory factory() {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }
}
