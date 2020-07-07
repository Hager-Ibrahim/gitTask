package com.example.githublocaldb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githublocaldb.R;
import com.example.githublocaldb.data.model.GithubInfoResponse;


import java.util.ArrayList;
import java.util.List;

public class GithubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<GithubInfoResponse> list;

    private static final int GITHUB_LIST_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int EXHAUSTED_TYPE = 3;

    public GithubAdapter(){
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType){
            case GITHUB_LIST_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.github_list_item, parent, false);
                return new GithubListViewHolder(view);
            }

            case LOADING_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false);
                return new LoadingViewHolder(view);
            }
            case EXHAUSTED_TYPE:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_exhausted, parent, false);
                return new LoadingViewHolder(view);
            }
            default:{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.github_list_item, parent, false);
                return new GithubListViewHolder(view);
            }
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if(getItemViewType(position) == GITHUB_LIST_TYPE ){
            RequestOptions requestOptions = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.white_background)
                    .error(R.drawable.white_background);

            Glide.with(((GithubListViewHolder) holder).itemView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(list.get(position).getOwner().getAvatarUrl())
                    .into(((GithubListViewHolder) holder).githubImg);

            ((GithubListViewHolder) holder).textView.setText(list.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), list.get(position).getName(), Toast.LENGTH_LONG).show();
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {

         if(list.get(position).getName().equals("LOADING...")){
            return LOADING_TYPE;
        }
         else if(list.get(position).getName().equals("EXHAUSTED...")){
             return EXHAUSTED_TYPE;
         }
        else{
            return GITHUB_LIST_TYPE;
        }
    }

    public void displayLoading(){
        if(!isLoading()){
            GithubInfoResponse github = new GithubInfoResponse();
            github.setName("LOADING...");
            List<GithubInfoResponse> loadingList = new ArrayList<>();
            loadingList.add(github);
            list = loadingList;
            notifyDataSetChanged();
        }
    }

    public void hideLoading(){
        if(isLoading()){
            for(GithubInfoResponse githubInfoResponse : list){
                if(githubInfoResponse.getName().equals("LOADING...")){
                    list.remove(githubInfoResponse);
                }
            }
            notifyDataSetChanged();
        }
    }

    private boolean isLoading(){
        if(list != null){
            if(list.size() > 0){
                if(list.get(list.size() - 1).getName().equals("LOADING...")){
                    return true;
                }
            }
        }
        return false;
    }

    public void setQueryExhausted(){
        hideLoading();
        GithubInfoResponse exhaustedGit = new GithubInfoResponse();
        exhaustedGit.setName("EXHAUSTED...");
        list.add(exhaustedGit);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<GithubInfoResponse> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
