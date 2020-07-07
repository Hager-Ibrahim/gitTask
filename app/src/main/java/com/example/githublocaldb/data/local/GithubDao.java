package com.example.githublocaldb.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.githublocaldb.data.model.GithubInfoResponse;

import java.util.List;


@Dao
public interface GithubDao {

    @Query("SELECT * FROM githubInfo WHERE name LIKE '%' || :query || '%'" +
            " OR " + " fullName LIKE '%' || :query || '%' OR " + " description LIKE '%' || :query || '%'" )
    LiveData<List<GithubInfoResponse>> searchGithubs(String query);


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    long[] insertAllGithubInfo(GithubInfoResponse... githubInfoResponses);
}
