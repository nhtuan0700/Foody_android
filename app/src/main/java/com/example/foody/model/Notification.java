package com.example.foody.model;

public class Notification {
    private String title;
    private String content;
    private int image;
    private String time;

    public Notification(String title, String content, int image, String time) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
