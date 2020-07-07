package com.example.githublocaldb.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.githublocaldb.data.model.GithubInfoResponse;


@Database(entities = {GithubInfoResponse.class}, version = 2)
public abstract class GithubDatabase extends RoomDatabase {
    public abstract GithubDao githubDao();
}
