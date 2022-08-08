package com.yveskalume.localdb;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.yveskalume.localdb.database.AppDatabase;
import com.yveskalume.localdb.database.model.User;
import com.yveskalume.localdb.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.valider.setOnClickListener(v -> {
            enregistrer();
        });

        getData();
    }

    private void getData() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();

        List<User> users = db.userDao().getAll();

        StringBuilder usersList = new StringBuilder();

        for (User user : users) {
            usersList.append(user.matricule).append("\n");
        }

        binding.users.setText(usersList);
    }

    private void enregistrer() {
        User user = new User(
                binding.name.getText().toString(),
                binding.postName.getText().toString(),
                binding.prenom.getText().toString(),
                binding.matricule.getText().toString()
        );

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();

        db.userDao().insert(user);
        getData();

    }
}