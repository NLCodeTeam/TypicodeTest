package ru.nlcodeteam.typicode.data.local;

/**
 * Created by eldar on 29.10.2017.
 */

public class Photo {
    private String title;
    private String url;

    public Photo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
