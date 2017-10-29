package ru.nlcodeteam.typicode.data.local;

/**
 * Created by eldar on 29.10.2017.
 */

public class Album {
    private int id;
    private int userId;
    private String title;

    public Album(int id,int userId,String title) {
        this.title = title;
        this.id = id;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
