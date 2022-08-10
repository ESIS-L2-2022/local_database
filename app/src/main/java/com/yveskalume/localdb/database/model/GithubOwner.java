package com.yveskalume.localdb.database.model;

public class GithubOwner {
    public int id;
    public String login;

    public Owner toOwner() {
        return new Owner(this.id,this.login);
    }
}
