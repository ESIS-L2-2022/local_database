package com.yveskalume.localdb.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Owner {
    @PrimaryKey
    public int id;
    public String login;

    public Owner() {
    }

    public Owner(int id, String login) {
        this.id = id;
        this.login = login;
    }
}
