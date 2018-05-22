package com.example.a16046512.c302_photostoreclient;

public class Photo {
    private int id;
    private String title;
    private String description;

    public Photo(int id,String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public int getid() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
