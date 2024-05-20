package com.example.peminjaman_sarpras.model;

public class IntroView_Model {

    String title , description;
    int ScreenImg;

    public IntroView_Model(String title, String description, int screenImg) {
        this.title = title;
        this.description = description;
        ScreenImg = screenImg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}
