package com.yveskalume.localdb;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.yveskalume.localdb.database.AppDatabase;
import com.yveskalume.localdb.database.model.GithubRepository;
import com.yveskalume.localdb.database.model.Owner;
import com.yveskalume.localdb.database.model.Repository;
import com.yveskalume.localdb.database.model.RepositoryOwner;
import com.yveskalume.localdb.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getData();
    }

    private void getData() {
        binding.progress.setVisibility(View.VISIBLE);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        syncData(db);

        RepositoryOwner repositoryOwners = db.repositoryDao().findOwner();

        binding.userName.setText(repositoryOwners.owner.login);
        binding.repos1.setText(repositoryOwners.repositories.get(0).name);
        binding.repo2.setText(repositoryOwners.repositories.get(1).name);

        binding.progress.setVisibility(View.INVISIBLE);

    }

    private void syncData(AppDatabase db) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService githubService = retrofit.create(GithubService.class);

        Call<List<GithubRepository>> githubCallback = githubService.getRepositories();

        githubCallback.enqueue(new Callback<List<GithubRepository>>() {
            @Override
            public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response) {
                if (response.isSuccessful()) {

                    List<GithubRepository> githubRepositories = response.body();
                    if (githubRepositories == null) {
                        Toast.makeText(MainActivity.this, "User no found", Toast.LENGTH_SHORT).show();
                    } else {

                        List<Repository> repositories = new ArrayList<>();

                        for (GithubRepository githubRepository : githubRepositories) {
                            repositories.add(githubRepository.toRepository());
                        }

                        db.repositoryDao().insertAll(repositories);
                        db.repositoryDao().insertOwner(githubRepositories.get(0).owner.toOwner());

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<GithubRepository>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    void saveToDatabase(Owner owner, List<Repository> repositories) {

    }

}