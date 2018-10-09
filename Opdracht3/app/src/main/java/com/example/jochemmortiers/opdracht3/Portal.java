package com.example.jochemmortiers.opdracht3;


import java.io.Serializable;

public class Portal implements Serializable {

    String url;
    String tile;

    public Portal(String url, String tile) {
        this.url = url;
        this.tile = tile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }


}
