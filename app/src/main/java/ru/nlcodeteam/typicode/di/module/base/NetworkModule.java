package ru.nlcodeteam.typicode.di.module.base;

import android.content.Context;
import android.util.Log;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import ru.nlcodeteam.typicode.di.scope.ApplicationContext;
import ru.nlcodeteam.typicode.di.scope.ApplicationScope;

/**
 * Created by eldar on 29.10.2017.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {
    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("Typicode",message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    @Provides
    @ApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10MB Cahe
    }

    @Provides
    @ApplicationScope
    public File cacheFile(@ApplicationContext Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }
}
