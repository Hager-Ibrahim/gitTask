package com.example.githublocaldb.utils;

import android.app.Application;

import com.example.githublocaldb.di.component.AppComponent;
import com.example.githublocaldb.di.component.DaggerAppComponent;
import com.example.githublocaldb.di.module.ApiModule;
import com.example.githublocaldb.di.module.AppModule;
import com.example.githublocaldb.di.module.NetModule;
import com.example.githublocaldb.di.module.RepositoryModule;


public class MyApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void onCreate(){
        super.onCreate();

        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                apiModule(new ApiModule()).
                netModule(new NetModule("https://api.github.com")).
                repositoryModule(new RepositoryModule()).
                build();

    }
}
