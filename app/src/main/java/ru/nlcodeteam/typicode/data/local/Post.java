package ru.nlcodeteam.typicode.data.local;

import java.io.Serializable;

/**
 * Created by eldar on 29.10.2017.
 */

public class Post implements Serializable {
    private String title;
    private String info;
    private int id;
    private int userId;

    public Post(int id, int userId, String title, String info) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
