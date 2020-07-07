package com.example.githublocaldb.di.module;




import com.example.githublocaldb.data.remote.GithubApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public GithubApi providesCatalogApi(Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }
}
