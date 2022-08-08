package com.yveskalume.localdb.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nom;
    public String postNom;
    public String prenom;
    public String matricule;

    public User() {

    }

    public User(String nom, String postNom, String prenom, String matricule) {
        this.nom = nom;
        this.postNom = postNom;
        this.prenom = prenom;
        this.matricule = matricule;
    }
}
