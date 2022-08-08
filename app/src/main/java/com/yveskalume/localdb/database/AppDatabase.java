package com.yveskalume.localdb.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yveskalume.localdb.database.dao.UserDao;
import com.yveskalume.localdb.database.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
