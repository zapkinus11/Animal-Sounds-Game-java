package com.android.hayvansesleri;

import android.graphics.Bitmap;

public class Animals {
    private Bitmap image;
    private int soundResid;
    private String name;
    public Animals(Bitmap image, int soundResid, String name) {
        this.image = image;
        this.soundResid = soundResid;
        this.name = name;
    }
    public Bitmap getImage() {
        return image;
    }
    public int getSoundResid() {
        return soundResid;
    }

    public String getName() {
        return name;
    }
}