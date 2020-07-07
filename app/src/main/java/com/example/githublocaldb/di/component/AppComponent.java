package com.example.githublocaldb.di.component;

import android.content.Context;


import com.example.githublocaldb.data.local.GithubDao;
import com.example.githublocaldb.di.module.ApiModule;
import com.example.githublocaldb.di.module.AppModule;
import com.example.githublocaldb.di.module.DaoModule;
import com.example.githublocaldb.di.module.ExecutorModule;
import com.example.githublocaldb.di.module.NetModule;
import com.example.githublocaldb.di.module.RepositoryModule;
import com.example.githublocaldb.ui.GithubListActivity;
import com.example.githublocaldb.ui.GithubListViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {AppModule.class, NetModule.class, RepositoryModule.class, ApiModule.class, DaoModule.class, ExecutorModule.class}
)
public interface AppComponent {

    public void inject(Context content);
    public void inject(GithubListViewModel content);

}
