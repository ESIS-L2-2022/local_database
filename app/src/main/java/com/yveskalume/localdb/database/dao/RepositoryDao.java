package com.yveskalume.localdb.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.yveskalume.localdb.database.model.Owner;
import com.yveskalume.localdb.database.model.Repository;
import com.yveskalume.localdb.database.model.RepositoryOwner;

import java.util.List;

@Dao
public interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOwner(Owner owner);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Repository> repositories);

    @Transaction
    @Query("SELECT * FROM owner LIMIT 1")
    RepositoryOwner findOwner();


}
