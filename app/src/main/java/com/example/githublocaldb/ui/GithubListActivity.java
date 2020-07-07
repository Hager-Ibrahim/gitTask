package com.example.githublocaldb.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.util.LogTime;
import com.example.githublocaldb.R;
import com.example.githublocaldb.adapter.GithubAdapter;
import com.example.githublocaldb.data.model.GithubInfoResponse;
import com.example.githublocaldb.utils.MyApplication;
import com.example.githublocaldb.utils.Resource;

import java.util.List;

public class GithubListActivity extends AppCompatActivity {

    GithubListViewModel modelView;
    RecyclerView recyclerView;
    GithubAdapter githubAdapter;

    private static final String TAG = "GithubListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_list);

        //get an instance of our viewmodel and get things injected...
        modelView = GithubListViewModel.create(this);
        MyApplication.getAppComponent().inject(modelView);

        githubAdapter = new GithubAdapter();

        initRecyclerView();
        initSearchView();
        subscribeObservers();

    }

    private void subscribeObservers(){
        modelView.getGithubList().observe(this, new Observer<Resource<List<GithubInfoResponse>>>() {
            @Override
            public void onChanged(Resource<List<GithubInfoResponse>> listResource) {

                if(listResource != null){
                    Log.d(TAG, "onChanged status: " + listResource.status);
                    if(listResource.data != null){

                        switch (listResource.status){
                            case LOADING:
                                githubAdapter.displayLoading();
                                break;

                            case ERROR:
                                githubAdapter.hideLoading();
                                githubAdapter.setList(listResource.data);
                                Toast.makeText(GithubListActivity.this, listResource.message, Toast.LENGTH_SHORT).show();
                                break;

                            case SUCCESS:
                                githubAdapter.setList(listResource.data);
                        }
                    }
                }
            }
        });
    }


    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                modelView.searchGithubList(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                // Wait for the user to submit the search. So do nothing here.

                return false;
            }
        });
    }

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(githubAdapter);
    }
}
