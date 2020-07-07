package com.example.githublocaldb.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githublocaldb.R;


public class GithubListViewHolder extends RecyclerView.ViewHolder {

    public AppCompatImageView githubImg;
    public TextView textView;

    public GithubListViewHolder(@NonNull View itemView) {
        super(itemView);

         githubImg  = itemView.findViewById(R.id.githubImageView);
         textView = itemView.findViewById(R.id.githubTextView);
    }


}
