package com.example.githublocaldb.data.remote;

import androidx.lifecycle.LiveData;

import com.example.githublocaldb.data.model.GithubBasicResponse;
import com.example.githublocaldb.utils.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubApi {

    @GET("search/repositories")
    LiveData<ApiResponse<GithubBasicResponse>> getRepo(@Query("q") String searchKeyword);


}
