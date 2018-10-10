package com.example.jochemmortiers.opdracht3;


import java.io.Serializable;

public class Portal implements Serializable {

    String url;
    String title;

    public Portal(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
