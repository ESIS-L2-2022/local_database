package com.yveskalume.localdb.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yveskalume.localdb.database.dao.RepositoryDao;
import com.yveskalume.localdb.database.model.Owner;
import com.yveskalume.localdb.database.model.Repository;

@Database(entities = {
        Owner.class,
        Repository.class
}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepositoryDao repositoryDao();
}
