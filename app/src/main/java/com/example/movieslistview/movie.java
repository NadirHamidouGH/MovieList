package com.example.movieslistview;

public class movie {
    private int id;
    private String title;
    private String date;
    private String note;
    private String description;
    private String imgUrl;

    public movie(){


    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public movie(String title, String date, String description , String imgUrl){

        this.title = title;
        this.date = date;
        this.description = description;
        this.imgUrl = imgUrl;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
