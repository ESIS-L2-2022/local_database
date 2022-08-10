package com.yveskalume.localdb;


import com.yveskalume.localdb.database.model.GithubRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {

    @GET("users/yveskalume/repos")
    Call<List<GithubRepository>> getRepositories();
}
