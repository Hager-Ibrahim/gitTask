package com.example.githublocaldb.ui;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.githublocaldb.data.model.GithubInfoResponse;
import com.example.githublocaldb.utils.Resource;

import java.util.List;

import javax.inject.Inject;

public class GithubListViewModel extends ViewModel {


    private MediatorLiveData<Resource<List<GithubInfoResponse>>> githubList = new MediatorLiveData<>();
    private GithubListRepo repository ;

    public static GithubListViewModel create(FragmentActivity activity) {
        GithubListViewModel viewModel = ViewModelProviders.of(activity).get(GithubListViewModel.class);
        return viewModel;
    }

    @Inject
    public void setRepository(GithubListRepo repository) {
        this.repository = repository;
    }

    public void searchGithubList(String query){
       LiveData<Resource<List<GithubInfoResponse>>> repositorySource =  repository.searchUsers(query);
       githubList.addSource(repositorySource, new Observer<Resource<List<GithubInfoResponse>>>() {
           @Override
           public void onChanged(Resource<List<GithubInfoResponse>> listResource) {
                githubList.setValue(listResource);
           }
       });
    }

    public LiveData<Resource<List<GithubInfoResponse>>> getGithubList(){
        return githubList;
    }
}
