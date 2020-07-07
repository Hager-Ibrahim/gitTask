package com.example.githublocaldb.di.module;

import android.app.Application;


import androidx.room.Room;

import com.example.githublocaldb.data.local.GithubDao;
import com.example.githublocaldb.data.local.GithubDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    @Provides
    @Singleton
    public GithubDao provideGitHubDao(Application app) {
        GithubDatabase db = Room.databaseBuilder(app,
                GithubDatabase.class, "github-db").build();
        return db.githubDao();
    }
}
