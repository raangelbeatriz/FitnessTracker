package com.rangelbeatriz.fitnesstracker;
public class MainItem {
    private int id;
    private int drawableId;
    private int title;
    private int color;

    public MainItem(int id, int drawableId, int title, int color) {
        this.id = id;
        this.drawableId = drawableId;
        this.title = title;
        this.color = color;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getId() {
        return id;
    }

    public int getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }
}
