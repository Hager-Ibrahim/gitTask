package com.example.githublocaldb.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import com.example.githublocaldb.data.local.GithubDao;
import com.example.githublocaldb.data.model.GithubBasicResponse;
import com.example.githublocaldb.data.model.GithubInfoResponse;
import com.example.githublocaldb.data.remote.GithubApi;
import com.example.githublocaldb.di.module.ExecutorModule;
import com.example.githublocaldb.utils.ApiResponse;
import com.example.githublocaldb.utils.NetworkBoundResource;
import com.example.githublocaldb.utils.Resource;

import java.util.List;

import javax.inject.Inject;

public class GithubListRepo {

    private GithubDao githubDao;
    private GithubApi githubApi;

    @Inject
    public GithubListRepo(GithubDao githubDao, GithubApi githubApi) {
        this.githubDao = githubDao;
        this.githubApi = githubApi;
    }

    public LiveData<Resource<List<GithubInfoResponse>>> searchUsers(String query){
        return new NetworkBoundResource<List<GithubInfoResponse>, GithubBasicResponse>(ExecutorModule.getInstance()) {
            @Override
            public void saveCallResult(@NonNull GithubBasicResponse item) {
                if(item.getItems() != null){
                    //List<GithubInfoResponse> githubList = item.getItems();
                    GithubInfoResponse[] githubInfoResponses = new GithubInfoResponse[item.getItems().size()];
                    githubDao.insertAllGithubInfo(item.getItems().toArray(githubInfoResponses));
                }
            }

            @Override
            public boolean shouldFetch(@Nullable List<GithubInfoResponse> data) {
                return true;
            }

            @NonNull
            @Override
            public LiveData<List<GithubInfoResponse>> loadFromDb() {
                return githubDao.searchGithubs(query);
            }

            @NonNull
            @Override
            public LiveData<ApiResponse<GithubBasicResponse>> createCall() {
                return githubApi.getRepo(query);
            }
        }.getAsLiveData();
    }
}
